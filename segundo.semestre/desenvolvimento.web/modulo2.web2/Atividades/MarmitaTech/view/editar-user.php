<?php
require '../config/config.php';
session_start();

$erro = '';
$sucesso = '';

if (!isset($_SESSION['usuario_id'])) {
    header('Location: ../index.php');
    exit;
}

$dashboardLink = ($_SESSION['usuario_nivel'] ?? '') === 'admin' 
    ? 'dashboard-admin.php' 
    : 'dashboard-user.php';

$id = $_SESSION['usuario_id'];

$stmt = $pdo->prepare("SELECT * FROM usuario WHERE id = :id");
$stmt->execute([':id' => $id]);
$usuario = $stmt->fetch(PDO::FETCH_ASSOC);

if (!$usuario) {
    $erro = "Usuário não encontrado.";
}

if (isset($_POST['salvar']) && !$erro) {
    $nome = trim($_POST['nome']) ?: $usuario['nome'];
    $email = trim($_POST['email']) ?: $usuario['email'];
    $nivel = ($_SESSION['usuario_nivel'] === 'admin') ? ($_POST['nivel'] ?? $usuario['nivel']) : 'usuario';
    $senhaHash = !empty($_POST['senha']) ? password_hash($_POST['senha'], PASSWORD_DEFAULT) : $usuario['senha'];

    $fotoPath = $usuario['foto'];
    if (isset($_FILES['foto']) && $_FILES['foto']['error'] === 0) {
        $nomeFoto = time() . '_' . $_FILES['foto']['name'];
        $destino = '../public/images/users/' . $nomeFoto;
        if (move_uploaded_file($_FILES['foto']['tmp_name'], $destino)) {
            $fotoPath = 'images/users/' . $nomeFoto;
        } else {
            $erro = "Falha ao enviar a foto.";
        }
    }

    if (!$erro) {
        $stmtUpdate = $pdo->prepare("
            UPDATE usuario 
            SET nome = :nome, email = :email, senha = :senha, foto = :foto, nivel = :nivel 
            WHERE id = :id
        ");
        if ($stmtUpdate->execute([
            ':nome' => $nome,
            ':email' => $email,
            ':senha' => $senhaHash,
            ':foto' => $fotoPath,
            ':nivel' => $nivel,
            ':id' => $id
        ])) {
            if ($_SESSION['usuario_id'] == $id) {
                $_SESSION['usuario_nome'] = $nome;
                $_SESSION['usuario_foto'] = $fotoPath;
            }
            header("Location: $dashboardLink");
            exit;
        } else {
            $erro = "Erro ao atualizar os dados no banco.";
        }
    }
}

$titulo_pagina = "Editar Usuário";
$css_pagina = "../public/css/editar-user.css";
include 'header.php';
?>

<main>
<section class="cadastro-section">
    <div class="cadastro-container">
        <h1 class="cadastro-title">Editar Usuário</h1>

        <?php if ($erro): ?>
            <p style="color:red; text-align:center; margin-bottom:1rem;"><?php echo $erro; ?></p>
        <?php endif; ?>
        <?php if ($sucesso): ?>
            <p style="color:green; text-align:center; margin-bottom:1rem;"><?php echo $sucesso; ?></p>
        <?php endif; ?>

        <form class="cadastro-form" action="editar-user.php" method="POST" enctype="multipart/form-data">
            <input class="cadastro-input" type="text" name="nome" placeholder="Nome (opcional)" value="<?php echo htmlspecialchars($usuario['nome'] ?? ''); ?>">
            <input class="cadastro-input" type="email" name="email" placeholder="Email (opcional)" value="<?php echo htmlspecialchars($usuario['email'] ?? ''); ?>">

            <?php if ($_SESSION['usuario_nivel'] === 'admin'): ?>
                <select class="cadastro-input" name="nivel">
                    <option value="admin" <?php echo ($usuario['nivel'] ?? '') === 'admin' ? 'selected' : ''; ?>>Admin</option>
                    <option value="usuario" <?php echo ($usuario['nivel'] ?? '') === 'usuario' ? 'selected' : ''; ?>>Usuário</option>
                </select>
            <?php endif; ?>

            <input class="cadastro-input" type="password" name="senha" placeholder="Nova senha (opcional)">

            <?php 
            $fotoExibe = !empty($usuario['foto']) && file_exists('../public/' . $usuario['foto']) 
                         ? $usuario['foto'] 
                         : 'images/users/default.png';
            ?>
            <img src="../public/<?php echo $fotoExibe; ?>" alt="Foto atual" class="preview-img">
            <input class="cadastro-input" type="file" name="foto" accept="image/*">

            <input class="cadastro-submit" type="submit" name="salvar" value="Salvar Alterações">
        </form>


        <div style="margin-top: 1rem; display:flex; justify-content:space-between;">
            <a href="<?php echo $dashboardLink; ?>" class="button-cancelar">Cancelar</a>
        </div>
    </div>
</section>
</main>

<?php include 'footer.php'; ?>
