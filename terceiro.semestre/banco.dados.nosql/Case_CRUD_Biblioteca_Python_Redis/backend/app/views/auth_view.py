from flask import Blueprint, request, jsonify
from app.controllers import auth_controller as AC

auth_bp = Blueprint("auth", __name__, url_prefix="/auth")


@auth_bp.post("/cadastro")
def cadastro():
    """
    Registrar novo usuário
    ---
    tags:
      - Autenticação
    consumes:
      - application/json
    parameters:
      - in: body
        name: body
        required: true
        schema:
          type: object
          required:
            - username
            - password
          properties:
            username:
              type: string
              example: joao
            password:
              type: string
              example: senha123
    responses:
      201:
        description: Usuário criado com sucesso
      400:
        description: Dados inválidos ou username já existe
    """
    data = request.get_json(force=True, silent=True) or {}
    usuario, err = AC.registrar(data.get("username"), data.get("password"))
    if err:
        return jsonify({"detail": err}), 400
    return jsonify(usuario), 201


@auth_bp.get("/login")
def login():
    """
    Autenticar usuário (Basic Auth)
    ---
    tags:
      - Autenticação
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
        description: "Basic base64(username:password)"
        example: "Basic YWRtaW46YWRtaW4="
    responses:
      200:
        description: Login bem-sucedido, retorna dados do usuário
      401:
        description: Credenciais inválidas
    """
    user, err = AC.login(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    return jsonify(user), 200


@auth_bp.get("/usuarios")
def listar_usuarios():
    """
    Listar todos os usuários (admin)
    ---
    tags:
      - Autenticação
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
    responses:
      200:
        description: Lista de usuários
      403:
        description: Acesso negado
    """
    _, err = AC.requer_admin(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 403
    return jsonify(AC.listar_usuarios()), 200


@auth_bp.delete("/usuarios/<username>")
def deletar_usuario(username):
    """
    Deletar usuário (admin)
    ---
    tags:
      - Autenticação
    parameters:
      - in: path
        name: username
        type: string
        required: true
      - in: header
        name: Authorization
        type: string
        required: true
    responses:
      200:
        description: Usuário deletado
      403:
        description: Acesso negado
      404:
        description: Usuário não encontrado
    """
    _, err = AC.requer_admin(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 403
    ok, err2 = AC.deletar_usuario(username)
    if not ok:
        return jsonify({"detail": err2}), 404
    return jsonify({"mensagem": "Usuário deletado com sucesso"}), 200
