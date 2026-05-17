from flask import Flask, jsonify
from flask_cors import CORS
from flasgger import Swagger
from config import Config


SWAGGER_TEMPLATE = {
    "swagger": "2.0",
    "info": {
        "title": "Biblioteca Digital API",
        "description": (
            "API REST para gerenciamento de uma Biblioteca Digital. "
            "Autenticação via **HTTP Basic Auth** (header Authorization: Basic base64(user:pass)). "
            "Admin padrão: **admin / admin**"
        ),
        "version": "1.0.0",
        "contact": {"name": "Biblioteca Digital"},
    },
    "securityDefinitions": {
        "basicAuth": {"type": "basic"}
    },
    "host": "localhost:5000",
    "basePath": "/",
    "schemes": ["http"],
}

SWAGGER_CONFIG = {
    "headers": [],
    "specs": [
        {
            "endpoint": "apispec",
            "route": "/apispec.json",
            "rule_filter": lambda rule: True,
            "model_filter": lambda tag: True,
        }
    ],
    "static_url_path": "/flasgger_static",
    "swagger_ui": True,
    "specs_route": "/docs/",
}


def create_app():
    app = Flask(__name__)
    app.config["SECRET_KEY"] = Config.SECRET_KEY
    app.config["SWAGGER"] = {"title": "Biblioteca Digital API", "uiversion": 3}

    # CORS — permite o frontend React
    CORS(app, resources={r"/*": {"origins": "*"}})

    # Swagger UI em /docs/
    Swagger(app, template=SWAGGER_TEMPLATE, config=SWAGGER_CONFIG)

    # Registra blueprints
    from app.views.auth_view import auth_bp
    from app.views.livro_view import livro_bp
    from app.views.usuario_view import usuario_bp

    app.register_blueprint(auth_bp)
    app.register_blueprint(livro_bp)
    app.register_blueprint(usuario_bp)

    # Health check
    @app.get("/")
    def health():
        return jsonify({"status": "ok", "servico": "Biblioteca Digital API", "docs": "/docs/"})

    return app
