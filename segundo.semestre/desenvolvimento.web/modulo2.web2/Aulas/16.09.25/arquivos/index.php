<?php 

$arquivo = fopen("dados.txt", "a");

// fwrite($arquivo,"Azien lindo.\n");
fclose($arquivo);

$arquivo = fopen("dados.txt", "r");
// $conteudo = fread($arquivo, filesize("dados.txt"));
// echo nl2br($conteudo);
while (($linha = fgets($arquivo)) != false) {
    echo "<br>" . $linha;
    $array = explode(",", $linha);
    print_r($array);
}
fclose($arquivo);
?>