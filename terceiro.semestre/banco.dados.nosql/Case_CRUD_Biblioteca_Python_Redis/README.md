# Biblioteca Digital

Sistema de Biblioteca Digital desenvolvido com Python, Flask, Redis, React e Material UI.

O sistema permite que um administrador gerencie o acervo da biblioteca, cadastrando, atualizando e removendo livros. Os livros possuem título, autor, categorias (múltiplas), ano de publicação, quantidade disponível, sinopse e capa.

Usuários comuns podem criar uma conta, consultar todos os livros disponíveis, pegar livros emprestados, favoritar livros e entrar em uma lista de espera quando um livro estiver sem estoque. Cada usuário pode ter no máximo 3 livros emprestados ao mesmo tempo.

Cada empréstimo dura 5 minutos. Se o usuário não devolver o livro dentro desse prazo, o sistema devolve automaticamente o livro ao estoque e emite uma notificação. Quando um livro volta a ficar disponível, os usuários que estavam na lista de espera recebem uma notificação dentro da própria aplicação. As notificações podem ser marcadas como lidas ou apagadas.

## Apresentação em Vídeo

[![Assistir no YouTube](https://img.shields.io/badge/YouTube-Assistir%20apresentação-red?logo=youtube)](https://youtu.be/Cr_kBPb9sB0)

https://youtu.be/Cr_kBPb9sB0

## Requisitos

- Docker instalado e em execução
- Node.js instalado

## Como Rodar o Backend e o Redis

O backend Flask e o Redis rodam juntos via Docker Compose. Na raiz do projeto, execute:

```bash
docker compose up -d --build
```

Para verificar se os containers estão rodando:

```bash
docker ps
```

Para parar os containers:

```bash
docker compose down
```

Backend disponível em:

```
http://localhost:5000
```

Documentação da API (Swagger):

```
http://localhost:5000/docs/
```

## Como Rodar o Frontend

Em outro terminal, entre na pasta do frontend:

```bash
cd frontend
```

Instale as dependências (apenas na primeira vez):

```bash
npm install
```

Rode o frontend:

```bash
npm start
```

Frontend disponível em:

```
http://localhost:3000
```

## Login Admin

O usuário admin é criado automaticamente quando o backend inicia, caso ainda não exista no Redis.

```
username: admin
senha: admin
```

## Capas dos Livros

As imagens de capa ficam em `frontend/public/covers/`. O nome do arquivo deve ser o título do livro normalizado: tudo em minúsculo, sem acentos e com espaços substituídos por `_`.

Exemplos:

```
Dom Casmurro          → dom_casmurro.png
Harry Potter          → harry_potter.png
O Senhor dos Anéis    → o_senhor_dos_aneis.png
```

Formatos aceitos: PNG e JPG. O upload pode ser feito diretamente pelo formulário de cadastro e edição de livros no painel do administrador.

## Modelagem da Base de Dados (Redis)

O banco de dados utilizado é o Redis (db=1), com prefixo `bib2:` em todas as chaves para isolar o projeto de outros que possam usar o mesmo Redis.

### Livros

| Chave | Tipo | Descrição |
|-------|------|-----------|
| `bib2:livro:{id}` | Hash | Dados do livro (id, titulo, autor, categoria, ano, quantidade, status, sinopse) |
| `bib2:livro_id` | String | Contador auto-incremento para geração de IDs |
| `bib2:livro:{id}:espera` | Set | Usuários na fila de espera pelo livro |

### Usuários

| Chave | Tipo | Descrição |
|-------|------|-----------|
| `bib2:usuario:{username}` | Hash | Dados do usuário (username, senha_hash, cargo) |
| `bib2:usuario:{username}:emprestimos` | Set | IDs dos livros atualmente emprestados |
| `bib2:usuario:{username}:favoritos` | Set | IDs dos livros favoritados |
| `bib2:usuario:{username}:espera` | Set | IDs dos livros aguardando disponibilidade |
| `bib2:usuario:{username}:notificacoes` | List | Notificações do usuário (JSON com mensagem e status de leitura) |

### Empréstimos

| Chave | Tipo | Descrição |
|-------|------|-----------|
| `bib2:emprestimo:{username}:{livro_id}` | Hash | Dados do empréstimo (username, livro_id, data, devolucao_em) |
| `bib2:emprestimos_vencimento` | Sorted Set | Todos os empréstimos ativos ordenados pelo timestamp de vencimento — usado pelo auto-retorno |

### Estrutura de um Livro no Redis

```
bib2:livro:1
  id         → "1"
  titulo     → "Dom Casmurro"
  autor      → "Machado de Assis"
  categoria  → "Literatura,Romance"
  ano        → "1899"
  quantidade → "3"
  status     → "Disponivel"
  sinopse    → "..."
```

## Observações

- O backend e o Redis precisam estar rodando antes de usar o frontend.
- O Redis roda dentro do Docker na rede interna, sem expor porta para o host, evitando conflito com outros projetos Redis locais.
- A pasta `covers/` é compartilhada entre o container do backend (para salvar uploads) e o frontend (para servir as imagens).
- A documentação completa dos endpoints está disponível em `/docs/`.
