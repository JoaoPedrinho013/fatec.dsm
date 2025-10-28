<?php
require 'config.php';

$query = $pdo->query("SELECT * FROM avaliacao");
$avaliacoes = $query->fetchAll();
?>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Marmita Tech</title>
</head>

<body>
    <header class="header">
        <a href="#"><h1>Marmita Tech</h1></a>
        <nav class="navegacao">
            <a href="#">Quem somos</a>
            <a href="#">Cardápio</a>
            <a href="#">Contato</a>
        </nav>
        <button type="submit" class="botao_login">Login</button>
    </header>
    <main>
        <section class="banner">
            <div class="banner-content">
                <h1>Bem-vindo ao Marmita Tech</h1>
                <p>Descubra marmitas incríveis e ofertas especiais</p>
                <button>Quero me inscrever</button>
            </div>
        </section>

        <section class="sobre">
            <h1 class="titulo">Quem somos</h1>
        </section>
        <section class="cardapio">
            <h1 class="titulo">Nossos pratos mais vendidos</h1>
            
        </section>
        <section class="cadastro">
            <h1 class="titulo">Se cadastre e receba ofertas!</h1>
            
        </section>
        <section class="avaliacao">
            <h1 class="titulo">O que dizem da nossa Marmita Tech</h1>

        </section>
    </main>
    <footer class="footer">
        <form action="#" method="POST"></form>
        <section>
            <p class="direitos">Marmita Tech &copy; Todos os direitos reservados</p>
        </section>
    </footer>
</body>

</html>