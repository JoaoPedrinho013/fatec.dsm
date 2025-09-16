<?php
require_once("funcoes.php");
// saudacao("Maria");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if(!empty($_POST['txtnome']) && 
    !empty($_POST['txtn1']) && 
    !empty($_POST['txtn2'])) {

    $nome = $_POST["txtnome"];
    $nota1 = $_POST["txtn1"];
    $nota2 = $_POST["txtn2"];

    saudacao($nome);

    $media = media($nota1, $nota2);
    echo "<br/>Média: $media";

    }else{
        erro();
    }

} 

?>