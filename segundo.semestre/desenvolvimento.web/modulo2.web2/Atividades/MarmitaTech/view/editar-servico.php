<?php
require '../config/config.php';
session_start();
if (!isset($_SESSION['usuario_id']) || $_SESSION['usuario_nivel'] !== 'admin') {
    header('Location: ../index.php');
    exit;
}

$erro = '';
$sucesso = '';

if (isset($_GET['id'])) {
    $id = (int)$_GET['id'];
    $_SESSION['editar_servico_id'] = $id;
} elseif (isset($_SESSION['editar_servico_id'])) {
    $id = (int)$_SESSION['editar_servico_id'];
} else {
    die("ID do serviço não fornecido ou inválido.");
}

$stmt = $pdo->prepare("SELECT * FROM servico WHERE id = :id");
$stmt->execute([':id' => $id]);
$servico = $stmt->fetch(PDO::FETCH_ASSOC);
if (!$servico) die("Serviço não encontrado.");

if (isset($_POST['salvar'])) {
    $titulo = trim($_POST['titulo']) ?: $servico['titulo'];
    $descricao = trim($_POST['descricao']) ?: $servico['descricao'];
    $preco = trim($_POST['preco']) ?: $servico['preco'];

    $fotoPath = $servico['foto'];
    if (isset($_FILES['foto']) && $_FILES['foto']['error'] === 0) {
        $nomeFoto = time() . '_' . $_FILES['foto']['name'];
        $destino = '../public/images/produtos/' . $nomeFoto;
        if (move_uploaded_file($_FILES['foto']['tmp_name'], $destino)) {
            $fotoPath = 'images/produtos/' . $nomeFoto;
        } else {
            $erro = "Falha ao enviar a imagem.";
        }
    }

    if (!$erro) {
        $stmtUpdate = $pdo->prepare("
            UPDATE servico 
            SET titulo = :titulo, descricao = :descricao, preco = :preco, foto = :foto
            WHERE id = :id
        ");
        if ($stmtUpdate->execute([
            ':titulo' => $titulo,
            ':descricao' => $descricao,
            ':preco' => $preco,
            ':foto' => $fotoPath,
            ':id' => $id
        ])) {
            $sucesso = "Serviço atualizado com sucesso!";
            header("Location: dashboard-admin.php#tabela-servicos");
            exit;
        } else {
            $erro = "Erro ao atualizar o serviço no banco.";
        }
    }
}

$titulo_pagina = "Editar Serviço";
$css_pagina = "../public/css/editar-servico.css";

include 'header.php';
?>

<main>
<section class="cadastro-section">
    <div class="cadastro-container">
        <h1 class="cadastro-title">Editar Serviço</h1>

        <?php if ($erro): ?>
            <p class="mensagem erro"><?php echo $erro; ?></p>
        <?php endif; ?>
        <?php if ($sucesso): ?>
            <p class="mensagem sucesso"><?php echo $sucesso; ?></p>
        <?php endif; ?>

        <form class="cadastro-form" action="editar-servico.php" method="POST" enctype="multipart/form-data">
            <input class="cadastro-input" type="text" name="titulo" placeholder="Título" value="<?php echo htmlspecialchars($servico['titulo'] ?? ''); ?>">
            <textarea class="cadastro-input" name="descricao" placeholder="Descrição"><?php echo htmlspecialchars($servico['descricao'] ?? ''); ?></textarea>
            <input class="cadastro-input" type="number" name="preco" placeholder="Preço" step="0.01" value="<?php echo $servico['preco'] ?? ''; ?>">

            <?php 
            $fotoExibe = !empty($servico['foto']) && file_exists('../public/' . $servico['foto']) 
                         ? $servico['foto'] 
                         : 'images/produtos/default.png';
            ?>
            <img src="../public/<?php echo $fotoExibe; ?>" alt="Imagem atual" class="preview-img">
            <input class="cadastro-input" type="file" name="foto" accept="image/*">

            <input class="cadastro-submit" type="submit" name="salvar" value="Salvar Alterações">
        </form>
    </div>
</section>
</main>

<?php include 'footer.php'; ?>
