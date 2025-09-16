<?php
require_once 'scripts/temasPerguntas.php';
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/quiz.css?v=<?= time() ?>">
    <title>Quiz Naruto & Boruto</title>
</head>
<body>
    <h1 class="titulo">Quiz Naruto & Boruto</h1>

    <?php
    $etapa = $_POST['etapa'] ?? 'tema';

    if ($etapa === 'tema') {
        echo '<form method="POST" class="form">';
        echo '<label class="tema">Escolha o tema: </label>';
        echo '<select name="temas" class="select" required>';
        foreach ($quiz as $tema => $perguntas) {
            echo "<option value='".htmlspecialchars($tema)."'>".htmlspecialchars($tema)."</option>";
        }
        echo '</select>';
        echo '<input type="hidden" name="etapa" value="perguntas">';
        echo '<input type="submit" value="Iniciar Quiz" class="botao">';
        echo '</form>';
    }

    elseif ($etapa === 'perguntas' && isset($_POST['temas'])) {
        $temaEscolhido = $_POST['temas'];
        echo '<form method="POST" class="form">';
        echo "<h2 class='tema'>Tema escolhido: ".htmlspecialchars($temaEscolhido)."</h2>";
        echo "<div class='caixa-perguntas'>";
        foreach ($quiz[$temaEscolhido] as $index => $pergunta) {
            echo "<div class='pergunta'>";
            echo "<h3>Pergunta ".($index + 1).": ".htmlspecialchars($pergunta['pergunta'])."</h3>";
            echo "<ul>";
            foreach ($pergunta['opcoes'] as $i => $opcao) {
                $required = $i === 0 ? "required" : "";
                echo "<li><input type='radio' name='pergunta_$index' value='".htmlspecialchars($opcao)."' $required> ".htmlspecialchars($opcao)."</li>";
            }
            echo "</ul>";
            echo "</div>";
        }
        echo "</div>";
        echo "<input type='hidden' name='temas' value='".htmlspecialchars($temaEscolhido)."'>";
        echo '<input type="hidden" name="etapa" value="resultado">';
        echo '<input type="submit" value="Enviar Respostas" class="botao">';
        echo '</form>';
    }

    elseif ($etapa === 'resultado' && isset($_POST['temas'])) {
        $temaEscolhido = $_POST['temas'];
        $pontuacao = 0;
        foreach ($quiz[$temaEscolhido] as $index => $pergunta) {
            if (!isset($pergunta['resposta'])) continue;
            if (isset($_POST["pergunta_$index"]) && $_POST["pergunta_$index"] === $pergunta['resposta']) {
                $pontuacao++;
            }
        }
        echo '<form method="POST" class="form">';
        echo "<h2 class='tema'>Resultado do tema: ".htmlspecialchars($temaEscolhido)."</h2>";
        echo "<h3>Sua pontuação: $pontuacao / ".count($quiz[$temaEscolhido])."</h3>";
        echo '<input type="hidden" name="etapa" value="tema">';
        echo '<input type="submit" value="Fazer novamente" class="botao">';
        echo '</form>';
    }
    ?>
</body>
</html>
