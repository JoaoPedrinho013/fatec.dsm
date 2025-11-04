<?php
require '../config/config.php';
session_start();

if (!isset($_SESSION['usuario_id']) || $_SESSION['usuario_nivel'] !== 'admin') {
    header('Location: ../index.php');
    exit;
}

$query = $pdo->prepare("SELECT nome, email, foto FROM usuario WHERE id = ?");
$query->execute([$_SESSION['usuario_id']]);
$usuarioLogado = $query->fetch();

$avaliacoes = $pdo->query("SELECT * FROM avaliacao")->fetchAll(PDO::FETCH_ASSOC);
$servicos   = $pdo->query("SELECT * FROM servico")->fetchAll(PDO::FETCH_ASSOC);
$contatos   = $pdo->query("SELECT * FROM contato ORDER BY data_envio DESC")->fetchAll(PDO::FETCH_ASSOC);
$usuarios   = $pdo->query("SELECT id, nome, email, nivel, foto FROM usuario")->fetchAll(PDO::FETCH_ASSOC);

$titulo_pagina = "Dashboard Admin";
$css_pagina = "../public/css/dashboard-admin.css";

include 'header.php';
?>

<main class="main_dashboard_admin">
    <h1 class="dashboard-title">Dashboard Admin</h1>

    <div class="dashboard-grid">
        <a href="#" class="dashboard-box" data-target="tabela-avaliacoes">Avaliações</a>
        <a href="#" class="dashboard-box" data-target="tabela-servicos">Serviços</a>
        <a href="#" class="dashboard-box" data-target="tabela-contato">Contato</a>
        <a href="#" class="dashboard-box" data-target="tabela-usuarios">Usuários</a>
    </div>

    <div id="tabela-avaliacoes" class="tabela-dashboard" style="display:none;">
        <h2>Lista de Avaliações</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Estrelas</th>
                    <th>Comentário</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($avaliacoes as $avaliacao): ?>
                <tr>
                    <td><?= $avaliacao['id'] ?></td>
                    <td><?= htmlspecialchars($avaliacao['nome']) ?></td>
                    <td>
                        <?php for($i=0; $i<$avaliacao['estrelas']; $i++): ?>
                            <i class="fas fa-star" style="color: #FFD700;"></i>
                        <?php endfor; ?>
                    </td>
                    <td class="td-truncate" title="<?= htmlspecialchars($avaliacao['comentario']) ?>">
                        <?= htmlspecialchars($avaliacao['comentario']) ?>
                    </td>
                    <td class="td-acoes">
                        <a href="../controllers/delete-avaliacao.php?id=<?= $avaliacao['id'] ?>" class="button delete" onclick="return confirm('Tem certeza que deseja excluir esta avaliação?')">
                            Excluir
                        </a>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>

    <div id="tabela-servicos" class="tabela-dashboard" style="display:none;">
        <h2>Lista de Serviços</h2>
        <div class="tabela-actions" style="text-align:right; margin-bottom:1rem;">
            <a href="create-servico.php" class="button add black"><i class="fas fa-plus"></i> Adicionar Serviço</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th><th>Título</th><th>Descrição</th><th>Preço</th><th>Imagem</th><th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($servicos as $servico): ?>
                <tr>
                    <td><?= $servico['id'] ?></td>
                    <td><?= htmlspecialchars($servico['titulo']) ?></td>
                    <td class="td-truncate" title="<?= htmlspecialchars($servico['descricao']) ?>"><?= htmlspecialchars($servico['descricao']) ?></td>
                    <td class="td-preco">R$ <?= number_format($servico['preco'],2,',','.') ?></td>
                    <td>
                        <?php if(!empty($servico['foto'])): ?>
                            <img src="../public/<?= $servico['foto'] ?>" alt="<?= htmlspecialchars($servico['titulo']) ?>" class="img-miniatura">
                        <?php else: ?>Sem imagem<?php endif; ?>
                    </td>
                    <td class="td-acoes">
                        <a href="editar-servico.php?id=<?= $servico['id'] ?>" class="button edit">Editar</a>
                        <a href="../controllers/delete-servico.php?id=<?= $servico['id'] ?>" class="button delete" onclick="return confirm('Tem certeza que deseja excluir este serviço?')">Excluir</a>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>

    <div id="tabela-usuarios" class="tabela-dashboard" style="display:none;">
        <h2>Lista de Usuários</h2>
        <div class="tabela-actions">
            <a href="create-user-admin.php" class="button add"><i class="fas fa-plus"></i> Adicionar Usuário</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th><th>Foto</th><th>Nome</th><th>Email</th><th>Nível</th><th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($usuarios as $usuarioItem): ?>
                <tr>
                    <td><?= $usuarioItem['id'] ?></td>
                    <td>
                        <?php $foto = !empty($usuarioItem['foto']) ? $usuarioItem['foto'] : 'images/users/default.png'; ?>
                        <img src="../public/<?= $foto ?>" alt="<?= htmlspecialchars($usuarioItem['nome']) ?>" class="img-miniatura">
                    </td>
                    <td><?= htmlspecialchars($usuarioItem['nome']) ?></td>
                    <td><?= htmlspecialchars($usuarioItem['email']) ?></td>
                    <td><?= ucfirst($usuarioItem['nivel']) ?></td>
                    <td class="td-acoes">
                        <a href="editar-user.php?id=<?= $usuarioItem['id'] ?>" class="button edit">Editar</a>
                        <a href="../controllers/delete-user.php?id=<?= $usuarioItem['id'] ?>" class="button delete" onclick="return confirm('Tem certeza que deseja excluir este usuário?')">Excluir</a>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>

    <div id="tabela-contato" class="tabela-dashboard" style="display:none;">
        <h2>Mensagens de Contato</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th><th>Nome</th><th>Email</th><th>Mensagem</th><th>Data Envio</th><th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($contatos as $contato): ?>
                <tr>
                    <td><?= $contato['id'] ?></td>
                    <td><?= htmlspecialchars($contato['nome']) ?></td>
                    <td><?= htmlspecialchars($contato['email']) ?></td>
                    <td class="td-truncate" title="<?= htmlspecialchars($contato['mensagem']) ?>"><?= htmlspecialchars($contato['mensagem']) ?></td>
                    <td><?= date('d/m/Y H:i', strtotime($contato['data_envio'])) ?></td>
                    <td class="td-acoes">
                        <a href="../controllers/delete-contato.php?id=<?= $contato['id'] ?>" class="button delete" onclick="return confirm('Tem certeza que deseja excluir esta mensagem?')">Excluir</a>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>

</main>

<script>
    const dashboardBoxes = document.querySelectorAll('.dashboard-box');
    const tabelas = document.querySelectorAll('.tabela-dashboard');

    dashboardBoxes.forEach(box => {
        box.addEventListener('click', function(e){
            e.preventDefault();
            const targetId = box.getAttribute('data-target');

            tabelas.forEach(t => {
                if(t.id === targetId){
                    t.style.display = t.style.display === 'block' ? 'none' : 'block';
                    if(t.style.display === 'block') t.scrollIntoView({behavior:'smooth'});
                } else {
                    t.style.display = 'none';
                }
            });
        });
    });
</script>
<script src="public/script/index.js" defer></script>

<?php
include 'footer.php';
?>
