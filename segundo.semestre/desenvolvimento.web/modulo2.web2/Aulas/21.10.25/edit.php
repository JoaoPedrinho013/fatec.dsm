<?php
require 'config.php';

$id = $_GET['id'];

if (!$id) {
    header("Location: index.php");
    exit;
}

$query = $pdo->prepare("SELECT * FROM avaliacao WHERE id=?");
$query->execute([$id]);
$avaliacao = $query->fetch();

if(!$avaliacao) {
    echo "Atualização não encontrada.";
    exit;
}

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $query = $pdo->prepare("UPDATE avaliacao SET nome = ?, estrelas = ?, comentario = ? WHERE id = ?");

    $query->execute([$_POST['nome'], (int)$_POST['estrelas'], $_POST['comentario'], $id]);

    header("Location: index.php");
    exit;
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atualizar Avaliação</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <h1>Atualizar Avaliação</h1>
    <form action="#" method="post">
        <label>Nome: </label>
        <input type="text" value="<?php echo $avaliacao['nome'] ?>" name="nome" required><br><br>
        <label>Estrelas (1 a 5): </label>
        <input type="number" value="<?php echo $avaliacao['estrelas'] ?>" name="estrelas" min="1" max="5" required> <br><br>
        <label>Comentario: </label> <br>
        <textarea name="comentario" rows="4" cols="50" required>
            <?php echo $avaliacao['comentario'] ?>
        </textarea> <br><br>
        <input type="submit" value="Salvar"> <br><br>
    </form>
    <p><a href="index.php" class="voltar">Voltar</a></p>
</body>

</html>