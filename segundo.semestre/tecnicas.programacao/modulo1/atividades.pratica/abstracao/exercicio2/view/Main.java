package view;

import model.aplicativo.Aplicativo;
import model.arquivo.Arquivo;
import model.aula.Aula;
import model.avaliacao.Avaliacao;
import model.bancoDados.BancoDados;
import model.cliente.Cliente;
import model.contaBancaria.ContaBancaria;
import model.curso.Curso;
import model.jogo.Jogo;
import model.mensagem.Mensagem;
import model.pedido.Pedido;
import model.produto.Produto;
import model.projeto.Projeto;

public class Main {
    public static void main(String[] args) {

        // package model.aplicativo
        { 
            Aplicativo freeFire = new Aplicativo();
            freeFire.nome = "Free Fire";
            freeFire.categoria = "Tiro";
            freeFire.desenvolvedor = "Garena";
            freeFire.tamanho = 13.42;
            freeFire.versao = "2.32.3";

            freeFire.executar();
            freeFire.instalador();
            freeFire.executar();
            freeFire.desinstalador();
            freeFire.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.aplicativo
        { 
            Aplicativo mobileLegends = new Aplicativo();
            mobileLegends.nome = "Mobile Legends Bang Bang";
            mobileLegends.categoria = "Moba";
            mobileLegends.desenvolvedor = "Moonton";
            mobileLegends.tamanho = 23.37;
            mobileLegends.versao = "1.23.2";

            mobileLegends.executar();
            mobileLegends.instalador();
            mobileLegends.executar();
            mobileLegends.desinstalador();
            mobileLegends.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.arquivo
        { 
            Arquivo fotoSecretas = new Arquivo();
            fotoSecretas.nome = "Foto Secreta";
            fotoSecretas.tipoArquivo = ".png";
            fotoSecretas.tamanho = 1.1;

            fotoSecretas.editar();
            fotoSecretas.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.arquivo
        { 
            Arquivo videoSecreto = new Arquivo();
            videoSecreto.nome = "Video Secreto";
            videoSecreto.tipoArquivo = ".mp4";
            videoSecreto.tamanho = 2.3;

            videoSecreto.editar();
            videoSecreto.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.aula
        { 
            Aula tecnicasProg = new Aula();
            tecnicasProg.professor = "Alessandro";
            tecnicasProg.material = "Notebook/Computador com Umbuntu";
            tecnicasProg.gradeCurricular = "Abstração, Encapsulamento, Herança e Polimorfismo";
            tecnicasProg.cargaHoraria = 20;

            tecnicasProg.calcularMedia(10, 0.7, 3.3);
            tecnicasProg.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.aula
        { 
            Aula estruturaDados = new Aula();
            estruturaDados.professor = "Fernanda";
            estruturaDados.material = "Notebook/Computador com internet";
            estruturaDados.gradeCurricular = "Array, Matriz, Classes, Métodos, Objetos, etc...";
            estruturaDados.cargaHoraria = 20;

            estruturaDados.calcularMedia(9.7, 9.1, 7.3);
            estruturaDados.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.avaliacao
        { 
            Avaliacao bancoDados = new Avaliacao();
            bancoDados.aluno = "João Pedro";
            bancoDados.materia = "Banco de Dados Relacional";
            bancoDados.regras = "Sem consulta e sem colar";
            bancoDados.gabarito = "1-B, 2-C, 3-A, 4-E, 5-D";
            bancoDados.tempoLimite = 1.30;

            bancoDados.preencherGabarito("1-B", "2-C", "3-A", "4-E", "5-D");
            bancoDados.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.avaliacao
        { 
            Avaliacao web2 = new Avaliacao();
            web2.aluno = "João Pedro";
            web2.materia = "Desenvolvimento web 2";
            web2.regras = "Sem consulta e sem colar";
            web2.gabarito = "1-E, 2-C, 3-C, 4-E, 5-D";
            web2.tempoLimite = 1.30;

            web2.preencherGabarito("1-B", "2-C", "3-A", "4-E", "5-D");
            web2.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.bancoDados
        {
            BancoDados postgresql = new BancoDados();
            postgresql.nomeBanco = "PostgreSQL";
            postgresql.tipoBanco = "SQL";
            postgresql.quantidadeTabela = 3;
            postgresql.dataCriacao = "11/11/2001";
            postgresql.dataAtualizacao = "11/11/2001";

            postgresql.conectarBanco();
            postgresql.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.bancoDados
        {
            BancoDados mongoDB = new BancoDados();
            mongoDB.nomeBanco = "MongoDB";
            mongoDB.tipoBanco = "NoSQL";
            mongoDB.quantidadeTabela = 5;
            mongoDB.dataCriacao = "12/12/2006";
            mongoDB.dataAtualizacao = "12/12/2006";

            mongoDB.conectarBanco();
            mongoDB.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.cliente
        {
            Cliente pedro = new Cliente();
            pedro.nome = "Pedro";
            pedro.cpf = "874.742.321-76";
            pedro.telefone = "(13)4002-8922";
            pedro.email = "pedro.fatec.sp.gov.br";
            pedro.idade = 21;

            pedro.pagar("Pix");
            pedro.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.cliente
        {
            Cliente joao = new Cliente();
            joao.nome = "João";
            joao.cpf = "874.742.321-76";
            joao.telefone = "(13)4002-8922";
            joao.email = "joao.fatec.sp.gov.br";
            joao.idade = 31;

            joao.avaliar(5);
            joao.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.contaBancaria
        {
            ContaBancaria contaPedro = new ContaBancaria();
            contaPedro.agencia = "0000";
            contaPedro.conta = "874.742.321-76";
            contaPedro.tipoConta = "Conta Corrente";
            contaPedro.nomeCliente = "Pedro";
            contaPedro.cpfCliente = "874.742.321-76";

            contaPedro.exibirSaldo("R$23.357,32");
            contaPedro.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.contaBancaria
        {
            ContaBancaria contaJoao = new ContaBancaria();
            contaJoao.agencia = "0001";
            contaJoao.conta = "823.123.321-76";
            contaJoao.tipoConta = "Conta Corrente";
            contaJoao.nomeCliente = "João";
            contaJoao.cpfCliente = "823.123.321-76";

            contaJoao.fazerPagamento("498223123-1", 321.32, "Cartão de Crédito");
            contaJoao.realizarTransferencia("874.742.321-76", 1532.3);
        }
        System.out.println("###---###---###---###---###");

        // package model.curso
        { 
            Curso DSM = new Curso();
            DSM.nomeCurso = "Desenvolvimento de Software Multiplataformas";
            DSM.cargaHoraria = 250;

            DSM.fazerMatricula("234.223.123");
            DSM.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.curso
        { 
            Curso ADS = new Curso();
            ADS.nomeCurso = "Analise e Desenvolvimento de Sistemas";
            ADS.cargaHoraria = 220;
            ADS.matriculas = "234.223.123";

            ADS.encerrarMatricula("234.223.123");
            ADS.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.jogo
        { 
            Jogo pokemmo = new Jogo();
            pokemmo.nomeJogador = "Azien";
            pokemmo.tempoJogo = 2345.3;
            pokemmo.nivelAtual = 46;

            pokemmo.iniciarPartida(null);
            pokemmo.salvarProgresso(54, 200.3, "Azien");
        }
        System.out.println("###---###---###---###---###");

        // package model.jogo
        { 
            Jogo minecraft = new Jogo();
            minecraft.nomeJogador = "Azien";
            minecraft.tempoJogo = 3845.3;
            minecraft.nivelAtual = 33;

            minecraft.salvarProgresso(121, 200.3, "Azien");
            minecraft.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.mensagem
        { 
            Mensagem msg = new Mensagem();
            msg.remetente = "João Pedro";
            msg.destinatario = "Pedro";
            msg.conteudo = "Fala Pedro, blz?";

            msg.enviar();
            msg.receber();
        }
        System.out.println("###---###---###---###---###");

        // package model.mensagem
        { 
            Mensagem msg2 = new Mensagem();
            msg2.remetente = "Pedro";
            msg2.destinatario = "João Pedro";
            msg2.conteudo = "Fala João, blz e vc?";

            msg2.enviar();
            msg2.receber();
            msg2.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.pedido
        { 
            Pedido pedido1 = new Pedido();
            pedido1.codigoPedido = "12345";
            pedido1.data = "01/01/2024";
            pedido1.valorTotal = 12350.75;
            pedido1.cliente = "João Pedro";
            pedido1.itens = "MacBook Pro, iPhone 13, iPad Air";

            pedido1.removerItem("iPad Air");
            pedido1.calcularValorTotal();
            pedido1.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.pedido
        { 
            Pedido pedido2 = new Pedido();
            pedido2.codigoPedido = "54321";
            pedido2.data = "02/01/2024";
            pedido2.valorTotal = 2350.50;
            pedido2.cliente = "Pedro";
            pedido2.itens = "Monitor LG, Teclado Mecânico";

            pedido2.adicionarItem("Mouse Gamer");
            pedido2.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.produto
        { 
            Produto produto1 = new Produto();
            produto1.cadastrarProduto("iPhone 13", "Eletrônicos", 7999.99);
            produto1.atualizarPreco(7499.99);
            produto1.detalhes();
        }
        System.out.println("###---###---###---###---###");

        // package model.produto
        { 
            Produto produto2 = new Produto();
            produto2.cadastrarProduto("Samsung Galaxy S21", "Eletrônicos", 6999.99);
            produto2.atualizarPreco(6499.99);
            produto2.detalhes();
        }
        System.out.println("###---###---###---###---###");
        
        // package model.projeto
        {   
            Projeto projeto1 = new model.projeto.Projeto();
            projeto1.nome = "Sistema de Gerenciamento Escolar";
            projeto1.descricao = "Desenvolver um sistema para gerenciar alunos, professores e cursos.";
            projeto1.dataInicio = "01/02/2024";
            projeto1.prazoFinal = "01/08/2024";
            projeto1.objetivo = "Facilitar a administração escolar.";

            projeto1.iniciarProjeto();
            projeto1.detalhes();
            projeto1.finalizarProjeto();
        }
        System.out.println("###---###---###---###---###");
        
        // package model.projeto
        {
            Projeto projeto2 = new model.projeto.Projeto();
            projeto2.nome = "Aplicativo de Entrega de Comida";
            projeto2.descricao = "Criar um aplicativo para pedidos e entregas de comida.";
            projeto2.dataInicio = "15/03/2024";
            projeto2.prazoFinal = "15/09/2024";
            projeto2.objetivo = "Conectar restaurantes e clientes.";

            projeto2.iniciarProjeto();
            projeto2.detalhes();
            projeto2.finalizarProjeto();
        }
        System.out.println("###---###---###---###---###");
    }
}