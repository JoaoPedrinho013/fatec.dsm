class Pessoa:
    total_pessoas = 0

    def __init__(self, _nome, _idade):
        self.nome = _nome
        self.idade = _idade
        Pessoa.total_pessoas += 1

    def __str__(self):
        print(f'Nome: {self.nome} - Idade: {self.idade}')

    def total(self):
        print(f'Total de Pessoas {Pessoa.total_pessoas}')

class Aluno(Pessoa):
    def __init__(self, _nome, _idade, _matricula, _curso):
        super().__init__(_nome, _idade)
        self.matricula = _matricula
        self.curso = _curso

    def __str__(self):
        print(f'Aluno: {self.nome} - Idade: {self.idade} - Matricula: {self.matricula} - Curso: {self.curso}')



a1 = Aluno("Lula", 22, "001", "DSM")
a1.__str__()

print("=-=" * 25)

p1 = Pessoa("Azien", "13")
p2 = Pessoa("Kaya", "22")
print(p1.nome, p1.idade)
print(Pessoa.total_pessoas)
p1.__str__()
p1.total()