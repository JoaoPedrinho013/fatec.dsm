package br.edu.fatecpg.fatec;

import br.edu.fatecpg.fatec.model.Aluno;
import br.edu.fatecpg.fatec.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FatecApplication implements CommandLineRunner {
	@Autowired
	private AlunoRepository rep;
	public static void main(String[] args) {
		SpringApplication.run(FatecApplication.class, args);
	}

	@java.lang.Override
	public void run(java.lang.String... args) throws Exception {
		/*
		Aluno a1 = new Aluno("Joao", "123.456.789.00");
		Aluno a2 = new Aluno("Caio", "456.456.789.00");
		Aluno a3 = new Aluno("Gege", "789.456.789.00");

		rep.save(a1);
		rep.save(a2);
		rep.save(a3);
		*/
		List<Aluno> alunos = rep.findAll();
		alunos.forEach(System.out::println);
	}
}
