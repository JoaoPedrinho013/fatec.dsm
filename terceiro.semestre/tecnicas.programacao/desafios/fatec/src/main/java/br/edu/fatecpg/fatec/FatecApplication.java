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
	public void run(String... args) throws Exception {

		if (rep.count() == 0) {
			Aluno a1 = new Aluno("Joao", "123.456.789.00", "001", "joao@gmail.com");
			Aluno a2 = new Aluno("Caio", "456.456.789.00", "002", "caio@gmail.com");
			Aluno a3 = new Aluno("Gege", "789.456.789.00", "003", "gege@gmail.com");
			Aluno a4 = new Aluno("Carlos", "456.456.456.00", "004", "carlos@gmail.com");
			Aluno a5 = new Aluno("Eduardo", "123.123.123.00", "005", "eduardo@gmail.com");
			Aluno a6 = new Aluno("Pedro", "789.789.789.00", "006", "pedro@gmail.com");

			rep.save(a1);
			rep.save(a2);
			rep.save(a3);
			rep.save(a4);
			rep.save(a5);
			rep.save(a6);
			System.out.println("Dados no banco inseridos com sucesso.");
		} else {
			System.out.println("Já existem dados no banco.");
		}


		/*BUSCAR TODOS E TRAZER NOME E EMAIL*/
		List<Aluno> alunos = rep.findAll();
		alunos.forEach(a ->
				System.out.println("Nome: " + a.getNome() + " | Email: " + a.getEmail())
		);

		/*BUSCAR POR NOME*/
		List<Aluno> encontrados = rep.buscarPorNome("Joao");

		encontrados.forEach(a ->
				System.out.println("Nome: " + a.getNome() + " | Email: " + a.getEmail())
		);
		/*BUSCAR POR ID*/
		rep.findById(5L).ifPresent(a ->
				System.out.println("Nome: " + a.getNome() + " | Email: " + a.getEmail())
		);

		/*DELETAR POR ID*/
		rep.deleteById(6L);

		rep.findAll().forEach(a ->
				System.out.println("Nome: " + a.getNome() + " | Email: " + a.getEmail())
		);
	}
}
