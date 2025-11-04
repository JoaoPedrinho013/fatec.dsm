<?php
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

require_once __DIR__ . '/../config/config.php';

$mensagem_sucesso = '';
$mensagem_erro = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['nome'])) {
    $nome = trim($_POST['nome']);
    $email = trim($_POST['email']);
    $mensagem = trim($_POST['mensagem']);

    if (!empty($nome) && !empty($email) && !empty($mensagem) && filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $stmt = $pdo->prepare("INSERT INTO contato (nome, email, mensagem) VALUES (?, ?, ?)");
        if ($stmt->execute([$nome, $email, $mensagem])) {
            $mensagem_sucesso = "Mensagem enviada com sucesso!";
        } else {
            $mensagem_erro = "Erro ao enviar mensagem.";
        }
    } else {
        $mensagem_erro = "Por favor, preencha todos os campos corretamente.";
    }
}
?>

<footer class="footer" id="contato">
    <div class="footer-content">
        <div class="contato">
            <h2>Contato</h2>
            <p><i class="fas fa-map-marker-alt"></i> Rua dos Alfeneiros, nº 4<br>Little Whinging, Surrey</p>
            <p><i class="fas fa-phone"></i> +44 6666-62442</p>
            <p><i class="fas fa-envelope"></i> contato@marmitatech.com</p>
            <div style="margin-top: 1.5rem;">
                <a href="https://wa.me/44666662442" target="_blank"><i class="fab fa-whatsapp"></i></a>
                <a href="https://www.instagram.com" target="_blank"><i class="fab fa-instagram"></i></a>
            </div>
        </div>

        <div class="footer-form">
            <h2>Fale conosco</h2>

            <?php if (!empty($mensagem_sucesso)): ?>
                <div class="msg-sucesso"><?php echo $mensagem_sucesso; ?></div>
            <?php elseif (!empty($mensagem_erro)): ?>
                <div class="msg-erro"><?php echo $mensagem_erro; ?></div>
            <?php endif; ?>

            <form id="formContato" method="POST" action="">
                <div class="form-group">
                    <input type="text" name="nome" placeholder="Seu nome" required>
                </div>
                <div class="form-group">
                    <input type="email" name="email" placeholder="Seu e-mail" required>
                </div>
                <div class="form-group">
                    <textarea name="mensagem" placeholder="Sua mensagem" required></textarea>
                </div>
                <button type="submit" class="form-btn">Enviar Mensagem</button>
            </form>
        </div>
    </div>

    <p class="direitos">Marmita Tech &copy; Todos os direitos reservados</p>
</footer>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const sucesso = document.querySelector(".msg-sucesso");
        const erro = document.querySelector(".msg-erro");

        const esconderMensagem = (elemento) => {
            setTimeout(() => {
                elemento.style.opacity = "0";
                elemento.style.transition = "opacity 0.5s ease";
                setTimeout(() => elemento.remove(), 500);
            }, 2000);
        };

        if (sucesso) esconderMensagem(sucesso);
        if (erro) esconderMensagem(erro);

        const url = new URL(window.location);
        url.search = "";
        window.history.replaceState({}, document.title, url);
    });
</script>