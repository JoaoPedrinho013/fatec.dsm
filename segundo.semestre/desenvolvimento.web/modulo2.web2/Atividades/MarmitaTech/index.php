<?php
session_start();
require 'config/config.php';

$stmt_servicos = $pdo->query("SELECT id, titulo, descricao, foto, preco FROM servico ORDER BY id DESC");
$servicos = $stmt_servicos->fetchAll();

$stmt_avaliacoes = $pdo->query("SELECT id, nome, estrelas, comentario FROM avaliacao ORDER BY id DESC");
$avaliacoes = $stmt_avaliacoes->fetchAll();

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['nome'])) {
    $nome = trim($_POST['nome']);
    $email = trim($_POST['email']);
    $mensagem = trim($_POST['mensagem']);

    if (!empty($nome) && !empty($email) && !empty($mensagem) && filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $stmt = $pdo->prepare("INSERT INTO contato (nome, email, mensagem) VALUES (?, ?, ?)");
        if ($stmt->execute([$nome, $email, $mensagem])) {
            $mensagem_sucesso = "Mensagem enviada com sucesso!";
        } else {
            $mensagem_erro = "Erro ao enviar mensagem.";
        }
    } else {
        $mensagem_erro = "Por favor, preencha todos os campos corretamente.";
    }
}


$mensagem_sucesso = '';
$mensagem_erro = '';

if (isset($_GET['contato_sucesso'])) {
    $mensagem_sucesso = "Mensagem enviada com sucesso!";
} elseif (isset($_GET['contato_erro'])) {
    $mensagem_erro = "Erro ao enviar mensagem. Por favor, tente novamente.";
}
?>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="public/css/index.css">
    <link rel="icon" href="public/favicon.ico" type="image/x-icon">
    <title>Marmita Tech</title>

</head>

