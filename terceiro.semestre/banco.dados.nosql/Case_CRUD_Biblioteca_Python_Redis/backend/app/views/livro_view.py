import os
import re
import unicodedata
from flask import Blueprint, request, jsonify
from app.controllers import auth_controller as AC
from app.controllers import livro_controller as LC

livro_bp = Blueprint("livros", __name__, url_prefix="/livros")

COVERS_DIR = "/covers"


def _titulo_para_filename(titulo: str) -> str:
    """Normaliza o título para nome de arquivo (igual ao frontend)."""
    nfd = unicodedata.normalize("NFD", titulo)
    sem_acentos = "".join(c for c in nfd if not unicodedata.combining(c))
    s = sem_acentos.lower().strip()
    s = re.sub(r"[^a-z0-9\s]", "", s)
    s = re.sub(r"\s+", "_", s)
    return s


@livro_bp.post("/")
def criar_livro():
    """
    Criar novo livro (admin)
    ---
    tags:
      - Livros
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: body
        name: body
        required: true
        schema:
          type: object
          required:
            - titulo
            - autor
            - categoria
            - ano
            - quantidade
          properties:
            titulo:
              type: string
              example: "Clean Code"
            autor:
              type: string
              example: "Robert C. Martin"
            categoria:
              type: string
              example: "Tecnologia,Fantasia"
              description: "Categorias separadas por vírgula, ou array de strings"
            ano:
              type: integer
              example: 2008
            quantidade:
              type: integer
              example: 3
            status:
              type: string
              example: "Disponivel"
            sinopse:
              type: string
              example: "Um livro sobre boas práticas de programação."
    responses:
      201:
        description: Livro criado
      400:
        description: Dados inválidos
      403:
        description: Acesso negado
    """
    _, err = AC.requer_admin(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 403

    data = request.get_json(force=True, silent=True) or {}
    # Aceita categorias como array ou string separada por vírgula
    if isinstance(data.get("categorias"), list):
        data["categoria"] = ",".join(data["categorias"])
    livro, errors = LC.criar_livro(data)
    if errors:
        return jsonify({"detail": errors}), 400
    return jsonify(livro), 201


@livro_bp.get("/")
def listar_livros():
    """
    Listar todos os livros
    ---
    tags:
      - Livros
    responses:
      200:
        description: Lista de livros
    """
    return jsonify(LC.listar_livros()), 200


@livro_bp.get("/<livro_id>")
def buscar_livro(livro_id):
    """
    Buscar livro por ID
    ---
    tags:
      - Livros
    parameters:
      - in: path
        name: livro_id
        type: string
        required: true
    responses:
      200:
        description: Dados do livro
      404:
        description: Livro não encontrado
    """
    livro, err = LC.buscar_livro(livro_id)
    if err:
        return jsonify({"detail": err}), 404
    return jsonify(livro), 200


@livro_bp.put("/<livro_id>")
def atualizar_livro(livro_id):
    """
    Atualizar livro (admin)
    ---
    tags:
      - Livros
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: livro_id
        type: string
        required: true
      - in: body
        name: body
        required: true
        schema:
          type: object
          properties:
            titulo:
              type: string
            autor:
              type: string
            categoria:
              type: string
            ano:
              type: integer
            quantidade:
              type: integer
            status:
              type: string
    responses:
      200:
        description: Livro atualizado
      400:
        description: Dados inválidos
      403:
        description: Acesso negado
      404:
        description: Livro não encontrado
    """
    _, err = AC.requer_admin(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 403

    data = request.get_json(force=True, silent=True) or {}
    # Aceita categorias como array ou string separada por vírgula
    if isinstance(data.get("categorias"), list):
        data["categoria"] = ",".join(data["categorias"])
    livro, errors = LC.atualizar_livro(livro_id, data)
    if errors:
        status = 404 if errors == "Livro não encontrado" else 400
        return jsonify({"detail": errors}), status
    return jsonify(livro), 200


@livro_bp.delete("/<livro_id>")
def deletar_livro(livro_id):
    """
    Deletar livro (admin)
    ---
    tags:
      - Livros
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: livro_id
        type: string
        required: true
    responses:
      200:
        description: Livro deletado
      403:
        description: Acesso negado
      404:
        description: Livro não encontrado
    """
    _, err = AC.requer_admin(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 403
    ok, err2 = LC.deletar_livro(livro_id)
    if not ok:
        return jsonify({"detail": err2}), 404
    return jsonify({"mensagem": "Livro deletado com sucesso"}), 200


@livro_bp.post("/upload-capa")
def upload_capa():
    """
    Upload da capa de um livro (admin) — salva em /covers/<titulo_normalizado>.png
    ---
    tags:
      - Livros
    consumes:
      - multipart/form-data
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: formData
        name: titulo
        type: string
        required: true
      - in: formData
        name: file
        type: file
        required: true
    responses:
      200:
        description: Capa salva com sucesso
      400:
        description: Dados inválidos
      403:
        description: Acesso negado
    """
    _, err = AC.requer_admin(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 403

    titulo = (request.form.get("titulo") or "").strip()
    if not titulo:
        return jsonify({"detail": "Campo 'titulo' é obrigatório"}), 400

    file = request.files.get("file")
    if not file:
        return jsonify({"detail": "Arquivo não enviado"}), 400

    ext = os.path.splitext(file.filename.lower())[1]
    if ext not in (".png", ".jpg", ".jpeg"):
        return jsonify({"detail": "Apenas arquivos PNG ou JPG são aceitos"}), 400

    # Sempre salva como .png para manter compatibilidade com getCoverUrl no frontend
    filename = _titulo_para_filename(titulo) + ".png"
    os.makedirs(COVERS_DIR, exist_ok=True)
    file.save(os.path.join(COVERS_DIR, filename))

    return jsonify({"mensagem": "Capa salva com sucesso", "filename": filename}), 200


@livro_bp.post("/<livro_id>/emprestar")
def emprestar(livro_id):
    """
    Emprestar livro
    ---
    tags:
      - Empréstimos
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: livro_id
        type: string
        required: true
    responses:
      200:
        description: Empréstimo realizado
      400:
        description: Não foi possível realizar o empréstimo
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    ok, err2 = LC.emprestar(livro_id, user["username"])
    if not ok:
        return jsonify({"detail": err2}), 400
    return jsonify({"mensagem": "Empréstimo realizado com sucesso"}), 200


@livro_bp.post("/<livro_id>/devolver")
def devolver(livro_id):
    """
    Devolver livro
    ---
    tags:
      - Empréstimos
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: livro_id
        type: string
        required: true
    responses:
      200:
        description: Devolução realizada
      400:
        description: Erro na devolução
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    ok, err2 = LC.devolver(livro_id, user["username"])
    if not ok:
        return jsonify({"detail": err2}), 400
    return jsonify({"mensagem": "Devolução realizada com sucesso"}), 200


@livro_bp.post("/<livro_id>/favoritos")
def favoritar(livro_id):
    """
    Adicionar livro aos favoritos
    ---
    tags:
      - Favoritos
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: livro_id
        type: string
        required: true
    responses:
      200:
        description: Adicionado aos favoritos
      400:
        description: Já está nos favoritos
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    ok, err2 = LC.favoritar(livro_id, user["username"])
    if not ok:
        return jsonify({"detail": err2}), 400
    return jsonify({"mensagem": "Adicionado aos favoritos"}), 200


@livro_bp.delete("/<livro_id>/favoritos")
def remover_favorito(livro_id):
    """
    Remover livro dos favoritos
    ---
    tags:
      - Favoritos
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: livro_id
        type: string
        required: true
    responses:
      200:
        description: Removido dos favoritos
      400:
        description: Livro não está nos favoritos
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    ok, err2 = LC.remover_favorito(livro_id, user["username"])
    if not ok:
        return jsonify({"detail": err2}), 400
    return jsonify({"mensagem": "Removido dos favoritos"}), 200


@livro_bp.post("/<livro_id>/espera")
def entrar_espera(livro_id):
    """
    Entrar na lista de espera
    ---
    tags:
      - Empréstimos
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: livro_id
        type: string
        required: true
    responses:
      200:
        description: Adicionado à lista de espera
      400:
        description: Erro
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    ok, err2 = LC.entrar_espera(livro_id, user["username"])
    if not ok:
        return jsonify({"detail": err2}), 400
    return jsonify({"mensagem": "Adicionado à lista de espera"}), 200
