<?php

$Nomes = ["Pedro", "Gege", "Caio", "Carlos"];
$Nomes[] = "João"; //add um novo
$Nomes[0] = "Loro"; //Substitui um existente

echo "Segundo nome: " . $Nomes[1];
sort($Nomes);

for ($c = 0; $c < count($Nomes); $c++) {
    echo "<br>Nomes[$c]:   $Nomes[$c]";
}
echo "<hr>";

$idades = array(25, 35, 53, 23);

echo "<br>Última idade: " . $idades[3];
echo "<br>Total de idades: " . count($idades);
sort($idades); // ordena
rsort($idades); // ordena ao contrario

foreach ($idades as $idade) { 
    echo "<br>". $idade ."";
}

echo "<hr>";
$aluno = [
    "Matricula" => 123,
    "Nome" => "João",
    "Curso" => "DSM"
];

echo "<br>Nome: " . $aluno["Nome"]; // lista
$aluno["periodo"] = "Vespertino"; // add um novo

unset($aluno["Matricula"]); //exclui um item

sort($aluno); 

foreach ($aluno as $a) {
    echo "<br> $a";
}

echo "<hr>";

$unidades = [
    0 => "PG",
    1 => "SV"
];

foreach ($unidades as $chave => $valor) {
    echo "<br> $chave: $valor";
}

echo "<hr>";