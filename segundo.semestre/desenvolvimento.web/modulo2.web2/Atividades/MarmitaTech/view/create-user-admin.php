<?php
require '../config/config.php';
session_start();

if (!isset($_SESSION['usuario_id']) || $_SESSION['usuario_nivel'] !== 'admin') {
    header('Location: ../index.php');
    exit;
}

$erro = '';
$sucesso = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nome = trim($_POST['nome']);
    $email = trim($_POST['email']);
    $senha = trim($_POST['senha']);
    $nivel = $_POST['nivel'] === 'admin' ? 'admin' : 'usuario'; // garante valor válido
    $senha_hash = password_hash($senha, PASSWORD_DEFAULT);

    // Caminho padrão da foto
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
        $stmt = $pdo->prepare("INSERT INTO usuario (nome, email, senha, foto, nivel) VALUES (?, ?, ?, ?, ?)");
        if ($stmt->execute([$nome, $email, $senha_hash, $caminho_foto, $nivel])) {
            $sucesso = "Usuário criado com sucesso!";
            header('Location: dashboard-admin.php#tabela-usuarios');
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

<main>
<section class="cadastro-section">
    <div class="cadastro-container">
        <h1 class="cadastro-title">Novo Usuário</h1>

        <?php if ($erro): ?>
            <p class="mensagem erro"><?php echo $erro; ?></p>
        <?php endif; ?>
        <?php if ($sucesso): ?>
            <p class="mensagem sucesso"><?php echo $sucesso; ?></p>
        <?php endif; ?>

        <form class="cadastro-form" action="create-user-admin.php" method="post" enctype="multipart/form-data">
            <input class="cadastro-input" type="text" name="nome" placeholder="Nome" required>
            <input class="cadastro-input" type="email" name="email" placeholder="Email" required>
            <input class="cadastro-input" type="password" name="senha" placeholder="Senha" required>
            <select class="cadastro-input" name="nivel" id="nivel">
                <option value="usuario" selected>Usuário</option>
                <option value="admin">Admin</option>
            </select>
            <input class="cadastro-input" type="file" name="foto" accept="image/*">
            <input class="cadastro-submit" type="submit" name="salvar" value="Salvar Usuário">
        </form>

        <div class="form-actions" style="text-align:center; margin-top: 1rem;">
            <a href="dashboard-admin.php" class="button-voltar">Voltar</a>
        </div>

    </div>
</section>
</main>

<?php include 'footer.php'; ?>
