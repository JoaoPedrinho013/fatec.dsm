package br.edu.fatecpg.fatec.repository;

import br.edu.fatecpg.fatec.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
