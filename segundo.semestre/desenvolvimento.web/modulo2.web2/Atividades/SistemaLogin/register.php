<?php
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $nome  = $_POST["nome"];
    $email = $_POST["email"];
    $senha = $_POST["senha"];

    if (!empty($nome) && !empty($email) && !empty($senha)) {
        $arquivo = fopen("data.txt", "a");

        $linha = $nome . " || " . $email . " || " . $senha;
        fwrite($arquivo, $linha . "\n");
        fclose($arquivo);

        setcookie("email", $email, time() + 3600);

        header("Location: login.php");
    } else {
        $erro = "Preencha todos os campos!";
    }
}
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Cadastro</h2>

        <?php if (isset($erro)) echo "<div class='error'>$erro</div>"; ?>

        <form method="post" action="">
            
            <input type="text" name="nome" placeholder="Nome" required>

            
            <input type="email" name="email" placeholder="Email" required>

            
            <input type="password" name="senha" placeholder="Senha" required>

            <button type="submit" class="btn-primary">Salvar</button>
        </form>
    </div>
</body>
</html>