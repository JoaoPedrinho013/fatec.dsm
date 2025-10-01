<?php 
session_start();

//apaga todas as variaveis de sessoes
session_unset();

//destruir a sessao
session_destroy();

header('Location: index.php')

?>