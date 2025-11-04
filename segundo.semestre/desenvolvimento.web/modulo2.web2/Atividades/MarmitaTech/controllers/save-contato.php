<?php
require '../config/config.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nome = trim($_POST['nome'] ?? '');
    $email = trim($_POST['email'] ?? '');
    $mensagem = trim($_POST['mensagem'] ?? '');


    if (!empty($nome) && !empty($email) && !empty($mensagem) && filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $stmt = $pdo->prepare("INSERT INTO contato (nome, email, mensagem) VALUES (?, ?, ?)");

        if ($stmt->execute([$nome, $email, $mensagem])) {

            header('Location: ../index.php?contato_sucesso=1#contato');
            exit;
        } else {
            header('Location: ../index.php?contato_erro=1#contato');
            exit;
        }
    } else {
        header('Location: ../index.php?contato_erro=1');
        exit;
    }
} else {
    header('Location: ../index.php');
    exit;
}
