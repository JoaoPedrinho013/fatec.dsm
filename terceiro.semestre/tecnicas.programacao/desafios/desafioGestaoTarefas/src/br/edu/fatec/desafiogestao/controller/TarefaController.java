package br.edu.fatec.desafiogestao.controller;


import br.edu.fatec.desafiogestao.db.DB;
import br.edu.fatec.desafiogestao.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TarefaController {

    public void create(Tarefa tarefa) {

        var query = " INSERT INTO tarefas (titulo, descricao, concluida, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setBoolean(3, tarefa.getConcluida());
            stmt.setString(4, tarefa.getCategoria());

            stmt.executeUpdate();

            System.out.println("Tarefa criada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar tarefa:");
        }

    }

    public List<Tarefa> getAll() {

        var query = "SELECT id, titulo, descricao, concluida, categoria FROM tarefas";

        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Tarefa tarefa = new Tarefa(
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBoolean("concluida"),
                        rs.getString("categoria")
                );

                tarefa.setId(rs.getInt("id"));

                tarefas.add(tarefa);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas:");
        }

        return tarefas;
    }

    public void update(Tarefa tarefa) {

        var query = """
            UPDATE tarefas
            SET titulo = ?, descricao = ?, concluida = ?, categoria = ?
            WHERE id = ?
            """;

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setBoolean(3, tarefa.getConcluida());
            stmt.setString(4, tarefa.getCategoria());
            stmt.setInt(5, tarefa.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa atualizada com sucesso!");
            } else {
                System.out.println("Tarefa não encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar tarefa:");
        }
    }

    public void deleteById(int id) {

        var query = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa excluída com sucesso!");
            } else {
                System.out.println("Tarefa não encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir tarefa:");
            e.printStackTrace();
        }
    }

    public List<Tarefa> findByStatus(boolean concluida) {

        var query = """
            SELECT id, titulo, descricao, concluida, categoria
            FROM tarefas
            WHERE concluida = ?
            """;

        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, concluida);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Tarefa tarefa = new Tarefa(
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBoolean("concluida"),
                        rs.getString("categoria")
                );

                tarefa.setId(rs.getInt("id"));

                tarefas.add(tarefa);
            }

        } catch (Exception e) {
            System.out.println("Erro ao filtrar por status:");
        }

        return tarefas;
    }

    public List<Tarefa> findByCategoria(String categoria) {

        var query = """
            SELECT id, titulo, descricao, concluida, categoria
            FROM tarefas
            WHERE LOWER(categoria) = LOWER(?)
            """;

        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conn = DB.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, categoria);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Tarefa tarefa = new Tarefa(
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBoolean("concluida"),
                        rs.getString("categoria")
                );

                tarefa.setId(rs.getInt("id"));

                tarefas.add(tarefa);
            }

        } catch (Exception e) {
            System.out.println("Erro ao filtrar por categoria:");
        }

        return tarefas;
    }
}
