<?php
$usuarios = [
    "0" => "Aluno",
    "1" => "Professor"
];
$cursos = ["ADS", "COMEX", "DSM", "GE", "PQ"];

$periodos = array("Matutino", "Vespertino", "Noturno");

$fotos = array("https://i0.wp.com/borutoexplorer.com.br/wp-content/uploads/2023/09/3df0dee9-b17f-4253-aeee-8c02b1aeb895.jpeg?resize=357%2C512&ssl=1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQapze4UtKrG0vmujhHOw7DRe_3EaQDxqad2g&s", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRY3ygCFpm0CTbZpnc-s6UXW-4TUhLCmpbGQ&s");
?>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Select</title>
    <style>
        body{
            background-color: #282828ff;
            color:white;
            margin:5% 10%;
        }
        .galeria {
            text-align: center;
        }
        .galeria > img {
            margin: 2%;
        }
    </style>
</head>

<body>
    <form action="#" method="post">
        <label>Usuário:</label>
        <?php
            foreach ($usuarios as $users) {
                echo "<input type='radio' name='usuario' value='$users'>$users";
            }
        ?>

<br>
<br>
<label>Curso:</label>
<select name="sltcurso">
    <?php
            foreach ($cursos as $curso) {
                echo "<option value='$curso'>$curso</option>";
            }
            ?>
        </select>
        
        <br>
        <br>
        <label>Periodo:</label>
        <?php 
            foreach ($periodos as $periodo) {  
                echo "<input type='checkbox' name='periodos[]' value='$periodo'>$periodo";
            }
            ?>
            
            <br>
            <br>
            <input type="submit">
            <hr>
            
        </form>
        
        
        <?php
    if ($_SERVER["REQUEST_METHOD"] === "POST") {
        $usuario = $_POST['usuario'];
        $curso = $_POST['sltcurso'];
        $periodo = $_POST['periodos'];
        
        echo "$usuario Matriculado em $curso";
        echo "<br>Periodos: ";
        echo "<ul>";
        foreach ($periodo as $p) {
            echo "<li> $p</li>";
        }
        echo "</ul>";
        
    }
    ?>
    <hr>
    <h2>Galeria</h2>
    <div class="galeria">
            <?php 
                foreach ($fotos as $f) {   
                    echo "<img src='$f' width='20%'>";
                 }
            ?>
            </div>
            <hr>
</body>

</html>