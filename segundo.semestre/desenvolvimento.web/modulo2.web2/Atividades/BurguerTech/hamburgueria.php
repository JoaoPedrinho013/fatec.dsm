<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hamburgueria Tech</title>

    <link rel="stylesheet" href="hamburgueria.css">
</head>

<body>
    <form action="#" method="post" class="form">
        <h1>BurguerTech</h1>
        <h2>Novo Pedido</h2>
        <label for="">Seu nome: </label>
        <input type="text" name="txtnome">

        <label for="">Escolha seu lanche</label>
        <select name="escolhaLanche" id="">
            <option value="30">X-Tudo - R$30,00</option>
            <option value="28">X-Bacon - R$28,00</option>
            <option value="25">X-Salada - $25,00</option>
            <option value="20">Tradicional - R$20,00</option>
        </select>

        <label for="">Fritas: </label>
        <p><input type="radio" name="rdfritas" value="nao">Não</p>
        <p><input type="radio" name="rdfritas" value="15">Sim (+ R$15,00)</p>

        <label for="">Bebidas (Você pode escolher mais de uma): </label>
        <p><input type="checkbox" name="bebidas[]" value="Refrigerante - R$10,00">Refrigerante - R$10,00</p>
        <p><input type="checkbox" name="bebidas[]" value="Suco 500ml - R$12,00">Suco 500ml - R$12,00</p>
        <p><input type="checkbox" name="bebidas[]" value="Agua 500ml - R$5,00">Agua 500ml - R$5,00</p>

        <input type="submit" value="Enviar" class="botao">
    </form>
</body>

</html>

<?php
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $cliente = $_POST["txtnome"];
    $fritas = $_POST["rdfritas"];
    $lanche = $_POST["escolhaLanche"];
    $bebidas = $_POST["bebidas"];
    $vl_total = 0;
    $cont = 0;

    echo "<section class='resumoPedido'><h2>Resumo do pedido</h2>";
    echo "<p><strong>Cliente:</strong> $cliente</p>";

    if($lanche == 20) {
        echo "<p><strong>Lanche:</strong> Tradicional (R$20,00)</p>";
        $vl_total += 20;
    } else if($lanche == 25) {
        echo "<p><strong>Lanche:</strong> X-Salada (R$25,00)</p>";
        $vl_total += 25;
    }else if($lanche == 28) {
        echo "<p><strong>Lanche:</strong> X-Bacon (R$28,00)</p>";
        $vl_total += 28;
    }else {
        echo "<p><strong>Lanche:</strong> X-Tudo (R$30,00)</p>";
        $vl_total += 30;
    }
    
    if ($fritas == 15) {
        echo "<p><strong>Fritas:</strong> Sim (R$15,00)</p>";
        $vl_total += 15;
    } else {
        echo "<p><strong>Fritas:</strong> Não</p>";
    }

    echo "<p><strong>Bebidas:</strong> ";
    foreach ($bebidas as $bebida) {
        if($cont == 0 || $cont ==2) {

            echo $bebida . " ";
        }else {

            echo $bebida . ", ";
        }
        $cont++;
        if($bebida == "Refrigerante - R$10,00") {
            $vl_total += 10;
        }else if($bebida == "Suco 500ml - R$12,00"){
            $vl_total += 12;
        }else {
            $vl_total += 5;
        }
    };
    echo "</p>";
    echo "<hr>";
    echo "<p><strong>Total a pagar:</strong> R$$vl_total,00 </p></section>";


}
?>