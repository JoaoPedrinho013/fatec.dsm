package br.edu.fatecpg.consumoapi.view;

import br.edu.fatecpg.consumoapi.dao.EmpresaDAO;
import br.edu.fatecpg.consumoapi.dao.SocioDAO;
import br.edu.fatecpg.consumoapi.model.Empresa;
import br.edu.fatecpg.consumoapi.model.Socio;
import br.edu.fatecpg.consumoapi.service.BrasilApi;

import com.google.gson.Gson;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static EmpresaDAO empresaDAO = new EmpresaDAO();
    static SocioDAO socioDAO = new SocioDAO();
    static Gson gson = new Gson();

    public static void main(String[] args) {

        while (true) {
            exibirMenu();

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> listarTodas();
                case "3" -> listarPorNome();
                case "4" -> deletar();
                case "9" -> {
                    System.out.println("\nEncerrando o programa. Até logo!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }


    private static void exibirMenu() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       GESTÃO DE EMPRESAS     ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1 - Cadastrar empresa       ║");
        System.out.println("║  2 - Listar todas            ║");
        System.out.println("║  3 - Buscar por nome         ║");
        System.out.println("║  4 - Deletar empresa         ║");
        System.out.println("║  9 - Sair                    ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }


    private static void cadastrar() {
        System.out.print("\nDigite o CNPJ: ");
        String input = scanner.nextLine().trim();

        String cnpj = input.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            System.out.println("CNPJ inválido. Certifique-se de digitar 14 dígitos.");
            return;
        }

        try {
            String json = BrasilApi.buscaEmpresa(cnpj);

            if (json.contains("\"message\"") || json.contains("\"error\"")) {
                System.out.println("CNPJ não encontrado na BrasilAPI. Verifique e tente novamente.");
                return;
            }

            Empresa empresa = gson.fromJson(json, Empresa.class);

            int empresaId = empresaDAO.inserir(empresa);

            if (empresaId == -1) return;

            for (Socio socio : empresa.getQsa()) {
                socioDAO.inserir(socio, empresaId);
            }

            System.out.println("Empresa \"" + empresa.getRazao_social() + "\" foi adicionada ao banco!");

        } catch (Exception e) {
            System.out.println("Erro ao consultar a API ou salvar os dados: " + e.getMessage());
        }
    }


    private static void listarTodas() {
        List<Empresa> empresas = empresaDAO.listarTodas();

        if (empresas.isEmpty()) {
            System.out.println("\nNenhuma empresa cadastrada.");
            return;
        }

        System.out.println("\n── Empresas cadastradas (" + empresas.size() + ") ──────────────────────");

        for (Empresa e : empresas) {
            System.out.printf("%-18s | %-40s | %s%n",
                e.getCnpj(),
                e.getRazao_social(),
                e.getNome_fantasia() != null ? e.getNome_fantasia() : "-"
            );
        }

        System.out.println("──────────────────────────────────────────────────────────────");
    }


    private static void listarPorNome() {
        System.out.print("\nDigite o nome da empresa: ");
        String nome = scanner.nextLine().trim();

        if (nome.isBlank()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }

        List<Empresa> empresas = empresaDAO.listarPorNome(nome);

        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa encontrada para \"" + nome + "\".");
            return;
        }

        for (Empresa e : empresas) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("  CNPJ          : " + e.getCnpj());
            System.out.println("  Razão Social  : " + e.getRazao_social());
            System.out.println("  Nome Fantasia : " + (e.getNome_fantasia() != null ? e.getNome_fantasia() : "-"));
            System.out.println("  Logradouro    : " + (e.getLogradouro() != null ? e.getLogradouro() : "-"));

            if (!e.getQsa().isEmpty()) {
                System.out.println("  Sócios:");
                for (Socio s : e.getQsa()) {
                    System.out.println("    • " + s.getNome_socio()
                        + " (" + s.getQualificacao_socio() + ")"
                        + " — " + s.getCnpj_cpf_do_socio());
                }
            } else {
                System.out.println("  Sócios        : Nenhum registrado");
            }

            System.out.println("╚══════════════════════════════════════════╝");
        }
    }

    private static void deletar() {
        System.out.print("\nDigite o CNPJ da empresa a deletar: ");
        String input = scanner.nextLine().trim();

        String cnpj = input.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            System.out.println("CNPJ inválido.");
            return;
        }

        System.out.print("Tem certeza que deseja deletar a empresa com CNPJ " + cnpj + "? (s/n): ");
        String confirmacao = scanner.nextLine().trim();

        if (!confirmacao.equalsIgnoreCase("s")) {
            System.out.println("Operação cancelada.");
            return;
        }

        boolean deletado = empresaDAO.deletar(cnpj);

        if (deletado) {
            System.out.println("Empresa deletada com sucesso! (sócios removidos automaticamente)");
        } else {
            System.out.println("⚠Nenhuma empresa encontrada com esse CNPJ.");
        }
    }
}