<body>

    <header class="header">
        <a href="#" onclick="window.scrollTo({top: 0, behavior: 'smooth'}); return false;">
            <img src="public/images/logo/logo_nome.png" alt="Marmita Tech" class="logo">
        </a>

        <div class="header-desktop">
            <nav class="navegacao">
                <a href="#sobre">Quem somos</a>
                <a href="#cardapio">Cardápio</a>
                <a href="#contato">Contato</a>
            </nav>

            <?php if (isset($_SESSION['usuario_id'])): ?>
                <?php
                $fotoUsuario = $_SESSION['usuario_foto'] ?? 'images/users/default.png';
                $caminhoFoto = "public/" . $fotoUsuario;

                if (!file_exists($caminhoFoto)) {
                    $caminhoFoto = "public/images/users/default.png";
                }
                $linkPerfil = ($_SESSION['usuario_nivel'] ?? '') === 'admin' ? 'view/dashboard-admin.php' : 'view/dashboard-user.php';
                ?>
                <div class="usuario-info">
                    <a href="<?php echo $linkPerfil; ?>" class="usuario-perfil-link">
                        <img src="<?php echo htmlspecialchars($caminhoFoto); ?>"
                            alt="Foto do usuário" class="usuario-foto">
                        <span class="usuario-nome"><?php echo htmlspecialchars($_SESSION['usuario_nome']); ?></span>
                    </a>
                    <a href="controllers/logout.php" class="btn-login sair">Sair</a>
                </div>
            <?php else: ?>
                <a href="view/login.php" class="btn-login">Login</a>
            <?php endif; ?>
        </div>

        <div class="menu-toggle" id="menuToggle">
            <span></span><span></span><span></span>
        </div>
    </header>



    <div class="menu-mobile" id="menuMobile">
        <nav class="navegacao">
            <a href="#sobre">Quem somos</a>
            <a href="#cardapio">Cardápio</a>
            <a href="#contato">Contato</a>
        </nav>
        <button type="button" class="botao">Login</button>
    </div>

    <main>
        <section class="banner">
            <div class="banner-content">
                <h1>Bem-vindo ao Marmita Tech</h1>
                <p>Descubra marmitas incríveis e ofertas especiais</p>
                <a href="view/create-user.php" class="btn-banner">Quero me inscrever</a>
            </div>
        </section>

        <section class="sobre" id="sobre">
            <h1 class="titulo">Quem somos</h1>
            <div class="sobre-content">
                <div class="sobre-texto">
                    <h3>Sua refeição perfeita, com tecnologia</h3>
                    <p>A Marmita Tech nasceu da paixão por comida de qualidade e a praticidade que a tecnologia pode proporcionar. Somos uma empresa inovadora que combina receitas tradicionais com processos modernos.</p>
                    <p>Nosso compromisso é levar até você refeições saborosas, nutritivas e preparadas com ingredientes frescos e selecionados. Cada marmita é cuidadosamente planejada por nutricionistas e preparada por chefs experientes.</p>
                    <p>Acreditamos que uma boa alimentação não precisa ser complicada. Por isso, criamos um sistema simples e eficiente que permite você receber suas marmitas no conforto do seu lar ou trabalho.</p>
                </div>
                <div class="sobre-imagem"></div>
            </div>
        </section>

        <section class="cardapio" id="cardapio">
            <h1 class="titulo">Nossos pratos</h1>
            <?php if (count($servicos) > 0): ?>
                <div class="cards-grid">
                    <?php foreach ($servicos as $servico): ?>
                        <div class="produto-card">
                            <div class="produto-imagem">
                                <?php if (!empty($servico['foto'])): ?>
                                    <img src="public/<?php echo htmlspecialchars($servico['foto']); ?>"
                                        alt="<?php echo htmlspecialchars($servico['titulo']); ?>"
                                        class="produto-img">
                                <?php else: ?>
                                    <i class="fas fa-utensils"></i>
                                <?php endif; ?>
                            </div>
                            <div class="produto-info">
                                <h3><?php echo htmlspecialchars($servico['titulo']); ?></h3>
                                <p><?php echo htmlspecialchars($servico['descricao']); ?></p>
                                <p class="preco">R$ <?php echo number_format($servico['preco'], 2, ',', '.'); ?></p>
                                <?php
                                if (isset($_SESSION['usuario_id'])) {
                                    $linkComprar = "view/checkout-servico.php?id=" . $servico['id'];
                                } else {
                                    $linkComprar = "view/login.php";
                                }
                                ?>
                                <a href="<?php echo $linkComprar; ?>" class="btn-comprar">Comprar</a>

                            </div>
                        </div>

                    <?php endforeach; ?>
                </div>
            <?php else: ?>
                <p style="text-align: center; width: 100%; padding: 2rem;">Nenhum prato disponível no momento.</p>
            <?php endif; ?>
        </section>



        <section class="cadastro" id="cadastro">
            <div class="cadastro-container">
                <h1 class="titulo">Se cadastre e receba ofertas!</h1>
                <p class="subtitulo">Receba marmitas incríveis, promoções exclusivas e novidades diretamente no seu e-mail.</p>
                <a href="view/create-user.php" class="btn-cadastro">Quero me inscrever</a>
            </div>
        </section>

        <section class="avaliacao">
            <h1 class="titulo">O que dizem da nossa Marmita Tech</h1>
            <div class="avaliacoes-container" id="avaliacoesContainer">
                <?php if (count($avaliacoes) > 0): ?>
                    <?php foreach ($avaliacoes as $avaliacao): ?>
                        <div class="avaliacao-card">
                            <div class="avaliacao-header">
                                <div class="avaliacao-avatar">
                                    <?php echo strtoupper(substr($avaliacao['nome'], 0, 1)); ?>
                                </div>
                                <div class="avaliacao-info">
                                    <h4><?php echo htmlspecialchars($avaliacao['nome']); ?></h4>
                                    <div class="estrelas">
                                        <?php
                                        $estrelas = (int)$avaliacao['estrelas'];
                                        for ($i = 1; $i <= 5; $i++):
                                        ?>
                                            <i class="fas fa-star <?php echo $i <= $estrelas ? 'star-filled' : ''; ?>"></i>
                                        <?php endfor; ?>
                                    </div>
                                </div>
                            </div>
                            <p class="avaliacao-comentario"><?php echo htmlspecialchars($avaliacao['comentario']); ?></p>
                        </div>
                    <?php endforeach; ?>
                <?php else: ?>
                    <p style="text-align: center; width: 100%; padding: 2rem;">Nenhuma avaliação disponível.</p>
                <?php endif; ?>
            </div>
        </section>
    </main>

    <footer class="footer" id="contato">
        <div class="footer-content">
            <div class="contato">
                <h2>Contato</h2>
                <p><i class="fas fa-map-marker-alt"></i> Rua dos Alfeneiros, nº 4<br>Little Whinging, Surrey</p>
                <p><i class="fas fa-phone"></i> +44 6666-62442</p>
                <p><i class="fas fa-envelope"></i> contato@marmitatech.com</p>
                <div style="margin-top: 1.5rem;">
                    <a href="https://wa.me/44666662442" target="_blank"><i class="fab fa-whatsapp"></i></a>
                    <a href="https://www.instagram.com" target="_blank"><i class="fab fa-instagram"></i></a>
                </div>
            </div>

            <div class="footer-form">
                <h2>Fale conosco</h2>
                <?php if (isset($_GET['contato_sucesso'])): ?>
                    <div class="msg-sucesso">
                        Sua mensagem foi enviada com sucesso!
                    </div>
                <?php elseif (isset($_GET['contato_erro'])): ?>
                    <div class="msg-erro">
                        Ocorreu um erro ao enviar sua mensagem. Tente novamente.
                    </div>
                <?php endif; ?>

                <form id="formContato" method="POST" action="controllers/save-contato.php">
                    <div class="form-group">
                        <input type="text" name="nome" placeholder="Seu nome" required>
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" placeholder="Seu e-mail" required>
                    </div>
                    <div class="form-group">
                        <textarea name="mensagem" placeholder="Sua mensagem" required></textarea>
                    </div>
                    <button type="submit" class="form-btn">Enviar Mensagem</button>
                </form>
            </div>
        </div>

        <p class="direitos">Marmita Tech &copy; Todos os direitos reservados</p>
    </footer>


    <script src="public/script/index.js" defer></script>

</body>

</html>