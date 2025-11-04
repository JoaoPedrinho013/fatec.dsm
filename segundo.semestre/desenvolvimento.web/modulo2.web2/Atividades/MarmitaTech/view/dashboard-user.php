<?php
require '../config/config.php';
session_start();

if (!isset($_SESSION['usuario_id'])) {
    header('Location: ../index.php');
    exit;
}

$usuarioId = $_SESSION['usuario_id'];

if (isset($_GET['delete_id'])) {
    $deleteStmt = $pdo->prepare("DELETE FROM avaliacao WHERE id = ? AND (nome = ? OR nome = ?)");
    $deleteStmt->execute([$_GET['delete_id'], $_SESSION['usuario_nome'], $_SESSION['usuario_email']]);
    header("Location: dashboard-user.php");
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['edit_id'])) {
    $editStmt = $pdo->prepare("UPDATE avaliacao SET estrelas = ?, comentario = ? WHERE id = ? AND (nome = ? OR nome = ?)");
    $editStmt->execute([
        (int)$_POST['estrelas'], 
        trim($_POST['comentario']), 
        (int)$_POST['edit_id'], 
        $_SESSION['usuario_nome'], 
        $_SESSION['usuario_email']
    ]);
    header("Location: dashboard-user.php");
    exit;
}

$query = $pdo->prepare("SELECT id, nome, email, foto FROM usuario WHERE id = ?");
$query->execute([$usuarioId]);
$usuarioLogado = $query->fetch();

$avaliacoes = $pdo->prepare("SELECT * FROM avaliacao WHERE nome = ? OR nome = ?");
$avaliacoes->execute([$usuarioLogado['nome'], $usuarioLogado['email']]);
$avaliacoes = $avaliacoes->fetchAll(PDO::FETCH_ASSOC);

$titulo_pagina = "Dashboard Usuário";
$css_pagina = "../public/css/dashboard-user.css";

include 'header.php';
?>

<main class="main_dashboard_user">
    <h1 class="dashboard-user-title">Bem-vindo, <?= htmlspecialchars($usuarioLogado['nome']) ?></h1>

    <div class="dashboard-user-info">
        <img src="../public/<?= !empty($usuarioLogado['foto']) ? $usuarioLogado['foto'] : 'images/users/default.png' ?>" 
             alt="<?= htmlspecialchars($usuarioLogado['nome']) ?>" 
             class="dashboard-user-foto">
        <p><strong>Nome:</strong> <?= htmlspecialchars($usuarioLogado['nome']) ?></p>
        <p><strong>Email:</strong> <?= htmlspecialchars($usuarioLogado['email']) ?></p>

        <a href="editar-user.php?id=<?= $usuarioLogado['id'] ?>" class="dashboard-user-button dashboard-user-button-edit">
            Editar Perfil
        </a>
    </div>

    <div id="tabela-avaliacoes-user" class="dashboard-user-tabela">
        <h2>Minhas Avaliações</h2>
        <?php if(count($avaliacoes) > 0): ?>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Estrelas</th>
                    <th>Comentário</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($avaliacoes as $avaliacao): ?>
                <tr>
                    <td><?= $avaliacao['id'] ?></td>
                    <td>
                        <?php for($i=0; $i<$avaliacao['estrelas']; $i++): ?>
                            <i class="fas fa-star dashboard-user-star"></i>
                        <?php endfor; ?>
                    </td>
                    <td class="dashboard-user-td-truncate" title="<?= htmlspecialchars($avaliacao['comentario']) ?>">
                        <?= htmlspecialchars($avaliacao['comentario']) ?>
                    </td>
                    <td>
                        <button class="dashboard-user-button dashboard-user-button-edit" onclick="showEditForm(<?= $avaliacao['id'] ?>, <?= $avaliacao['estrelas'] ?>, '<?= htmlspecialchars(addslashes($avaliacao['comentario'])) ?>')">Editar</button>

                        <a href="?delete_id=<?= $avaliacao['id'] ?>" class="dashboard-user-button dashboard-user-button-delete" onclick="return confirm('Tem certeza que deseja excluir esta avaliação?')">
                            Excluir
                        </a>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
        <?php else: ?>
            <p>Você ainda não enviou avaliações.</p>
        <?php endif; ?>
    </div>

    <div id="edit-form-container" style="display:none; margin-top:2rem;">
        <h2>Editar Avaliação</h2>
        <form method="POST" id="edit-form">
            <input type="hidden" name="edit_id" id="edit_id">
            <label for="estrelas">Estrelas:</label>
            <select name="estrelas" id="edit_estrelas" required>
                <option value="1">⭐</option>
                <option value="2">⭐⭐</option>
                <option value="3">⭐⭐⭐</option>
                <option value="4">⭐⭐⭐⭐</option>
                <option value="5">⭐⭐⭐⭐⭐</option>
            </select>

            <label for="comentario">Comentário:</label>
            <textarea name="comentario" id="edit_comentario" required></textarea>

            <button type="submit" class="dashboard-user-button dashboard-user-button-save">Salvar Alterações</button>
            <button type="button" class="dashboard-user-button dashboard-user-button-cancel" onclick="hideEditForm()">Cancelar</button>
        </form>
    </div>
</main>

<script>
function showEditForm(id, estrelas, comentario) {
    document.getElementById('edit-form-container').style.display = 'block';
    document.getElementById('edit_id').value = id;
    document.getElementById('edit_estrelas').value = estrelas;
    document.getElementById('edit_comentario').value = comentario;
    window.scrollTo({top: document.getElementById('edit-form-container').offsetTop - 20, behavior: 'smooth'});
}

function hideEditForm() {
    document.getElementById('edit-form-container').style.display = 'none';
}
</script>

<?php
include 'footer.php';
?>
