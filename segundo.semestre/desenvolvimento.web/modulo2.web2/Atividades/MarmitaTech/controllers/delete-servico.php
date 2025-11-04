<?php
require '../config/config.php';

if (!isset($_GET['id']) || empty($_GET['id'])) {
    die("ID do serviço não fornecido.");
}

$id = (int)$_GET['id'];

$sql = "SELECT foto FROM servico WHERE id = :id";
$stmt = $pdo->prepare($sql);
$stmt->execute([':id' => $id]);
$servico = $stmt->fetch(PDO::FETCH_ASSOC);

if (!$servico) {
    die("Serviço não encontrado.");
}

if (!empty($servico['foto']) && file_exists('../public/' . $servico['foto'])) {
    unlink('../public/' . $servico['foto']);
}

$sqlDelete = "DELETE FROM servico WHERE id = :id";
$stmtDelete = $pdo->prepare($sqlDelete);
$stmtDelete->execute([':id' => $id]);

header("Location: ../view/dashboard-admin.php#tabela-servicos");
exit;
?>
