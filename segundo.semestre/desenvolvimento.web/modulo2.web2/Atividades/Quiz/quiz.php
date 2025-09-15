<?php
$quiz = [
    "Naruto Clássico" => [
        [
            'pergunta' => 'Quem é o sensei do Time 7?',
            'opcoes' => ['Kakashi Hatake', 'Might Guy', 'Jiraiya', 'Asuma Sarutobi'],
            'resposta' => 'Kakashi Hatake'
        ],
        [
            'pergunta' => 'Qual é a técnica mais famosa de Naruto?',
            'opcoes' => ['Kage Bunshin no Jutsu', 'Kagemane no Jutsu', 'Henge no Jutsu', 'Katon: Goukakyuu no Jutsu'],
            'resposta' => 'Kage Bunshin no Jutsu'
        ],
        [
            'pergunta' => 'Qual é a missão inicial do Time 7 no início da série?',
            'opcoes' => ['Escoltar um construtor do Pais das Ondas', 'Capturar um criminoso fugitivo', 'Recuperar um pergaminho roubado', 'Treinar na floresta de Konoha'],
            'resposta' => 'Escoltar um construtor do Pais das Ondas'
        ],
        [
            'pergunta' => 'Quem é o mestre de Rock Lee?',
            'opcoes' => ['Kakashi Hatake', 'Might Guy', 'Jiraiya', 'Asuma Sarutobi'],
            'resposta' => 'Might Guy'
        ],
        [
            'pergunta' => 'Qual é o principal objetivo da Akatsuki durante Naruto Clássico?',
            'opcoes' => ['Destruir Konoha', 'Roubar jutsus proibidos', 'Capturar todas as Bestas com Cauda', 'Treinar ninjas renegados'],
            'resposta' => 'Capturar todas as Bestas com Cauda'
        ]
    ],

    "Naruto Shippuden" => [
        [
            'pergunta' => 'Quantos corpos Pain realmente possui?',
            'opcoes' => ['6', '5', '9', '8'],
            'resposta_correta' => '6'
        ],
        [
            'pergunta' => 'Qual é a verdadeira identidade de Tobi?',
            'opcoes' => ['Obito Uchiha', 'Madara Uchiha', 'Sasuke Uchiha', 'Kakashi'],
            'resposta_correta' => 'Obito Uchiha'
        ],
        [
            'pergunta' => 'Qual jinchuuriki possui duas caudas e é amigo de Killer Bee?',
            'opcoes' => ['Yugito Nii', 'Gaara', 'Roshi', 'Fuu'],
            'resposta_correta' => 'Yugito Nii'
        ],
        [
            'pergunta' => 'Qual é a técnica proibida que Orochimaru queria usar em Sasuke?',
            'opcoes' => ['Edo Tensei', 'Fuinjutsu de Imortalidade', 'Selo Cursed', 'Kinjutsu da Vida Eterna'],
            'resposta_correta' => 'Fuinjutsu de Imortalidade'
        ],
        [
            'pergunta' => 'Quem salvou os Cinco Kages depois de serem derrotados por Madara?',
            'opcoes' => ['Orochimaru', 'Naruto e Sasuke', 'Tsunade', 'Kakashi'],
            'resposta_correta' => 'Orochimaru'
        ]
    ],

    "Boruto: Two Blue Vortex" => [
        [
            'pergunta' => ' Quem é Kawaki? ',
            'opcoes' => ['Encarnação de Isshiki Õtsutsuki', 'Filho de Naruto com Hinata', 'Nenhuma das opções', 'Futuro receptáculo de Isshiki Õtsutsuki'],
            'resposta_correta' => 'Futuro receptáculo de Isshiki Õtsutsuki'
        ],
        [
            'pergunta' => 'Quem é o atual hokage em Boruto? ',
            'opcoes' => ['Tsunade', 'Kakashi', 'Shikamaru', 'Konohamaru'],
            'resposta_correta' => 'Shikamaru'
        ],
        [
            'pergunta' => 'Quem é o principal suspeito de matar Naruto de acordo com os moradores da Vila da Folha? ',
            'opcoes' => ['Kawaki', 'Boruto', 'Sasuke', 'Momoshiki'],
            'resposta_correta' => 'Boruto'
        ],
        [
            'pergunta' => 'Quem fez o Sasuke perder o Rinnegan? ',
            'opcoes' => ['Momoshiki Õtsutsuki', 'Boruto Uzumaki', 'Ele ainda possui o Rinnegan', 'Isshiki Õtsutsuki'],
            'resposta_correta' => 'Momoshiki Õtsutsuki'
        ],
        [
            'pergunta' => 'Qual Shinju foi derrotado pela Sarada Uchiha?',
            'opcoes' => ['Juura', 'Hidari', 'Ryuu', 'Matsuri'],
            'resposta_correta' => 'Ryuu'
        ]
    ]
];
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz</title>
    <link rel="stylesheet" href="styles/quiz.css">
</head>

<body>
    <h1 class="titulo">Quiz Naruto & Boruto</h1>
    <?php
    if (!isset($_POST['temaSelecionado'])) {
    ?>
        <form method="post" class="form">
            <label for="tema">Escolha um tema: </label>
            <select name="temaSelecionado" id="tema" class="escolhaTema">
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
       $tema = $_POST['temaSelecionado'];
        $indicePergunta = isset($_POST['indicePergunta']) ? (int)$_POST['indicePergunta'] : 0;

        // Se ainda houver perguntas
        if ($indicePergunta < count($quiz[$tema])) {
            $perguntaAtual = $quiz[$tema][$indicePergunta];
            ?>
            <form method="post">
                <h2><?php echo $tema; ?></h2>
                <p><strong><?php echo $perguntaAtual['pergunta']; ?></strong></p>

                <?php foreach ($perguntaAtual['opcoes'] as $opcao): ?>
                    <label>
                        <input type="radio" name="resposta" value="<?php echo $opcao; ?>">
                        <?php echo $opcao; ?>
                    </label><br>
                <?php endforeach; ?>

                <input type="hidden" name="temaSelecionado" value="<?php echo $tema; ?>">
                <input type="hidden" name="indicePergunta" value="<?php echo $indicePergunta + 1; ?>">
                <input type="submit" value="Próximo">
            </form>

            <form method="post">
                <input type="submit" value="Voltar para escolha de tema">
            </form>
            <?php
        } else {
            // Quando acaba as perguntas
            echo "<h2>Fim do quiz em <em>$tema</em>!</h2>";
            ?>
            <form method="post">
                <input type="submit" value="Jogar novamente">
            </form>
            <?php
        }
    }
    ?>
</body>
</html>

</body>

</html>