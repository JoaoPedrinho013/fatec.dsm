import os
from dotenv import load_dotenv

load_dotenv()

class Config:
    REDIS_HOST = os.getenv("REDIS_HOST", "localhost")
    REDIS_PORT = int(os.getenv("REDIS_PORT", 6379))
    REDIS_DB = int(os.getenv("REDIS_DB", 1))  # db=1 para nao conflitar com o outro projeto
    SECRET_KEY = os.getenv("FLASK_SECRET_KEY", "biblioteca-digital-secret")
    DEBUG = os.getenv("FLASK_DEBUG", "true").lower() == "true"
    PORT = int(os.getenv("FLASK_PORT", 5000))

    # Prefixo de chaves Redis — distinto do outro projeto
    KEY_PREFIX = "bib2"
