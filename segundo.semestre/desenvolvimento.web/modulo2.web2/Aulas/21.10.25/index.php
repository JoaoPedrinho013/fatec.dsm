<?php 
require 'config.php';

$query = $pdo->query("SELECT * FROM avaliacao");
$avaliacoes = $query->fetchAll();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP + MySQL</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Avalições</h1>
    <a href="create.php">Nova Avalição</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Estrelas</th>
            <th>Comentário</th>
            <th>Ações</th>
        </tr>
        <?php foreach($avaliacoes as $a): ?>
            <tr>
                <td><?php echo $a['id'] ?></td>
                <td><?php echo $a['nome'] ?></td>
                <td><?php echo $a['estrelas'] ?></td>
                <td><?php echo $a['comentario'] ?></td>
                <td>
                    <a href="edit.php?id=<?php echo $a['id'] ?>">Editar</a>
                    |
                    <a href="delete.php?id=<?php echo $a['id']; ?>">Excluir</a>
                </td>
            </tr>
        <?php endforeach ?>
    </table>
</body>
</html>