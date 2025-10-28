<?php 
require 'config.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    
    if(isset($_FILES['foto']) && $_FILES['foto']['error'] === UPLOAD_ERR_OK) {
        $extensao = pathinfo($_FILES['foto']['name'], PATHINFO_EXTENSION);
        $nome_arquivo = uniqid('user_', true) . '.' . $extensao;
        $caminho_foto = 'images/users' . $nome_arquivo;

        if(move_uploaded_file($_FILES['foto']['tmp_name'], $caminho_foto)) {
            echo "Erro ao salvar o arquivo.";
        }
        
    } else {
       echo "Erro no upload da foto.";
    }

    $senha_hash = password_hash($_POST['senha'], PASSWORD_DEFAULT);



    $query = $pdo->prepare("INSERT INTO usuario (nome, email, senha, foto) VALUES (?, ?, ?, ?)");

    $query->execute([
        $_POST['nome'],
        $_POST['email'],
        $senha_hash, $caminho_foto
    ]);

    header('Location: login.php');
    exit;
}
?>






<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Novo usuário</title>
    
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Novo usuário</h1>
    <form action="#" method="post" enctype="multipart/form-data">
    Nome: <input type="text" name="nome" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Senha: <input type="password" name="senha" required><br><br>
    Foto: <input type="file" name="foto" accept="image/*"><br><br>
    <input type="submit" value="Salvar">
</form>
<p>Ja tem cadastro?<a href="login.php">Clique Aqui</a></p>
<p><a href="index.php">Inicio</a></p>
</body>
</html>