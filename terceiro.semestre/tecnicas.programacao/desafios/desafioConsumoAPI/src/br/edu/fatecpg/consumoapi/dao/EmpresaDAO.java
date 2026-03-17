package br.edu.fatecpg.consumoapi.dao;

import br.edu.fatecpg.consumoapi.db.DB;
import br.edu.fatecpg.consumoapi.model.Empresa;
import br.edu.fatecpg.consumoapi.model.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    private static final String UNIQUE_VIOLATION = "23505";


    public int inserir(Empresa empresa) {

        String sql = """
            INSERT INTO empresa (cnpj, razao_social, nome_fantasia, logradouro)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, empresa.getCnpj());
            stmt.setString(2, empresa.getRazao_social());
            stmt.setString(3, empresa.getNome_fantasia());
            stmt.setString(4, empresa.getLogradouro());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            if (UNIQUE_VIOLATION.equals(e.getSQLState())) {
                System.out.println("⚠️  Empresa com CNPJ " + empresa.getCnpj() + " já está cadastrada no banco.");
            } else {
                System.out.println("Erro ao salvar empresa: " + e.getMessage());
            }
        }

        return -1;
    }


    public List<Empresa> listarTodas() {

        List<Empresa> empresas = new ArrayList<>();

        String sql = """
            SELECT cnpj, razao_social, nome_fantasia, logradouro
            FROM empresa
            ORDER BY razao_social
        """;

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                empresas.add(new Empresa(
                    rs.getString("cnpj"),
                    rs.getString("razao_social"),
                    rs.getString("nome_fantasia"),
                    rs.getString("logradouro")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar empresas: " + e.getMessage());
        }

        return empresas;
    }


    public List<Empresa> listarPorNome(String nome) {

        List<Empresa> empresas = new ArrayList<>();

        String sql = """
            SELECT e.id, e.cnpj, e.razao_social, e.nome_fantasia, e.logradouro,
                   s.nome_socio, s.cnpj_cpf_do_socio, s.qualificacao_socio
            FROM empresa e
            LEFT JOIN socio s ON s.empresa_id = e.id
            WHERE LOWER(e.razao_social) LIKE LOWER(?)
               OR LOWER(e.nome_fantasia) LIKE LOWER(?)
            ORDER BY e.razao_social
        """;

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String filtro = "%" + nome + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);

            ResultSet rs = stmt.executeQuery();
            int ultimoId = -1;
            Empresa empresa = null;

            while (rs.next()) {
                int id = rs.getInt("id");

                if (id != ultimoId) {
                    empresa = new Empresa(
                        rs.getString("cnpj"),
                        rs.getString("razao_social"),
                        rs.getString("nome_fantasia"),
                        rs.getString("logradouro")
                    );
                    empresas.add(empresa);
                    ultimoId = id;
                }

                String nomeSocio = rs.getString("nome_socio");
                if (nomeSocio != null && empresa != null) {
                    empresa.getQsa().add(new Socio(
                        nomeSocio,
                        rs.getString("cnpj_cpf_do_socio"),
                        rs.getString("qualificacao_socio")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar empresa: " + e.getMessage());
        }

        return empresas;
    }


    public boolean deletar(String cnpj) {

        String sql = "DELETE FROM empresa WHERE cnpj = ?";

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar empresa: " + e.getMessage());
        }

        return false;
    }
}
