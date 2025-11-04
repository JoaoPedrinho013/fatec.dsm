<?php
require '../config/config.php';
session_start();

if (!isset($_SESSION['usuario_id']) || $_SESSION['usuario_nivel'] !== 'admin') {
    header('Location: ../index.php');
    exit;
}

$erro = '';
$sucesso = '';

if (isset($_POST['salvar'])) {
    $titulo = trim($_POST['titulo']);
    $descricao = trim($_POST['descricao']);
    $preco = trim($_POST['preco']);

    $fotoPath = null;

    if (isset($_FILES['foto']) && $_FILES['foto']['error'] === 0) {
        $nomeFoto = time() . '_' . preg_replace('/[^a-zA-Z0-9._-]/', '_', $_FILES['foto']['name']);
        $destino = '../public/images/produtos/' . $nomeFoto;

        if (move_uploaded_file($_FILES['foto']['tmp_name'], $destino)) {
            $fotoPath = 'images/produtos/' . $nomeFoto;
        } else {
            $erro = "Falha ao enviar a imagem.";
        }
    }

    if (!$erro) {
        $sql = "INSERT INTO servico (titulo, descricao, preco, foto) 
                VALUES (:titulo, :descricao, :preco, :foto)";
        $stmt = $pdo->prepare($sql);
        if ($stmt->execute([
            ':titulo' => $titulo,
            ':descricao' => $descricao,
            ':preco' => $preco,
            ':foto' => $fotoPath
        ])) {
            $sucesso = "Serviço criado com sucesso!";
            header("Location: dashboard-admin.php#tabela-servicos");
            exit;
        } else {
            $erro = "Erro ao salvar o serviço no banco.";
        }
    }
}


$titulo_pagina = "Criar Novo Serviço";
$css_pagina = "../public/css/editar-servico.css"; 
include 'header.php';
?>

<main>
<section class="cadastro-section">
    <div class="cadastro-container">
        <h1 class="cadastro-title">Criar Novo Serviço</h1>

        <?php if ($erro): ?>
            <p class="mensagem erro"><?php echo $erro; ?></p>
        <?php endif; ?>
        <?php if ($sucesso): ?>
            <p class="mensagem sucesso"><?php echo $sucesso; ?></p>
        <?php endif; ?>

        <form class="cadastro-form" action="create-servico.php" method="POST" enctype="multipart/form-data">
            <input class="cadastro-input" type="text" name="titulo" placeholder="Título" required>
            <textarea class="cadastro-input" name="descricao" placeholder="Descrição" rows="4" required></textarea>
            <input class="cadastro-input" type="number" name="preco" placeholder="Preço" step="0.01" required>
            <input class="cadastro-input" type="file" name="foto" accept="image/*">
            <input class="cadastro-submit" type="submit" name="salvar" value="Salvar Serviço">
        </form>
    </div>
</section>
</main>

<?php include 'footer.php'; ?>
