<?php
session_start();
require '../config/config.php';

$mensagem_sucesso = '';
$mensagem_erro = '';

if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit;
}

if (isset($_GET['id'])) {
    $servico_id = (int)$_GET['id'];
    $_SESSION['servico_id'] = $servico_id; 
} elseif (isset($_SESSION['servico_id'])) {
    $servico_id = (int)$_SESSION['servico_id']; 
} else {
    header("Location: index.php"); 
    exit;
}

$stmt = $pdo->prepare("SELECT id, titulo, descricao, foto, preco FROM servico WHERE id = ?");
$stmt->execute([$servico_id]);
$servico = $stmt->fetch();

if (!$servico) {
    echo "Serviço não encontrado!";
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nome = trim($_POST['nome']);
    $estrelas = (int)$_POST['estrelas'];
    $comentario = trim($_POST['comentario']);

    if (!empty($nome) && !empty($comentario) && $estrelas >= 1 && $estrelas <= 5) {
        $stmt = $pdo->prepare("INSERT INTO avaliacao (nome, estrelas, comentario) VALUES (?, ?, ?)");
        if ($stmt->execute([$nome, $estrelas, $comentario])) {
            header("Location: ../index.php");
            exit;
        } else {
            $mensagem_erro = "Erro ao enviar avaliação. Tente novamente.";
        }
    } else {
        $mensagem_erro = "Preencha todos os campos corretamente e selecione as estrelas.";
    }
}

$titulo_pagina = "Avaliar Serviço - " . htmlspecialchars($servico['titulo']);
$css_pagina = "../public/css/avaliar-servico.css";

include 'header.php';
?>

<main class="checkout-main">
    <div class="avaliar-container">
        <?php if (!empty($servico['foto'])): ?>
            <img src="../public/<?php echo htmlspecialchars($servico['foto']); ?>" alt="<?php echo htmlspecialchars($servico['titulo']); ?>">
        <?php endif; ?>
        <h2><?php echo htmlspecialchars($servico['titulo']); ?></h2>
        <p class="preco">R$ <?php echo number_format($servico['preco'], 2, ',', '.'); ?></p>

        <?php if ($mensagem_sucesso): ?>
            <div class="mensagem sucesso"><?php echo $mensagem_sucesso; ?></div>
        <?php elseif ($mensagem_erro): ?>
            <div class="mensagem erro"><?php echo $mensagem_erro; ?></div>
        <?php endif; ?>

        <form method="POST">
            <label for="nome">Nome:</label>
            <input type="text" name="nome" id="nome" value="<?php echo htmlspecialchars($_SESSION['usuario_nome']); ?>" required>

            <label for="estrelas">Estrelas:</label>
            <select name="estrelas" id="estrelas" required>
                <option value="">Selecione</option>
                <option value="1">⭐</option>
                <option value="2">⭐⭐</option>
                <option value="3">⭐⭐⭐</option>
                <option value="4">⭐⭐⭐⭐</option>
                <option value="5">⭐⭐⭐⭐⭐</option>
            </select>

            <label for="comentario">Comentário:</label>
            <textarea name="comentario" id="comentario" placeholder="Escreva sua opinião" required></textarea>

            <button type="submit" class="btn-avaliar">Enviar Avaliação</button>
        </form>
    </div>
</main>

<?php
include 'footer.php';
?>
