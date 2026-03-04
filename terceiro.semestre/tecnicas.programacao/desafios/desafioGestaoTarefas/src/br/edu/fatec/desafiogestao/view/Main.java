package br.edu.fatec.desafiogestao.view;

import br.edu.fatec.desafiogestao.controller.TarefaController;
import br.edu.fatec.desafiogestao.model.Tarefa;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TarefaController controller = new TarefaController();

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Listar todas tarefas");
            System.out.println("3 - Editar tarefa");
            System.out.println("4 - Excluir tarefa");
            System.out.println("5 - Filtrar tarefas por Categoria");
            System.out.println("6 - Filtrar tarefas por Status");
            System.out.println("9 - Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1: {
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Descricao: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Status (PENDENTE/CONCLUIDA): ");
                    String status = scanner.nextLine().toLowerCase();
                    boolean concluida = status.equals("concluida");

                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();

                    Tarefa tarefa = new Tarefa(titulo, descricao, concluida, categoria);
                    controller.create(tarefa);
                    break;
                }

                case 2: {
                    var tarefas = controller.getAll();

                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                    } else {
                        for (Tarefa t : tarefas) {
                            System.out.println("---------------------------");
                            System.out.println("ID: " + t.getId());
                            System.out.println("Título: " + t.getTitulo());
                            System.out.println("Descrição: " + t.getDescricao());
                            System.out.println("Concluída: " + (t.getConcluida() ? "Concluida" : "Pendente"));
                            System.out.println("Categoria: " + t.getCategoria());
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.print("Digite o ID da tarefa para editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo Título: ");
                    String novoTitulo = scanner.nextLine();

                    System.out.print("Nova Descrição: ");
                    String novaDescricao = scanner.nextLine();

                    System.out.print("Status (PENDENTE/CONCLUIDA): ");
                    String status = scanner.nextLine().toLowerCase();
                    boolean concluida = status.equals("concluida");

                    System.out.print("Nova Categoria: ");
                    String novaCategoria = scanner.nextLine();

                    Tarefa tarefaAtualizada = new Tarefa(novoTitulo, novaDescricao, concluida, novaCategoria);
                    tarefaAtualizada.setId(idEditar);

                    controller.update(tarefaAtualizada);
                    break;
                }

                case 4: {
                    System.out.print("Digite o ID da tarefa para excluir: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();

                    controller.deleteById(idExcluir);
                    break;
                }

                case 5: {

                    System.out.print("Digite a categoria: ");
                    String categoriaFiltro = scanner.nextLine();

                    var tarefas = controller.findByCategoria(categoriaFiltro);

                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa encontrada.");
                    } else {
                        for (Tarefa t : tarefas) {
                            System.out.println("---------------------------");
                            System.out.println("ID: " + t.getId());
                            System.out.println("Título: " + t.getTitulo());
                            System.out.println("Descrição: " + t.getDescricao());
                            System.out.println("Concluída: " + (t.getConcluida() ? "Concluída" : "Pendente"));
                            System.out.println("Categoria: " + t.getCategoria());
                        }
                    }

                    break;
                }

                case 6: {

                    System.out.print("Status (PENDENTE/CONCLUIDA): ");
                    String statusFiltro = scanner.nextLine().toLowerCase();

                    boolean concluida = statusFiltro.equals("concluida");

                    var tarefas = controller.findByStatus(concluida);

                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa encontrada.");
                    } else {
                        for (Tarefa t : tarefas) {
                            System.out.println("---------------------------");
                            System.out.println("ID: " + t.getId());
                            System.out.println("Título: " + t.getTitulo());
                            System.out.println("Descrição: " + t.getDescricao());
                            System.out.println("Concluída: " + (t.getConcluida() ? "Concluída" : "Pendente"));
                            System.out.println("Categoria: " + t.getCategoria());
                        }
                    }

                    break;
                }

                case 9: {
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;
                }

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}