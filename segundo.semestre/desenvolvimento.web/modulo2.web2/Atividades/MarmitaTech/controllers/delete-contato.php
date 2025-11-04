<?php
require '../config/config.php';

if (!isset($_GET['id']) || empty($_GET['id'])) {
    die("ID da mensagem não fornecido.");
}

$id = (int)$_GET['id'];

$sql = "DELETE FROM contato WHERE id = :id";
$stmt = $pdo->prepare($sql);
$stmt->execute([':id' => $id]);

header("Location: ../view/dashboard-admin.php#tabela-contato");
exit;
?>
