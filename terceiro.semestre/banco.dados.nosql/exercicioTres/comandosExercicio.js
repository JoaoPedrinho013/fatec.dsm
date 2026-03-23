/* 1. Selecionar os nomes e os endereços dos funcionários que são programadores e
trabalham no departamento 3. */

db.funcionario.find(
  {
    funcao: "PROGRAMADOR",
    depto: 3
  },
  {
    nome: 1,
    endereco: 1,
    _id: 0
  }
)





db.filmes.find({
    $and: [
    {
        $or: [
            { generos: "Crime"},
            { generos: "Ação"}
        ]
    },
    { ano: {$gt: 2000}}
    ]
})