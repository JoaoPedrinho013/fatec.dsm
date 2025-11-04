<?php
require '../config/config.php';
session_start();

$erro = '';
$sucesso = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nome = trim($_POST['nome']);
    $email = trim($_POST['email']);
    $senha = trim($_POST['senha']);
    $senha_hash = password_hash($senha, PASSWORD_DEFAULT);


    $caminho_foto = 'images/users/default.png';
    if(isset($_FILES['foto']) && $_FILES['foto']['error'] === UPLOAD_ERR_OK) {
        $extensao = pathinfo($_FILES['foto']['name'], PATHINFO_EXTENSION);
        $nome_arquivo = uniqid('user_', true) . '.' . $extensao;

        $pasta_destino = __DIR__ . '/../public/images/users/';
        if(!is_dir($pasta_destino)) mkdir($pasta_destino, 0755, true);

        $arquivo_destino = $pasta_destino . $nome_arquivo;

        if(move_uploaded_file($_FILES['foto']['tmp_name'], $arquivo_destino)) {
            $caminho_foto = 'images/users/' . $nome_arquivo;
        } else {
            $erro = "Falha ao enviar a imagem.";
        }
    }

    if (!$erro) {
        $nivel = 'usuario';

        $stmt = $pdo->prepare("INSERT INTO usuario (nome, email, senha, foto, nivel) VALUES (?, ?, ?, ?, ?)");
        if ($stmt->execute([$nome, $email, $senha_hash, $caminho_foto, $nivel])) {
            $sucesso = "Usuário criado com sucesso!";
            header('Location: login.php');
            exit;
        } else {
            $erro = "Erro ao salvar o usuário no banco.";
        }
    }
}

$titulo_pagina = "Novo Usuário";
$css_pagina = "../public/css/create-user.css"; 
include 'header.php';
?>

<section class="cadastro-section">
    <div class="cadastro-container">
        <h1 class="cadastro-title">Novo usuário</h1>

        <?php if ($erro): ?>
            <p class="mensagem erro"><?php echo $erro; ?></p>
        <?php endif; ?>
        <?php if ($sucesso): ?>
            <p class="mensagem sucesso"><?php echo $sucesso; ?></p>
        <?php endif; ?>

        <form class="cadastro-form" action="create-user.php" method="post" enctype="multipart/form-data">
            <input class="cadastro-input" type="text" name="nome" placeholder="Nome" required>
            <input class="cadastro-input" type="email" name="email" placeholder="Email" required>
            <input class="cadastro-input" type="password" name="senha" placeholder="Senha" required>
            <input class="cadastro-input" type="file" name="foto" accept="image/*">
            <input class="cadastro-submit" type="submit" value="Salvar">
        </form>

        <p class="cadastro-text">Já tem cadastro? <a href="login.php">Clique Aqui</a></p>
        <p class="cadastro-text"><a href="../index.php">Início</a></p>
    </div>
</section>

<?php include 'footer.php'; ?>
