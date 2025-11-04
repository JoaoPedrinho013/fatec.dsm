<?php
session_start();
require '../config/config.php';

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
    echo "Serviço não selecionado!";
    exit;
}


$stmt = $pdo->prepare("SELECT id, titulo, descricao, foto, preco FROM servico WHERE id = ?");
$stmt->execute([$servico_id]);
$servico = $stmt->fetch();

if (!$servico) {
    echo "Serviço não encontrado!";
    exit;
}



$titulo_pagina = "Checkout - " . htmlspecialchars($servico['titulo']);
$css_pagina = "../public/css/checkout.css";


include 'header.php';
?>

<main class="checkout-main">
    <div class="checkout-card">
        <?php if (!empty($servico['foto'])): ?>
            <img src="../public/<?php echo htmlspecialchars($servico['foto']); ?>" alt="<?php echo htmlspecialchars($servico['titulo']); ?>">
        <?php endif; ?>
        <h2><?php echo htmlspecialchars($servico['titulo']); ?></h2>
        <p><?php echo htmlspecialchars($servico['descricao']); ?></p>
        <p class="preco">R$ <?php echo number_format($servico['preco'], 2, ',', '.'); ?></p>
        <a href="avaliar-servico.php?id=<?php echo $servico['id']; ?>" class="btn-finalizar">Finalizar Compra</a>
    </div>
</main>

<?php
include 'footer.php';
?>
