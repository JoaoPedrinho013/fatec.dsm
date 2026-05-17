from flask import Blueprint, request, jsonify
from app.controllers import auth_controller as AC
from app.controllers import usuario_controller as UC

usuario_bp = Blueprint("usuarios", __name__, url_prefix="/usuarios")


@usuario_bp.get("/me/emprestimos")
def emprestimos():
    """
    Listar empréstimos do usuário autenticado
    ---
    tags:
      - Perfil do Usuário
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
    responses:
      200:
        description: Lista de empréstimos ativos
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    return jsonify(UC.listar_emprestimos(user["username"])), 200


@usuario_bp.get("/me/favoritos")
def favoritos():
    """
    Listar favoritos do usuário autenticado
    ---
    tags:
      - Perfil do Usuário
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
    responses:
      200:
        description: Lista de livros favoritos
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    return jsonify(UC.listar_favoritos(user["username"])), 200


@usuario_bp.get("/me/espera")
def espera():
    """
    Listar lista de espera do usuário autenticado
    ---
    tags:
      - Perfil do Usuário
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
    responses:
      200:
        description: Livros na lista de espera
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    return jsonify(UC.listar_espera(user["username"])), 200


@usuario_bp.get("/me/notificacoes")
def notificacoes():
    """
    Listar notificações do usuário autenticado
    ---
    tags:
      - Perfil do Usuário
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
    responses:
      200:
        description: Lista de notificações
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    return jsonify(UC.listar_notificacoes(user["username"])), 200


@usuario_bp.patch("/me/notificacoes/<int:index>/lida")
def marcar_notificacao_lida(index):
    """
    Marcar notificação como lida (mantém na lista, remove do contador)
    ---
    tags:
      - Perfil do Usuário
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: index
        type: integer
        required: true
    responses:
      200:
        description: Notificação marcada como lida
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    UC.marcar_notificacao_lida(user["username"], index)
    return jsonify({"mensagem": "Notificação marcada como lida"}), 200


@usuario_bp.delete("/me/notificacoes/<int:index>")
def remover_notificacao(index):
    """
    Remover notificação pelo índice
    ---
    tags:
      - Perfil do Usuário
    parameters:
      - in: header
        name: Authorization
        type: string
        required: true
      - in: path
        name: index
        type: integer
        required: true
    responses:
      200:
        description: Notificação removida
      401:
        description: Não autenticado
    """
    user, err = AC.requer_auth(request.headers.get("Authorization"))
    if err:
        return jsonify({"detail": err}), 401
    UC.remover_notificacao(user["username"], index)
    return jsonify({"mensagem": "Notificação removida"}), 200
