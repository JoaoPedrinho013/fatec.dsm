import os
import threading
import time
from app import create_app
from app.controllers.auth_controller import criar_admin_padrao
from config import Config


def auto_return_loop():
    """Thread que verifica e devolve automaticamente empréstimos vencidos a cada 30s."""
    time.sleep(5)  # aguarda o servidor iniciar
    while True:
        try:
            from app.controllers.livro_controller import verificar_emprestimos_vencidos
            verificar_emprestimos_vencidos()
        except Exception as e:
            print(f"[Auto-retorno] Erro no loop: {e}")
        time.sleep(30)


app = create_app()

if __name__ == "__main__":
    with app.app_context():
        criar_admin_padrao()

    # Inicia o background thread de auto-retorno
    # Em modo debug, o werkzeug usa dois processos; só inicia no processo filho
    should_start = not Config.DEBUG or os.environ.get("WERKZEUG_RUN_MAIN") == "true"
    if should_start:
        t = threading.Thread(target=auto_return_loop, daemon=True, name="AutoReturn")
        t.start()
        print("[Auto-retorno] Thread iniciada (intervalo: 30s)")

    app.run(host="0.0.0.0", port=Config.PORT, debug=Config.DEBUG)
