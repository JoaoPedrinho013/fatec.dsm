<?php
require '../config/config.php';

if (!isset($_GET['id']) || empty($_GET['id'])) {
    die("ID do usuário não fornecido.");
}

$id = (int)$_GET['id'];

$sql = "SELECT foto FROM usuario WHERE id = :id";
$stmt = $pdo->prepare($sql);
$stmt->execute([':id' => $id]);
$usuario = $stmt->fetch(PDO::FETCH_ASSOC);

if (!$usuario) {
    die("Usuário não encontrado.");
}

if (!empty($usuario['foto']) && basename($usuario['foto']) !== 'default.png') {
    $caminhoFoto = '../public/' . $usuario['foto'];

  
    if (file_exists($caminhoFoto)) {
        unlink($caminhoFoto);
    }
}


$sqlDelete = "DELETE FROM usuario WHERE id = :id";
$stmtDelete = $pdo->prepare($sqlDelete);
$stmtDelete->execute([':id' => $id]);

header("Location: ../view/dashboard-admin.php#tabela-usuarios");
exit;
?>
