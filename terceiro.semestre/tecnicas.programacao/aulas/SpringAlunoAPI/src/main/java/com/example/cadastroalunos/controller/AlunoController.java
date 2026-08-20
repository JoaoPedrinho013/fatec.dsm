package com.example.cadastroalunos.controller;

import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository repo;

    public AlunoController(AlunoRepository repo) {
        this.repo = repo;
    }

    // GET /alunos → lista todos
    @GetMapping
    public List<Aluno> listar() {
        return repo.findAll();
    }

    // GET /alunos/{id} → busca por ID
    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }

    // POST /alunos → cadastra novo aluno
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno cadastrar(@RequestBody Aluno aluno) {
        return repo.save(aluno);
    }

    // PUT /alunos/{id} → atualiza aluno existente
    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno dados) {
        Aluno aluno = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        aluno.setNome(dados.getNome());
        aluno.setCpf(dados.getCpf());
        aluno.setEmail(dados.getEmail());
        return repo.save(aluno);
    }

    // DELETE /alunos/{id} → remove aluno
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        repo.deleteById(id);
    }
}
