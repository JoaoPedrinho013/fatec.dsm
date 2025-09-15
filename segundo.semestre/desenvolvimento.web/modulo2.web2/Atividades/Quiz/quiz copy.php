<?php
include 'scripts/temasPerguntas.php';
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .form { margin: 20px; padding: 15px; border: 1px solid #ccc; width: 400px; }
        .botao { margin-top: 10px; display: block; }
    </style>
</head>

<body>
    <h1>Quiz Naruto & Boruto</h1>

    <?php
    // Verifica se já foi escolhido um tema
    if (!isset($_POST['temaSelecionado'])) {
        // MOSTRA O FORM DE TEMAS
        ?>
        <form method="post" class="form">
            <label for="tema">Escolha um tema: </label>
            <select name="temaSelecionado" id="tema">
                <?php
                foreach ($quiz as $tema => $perguntas) {
                    echo "<option value=\"$tema\">$tema</option>";
                }
                ?>
            </select>
            <input type="submit" value="Selecionar Tema" class="botao">
        </form>
        <?php
    } else {
        // MOSTRA A 1ª PERGUNTA DO TEMA
        $tema = $_POST['temaSelecionado'];
        $primeiraPergunta = $quiz[$tema][0];
        ?>
        <form method="post" class="form">
            <h2><?php echo $tema; ?></h2>
            <p><strong><?php echo $primeiraPergunta['pergunta']; ?></strong></p>

            <?php foreach ($primeiraPergunta['opcoes'] as $opcao): ?>
                <label>
                    <input type="radio" name="resposta" value="<?php echo $opcao; ?>">
                    <?php echo $opcao; ?>
                </label><br>
            <?php endforeach; ?>

            <br>
            <input type="submit" value="Próximo" class="botao">
        </form>

        <!-- Botão para voltar a escolha de tema -->
        <form method="post" class="form">
            <input type="submit" value="Voltar para escolha de tema" class="botao">
        </form>
        <?php
    }
    ?>
</body>
</html>
