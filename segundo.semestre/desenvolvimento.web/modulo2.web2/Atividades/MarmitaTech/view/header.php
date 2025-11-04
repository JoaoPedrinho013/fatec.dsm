<?php
if(session_status() == PHP_SESSION_NONE){
    session_start();
}
require '../config/config.php';
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo $titulo_pagina ?? 'Marmita Tech'; ?></title>

    <link rel="icon" href="../public/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="../public/css/index.css">

    <?php if(isset($css_pagina)) : ?>
        <link rel="stylesheet" href="<?php echo $css_pagina; ?>">
    <?php endif; ?>


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
<header class="header">
    <a href="../index.php">
        <img src="../public/images/logo/logo_nome.png" alt="Marmita Tech" class="logo">
    </a>

    <div class="header-desktop">
        <nav class="navegacao">
            <a href="../index.php#sobre">Quem somos</a>
            <a href="../index.php#cardapio">Cardápio</a>
            <a href="../index.php#contato">Contato</a>
        </nav>

        <?php if (isset($_SESSION['usuario_id'])): ?>
            <?php
            $fotoUsuario = $_SESSION['usuario_foto'] ?? 'images/users/default.png';
            $caminhoFoto = "../public/" . $fotoUsuario;
            if (!file_exists($caminhoFoto)) {
                $caminhoFoto = "../public/images/users/default.png";
            }

            $linkPerfil = ($_SESSION['usuario_nivel'] ?? '') === 'admin' 
                ? 'dashboard-admin.php' 
                : 'dashboard-user.php';
            ?>
            <div class="usuario-info">
                <a href="<?php echo $linkPerfil; ?>" class="usuario-perfil-link">
                    <img src="<?php echo htmlspecialchars($caminhoFoto); ?>" alt="Foto do usuário" class="usuario-foto">
                    <span class="usuario-nome"><?php echo htmlspecialchars($_SESSION['usuario_nome']); ?></span>
                </a>
                <a href="../controllers/logout.php" class="btn-login sair">Sair</a>
            </div>
        <?php else: ?>
            <a href="login.php" class="btn-login">Login</a>
        <?php endif; ?>
    </div>

    <div class="menu-toggle" id="menuToggle">
        <span></span><span></span><span></span>
    </div>
</header>


<main>
