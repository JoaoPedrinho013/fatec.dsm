package br.edu.fatecpg.consumoapi.dao;

import br.edu.fatecpg.consumoapi.db.DB;
import br.edu.fatecpg.consumoapi.model.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SocioDAO {

    public void inserir(Socio socio, int empresaId) {

        String sql = """
        INSERT INTO socio
        (nome_socio, cnpj_cpf_do_socio, qualificacao_socio, empresa_id)
        VALUES (?, ?, ?, ?)
    """;

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, socio.getNome_socio());
            stmt.setString(2, socio.getCnpj_cpf_do_socio());
            stmt.setString(3, socio.getQualificacao_socio());
            stmt.setInt(4, empresaId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar socio");
            e.printStackTrace();
        }
    }
}