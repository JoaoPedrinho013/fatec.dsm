package br.edu.fatecpg.consumoapi.view;

import br.edu.fatecpg.consumoapi.db.DB;
import br.edu.fatecpg.consumoapi.model.Empresa;
import br.edu.fatecpg.consumoapi.model.Socio;
import br.edu.fatecpg.consumoapi.service.BrasilApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Scanner scan = new Scanner(System.in);
        boolean validacao = true;

        System.out.println("Seja bem vindo ao Cadastro de Empresas e Socios");

        while(validacao) {
            System.out.println("Digite 1 para cadastrar uma Empresa, \n" +
                    "Digite 2 para excluir uma Empresa, \n" +
                    "Digite 3 para ver todas Empresas, \n" +
                    "Digite 9 para sair:");

            String opcao = scan.nextLine();

            switch (opcao){
                case "1":
                    System.out.println("Digite um CNPJ valido: ");
                    String cnpjDigitado = scan.nextLine();
                    String cnpjNormalizado = cnpjDigitado.toUpperCase().replaceAll("[^A-Z0-9]", "");

                    try {
                        String retorno = BrasilApi.buscaEmpresa(cnpjNormalizado);

                        if (!retorno.contains("bad_request")){

                            Empresa end = gson.fromJson(retorno, Empresa.class);

                            String insertEmpresa = " INSERT INTO empresa (cnpj, razao_social, nome_fantasia, logradouro) VALUES (?, ?, ?, ?)";

                            try (Connection conn = DB.connection();
                                 PreparedStatement stmtEmpresa = conn.prepareStatement(insertEmpresa, Statement.RETURN_GENERATED_KEYS)) {

                                stmtEmpresa.setString(1, end.getCnpj());
                                stmtEmpresa.setString(2, end.getRazao_social());
                                stmtEmpresa.setString(3, end.getNome_fantasia());
                                stmtEmpresa.setString(4, end.getLogradouro());

                                int rowsEmpresa = stmtEmpresa.executeUpdate();
                                System.out.println("COD_STATUS: 201 - Empresa criada com sucesso");

                                if (rowsEmpresa > 0){
                                    ResultSet generatedKeys = stmtEmpresa.getGeneratedKeys();
                                    int empresa_id = -1;
                                    if (generatedKeys.next()) { empresa_id = generatedKeys.getInt(1); }

                                    String insertSocio = "INSERT INTO socio (nome_socio, cnpj_cpf_do_socio, qualificacao_socio, empresa_id) VALUES (?, ?, ?, ?)";

                                    try {
                                        PreparedStatement stmtSocio = conn.prepareStatement(insertSocio);
                                        for (Socio campo :  end.getQsa()){
                                            stmtSocio.setString(1, campo.getNome_socio());
                                            stmtSocio.setString(1, campo.getCnpj_cpf_do_socio());
                                            stmtSocio.setString(3, campo.getQualificacao_socio());
                                            stmtSocio.setInt(4, empresa_id);
                                            stmtSocio.executeUpdate();
                                        }
                                    } catch (Exception e) {
                                        System.out.println("ERROR: " + e);
                                    }
                                }

                                System.out.println(end + "\n");

                            } catch (Exception e) {
                                System.out.println("COD_STATUS: 409 - Essa empresa ja foi criada!");
                            }

                        } else {
                            System.out.println("COD_STATUS: 400 - CNPJ Invalido!");
                        }

                    } catch (IOException | InterruptedException e) {
                        System.out.println("ERROR: " + e + " - CNPJ Invalido!\n");
                    }
                    break;

                case "2":
                    System.out.println("EXCLUIR EMPRESA");
                    break;

                case "3":
                    System.out.println("LISTAR TODAS EMPRESAS");
                    break;

                case "9":
                    System.out.println("Obrigado por usar o sistema!");
                    validacao = false;
                    break;

                default:
                    System.out.println("Essa opcao nao existe!");
            }
        }


    }
}
