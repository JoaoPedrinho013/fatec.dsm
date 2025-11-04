<?php 
require '../config/config.php';
session_start();

$titulo_pagina = "Login";
$css_pagina = "../public/css/login.css";

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = $_POST['email'];
    $senha = $_POST['senha'];

    $query = $pdo->prepare("SELECT * FROM usuario WHERE email = ?");
    $query->execute([$email]);
    $usuario = $query->fetch();

    if($usuario && password_verify($senha, $usuario['senha'])) {
        $_SESSION['usuario_id'] = $usuario['id'];
        $_SESSION['usuario_nome'] = $usuario['nome'];
        $_SESSION['usuario_foto'] = $usuario['foto'] ?? null;
        $_SESSION['usuario_nivel'] = $usuario['nivel'];

        if($usuario['nivel'] === 'admin') {
            header('Location: dashboard-admin.php');
        } else {
            header('Location: ../index.php');
        }
        exit;
    } else {
        $erro = "Email ou senha inválidos.";
    }
}


include 'header.php';
?>

<main>
    <section class="login-section">
        <div class="login-container">
            <h1 class="login-title">Login</h1>
            <?php if(isset($erro)) echo "<p style='color:red;margin-bottom:1rem;'>$erro</p>"; ?>
            <form class="login-form" action="#" method="post">
                <input class="login-input" type="email" name="email" placeholder="Email" required>
                <input class="login-input" type="password" name="senha" placeholder="Senha" required>
                <input class="login-submit" type="submit" value="Entrar">
            </form>
            <p class="login-text">Não tem cadastro? <a href="create-user.php">Clique Aqui</a></p>
            <p class="login-text"><a href="../index.php">Início</a></p>
        </div>
    </section>
</main>

<?php
include 'footer.php';
?>
