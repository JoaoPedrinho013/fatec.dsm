# Desafio de Design Thinking: melhorando a experiência dos alunos da FATEC

**Objeto de estudo:** SIGA (sistema acadêmico da FATEC)
**Método:** análise de 7 prints de tela + etapas de Design Thinking (problema, entrevista, persona, definição, ideias e solução) + protótipo navegável em HTML, CSS e JS

---

## Sobre o projeto

**O que é:** um trabalho acadêmico de Design Thinking que responde ao desafio "melhorar a experiência dos alunos da FATEC". O projeto analisa o SIGA, o sistema acadêmico usado pelos alunos, e propõe uma versão redesenhada de algumas telas.

**O que foi feito, em ordem:**
1. Análise de 7 prints do SIGA, com os pontos fracos de usabilidade (Passo 1).
2. Escolha de um problema real: a dificuldade de consultar horário e avaliações de forma rápida, principalmente pelo celular (Passo 2).
3. Perguntas de entrevista, persona hipotética (Roberto Carlos), definição do problema, 10 ideias e a solução escolhida, o calendário "Minha semana" (Passos 3 a 7).
4. Um **protótipo navegável** com login, tela inicial e horário das aulas, feito só com HTML, CSS e um pouco de JavaScript (seção "Protótipo" no final).

**O que este projeto não é:**
- Não é um sistema oficial e não tem ligação com a FATEC, o Centro Paula Souza ou o SIGA real.
- O protótipo não se conecta ao SIGA: o login aceita qualquer coisa (inclusive campos vazios) e todos os dados são fixos, escritos no código.
- A persona e as suposições ainda não foram validadas com alunos reais.

**Como o documento está organizado:** Passos 1 a 7 (o processo de Design Thinking) e, no final, a seção "Protótipo".

> **Aviso sobre as fontes:** a pasta `prints/` não existia (ou estava vazia) no diretório, então a análise foi feita com as 7 imagens anexadas na conversa, numeradas de 1 a 7 na ordem em que foram enviadas. Tudo o que está aqui vem do que aparece nessas imagens. Quando eu suponho algo, escrevo **(suposição)**.
> Por privacidade, não reproduzi nome completo nem RA do aluno que aparecem nos prints.

---

## Passo 1 - Análise das telas

### 1.1 Print por print

| Print | Tela | O que o aluno consegue fazer (visível na imagem) |
|---|---|---|
| **1** | **Início / menu principal (seções abertas)** | Ver três blocos com atalhos: **Meu curso** (Notas, Faltas, Horário, Disciplinas, Histórico, Exame Final), **Solicitações** (Solicitar AE, Solicitar EP, Solicitar AC, Rev. Notas/Faltas, Regime Domiciliar, Rematrícula) e **Documentos** (Documentos). O bloco Solicitações tem um selo azul com o número **6**. Há uma barra inferior fixa com **Início, Meu Curso e Sair**, e um topo com ícones, "Olá, JOÃO" e foto. |
| **2** | **Consulta notas** | Ver a lista de disciplinas do semestre (7 cartões, cada um com código, nome e professor) e voltar pelo botão "Voltar". O subtítulo diz "Consulta de notas do semestre". **Nenhuma nota aparece nessa tela**; **(suposição)** é preciso clicar em uma disciplina para vê-las. |
| **3** | **Não é uma tela do SIGA** | É o enunciado do desafio (grupos de 3 a 5, 8 etapas). Não entra na análise do sistema. |
| **4** | **Avaliações** | Só aparece o título "Avaliações" e o texto "lista de avaliações agendadas neste mês". **A lista está vazia**. **(Suposição):** a tela está incompleta ou não havia provas no mês. A barra inferior tem só **Meu Curso** e **Sair** (sem "Início"). |
| **5** | **Início / menu principal (seções fechadas) + cartão do aluno** | Abrir ou fechar os três blocos (Meu curso, Solicitações, Documentos). Abaixo, um cartão com dados do aluno: iniciais, nome, RA, **Status** (Em Curso), **Curso** (Tecnologia em Desenvolvimento de Software Multiplataforma), **Turno** (Tarde) e **Unidade** (Faculdade de Tecnologia de Praia Grande). Há um link "Ver índices e prazos". |
| **6** | **Faltas** ("Faltas no semestre") | Ver, para cada disciplina, o professor, **Aulas, Presença, Ausencia e Frequência (%)**. Cinco das seis disciplinas visíveis mostram um **ícone de alerta (triângulo)** ao lado da frequência (66.7, 71.4, 42.9, 50.0 e 57.1). Só a que está em 100.0 não tem o ícone. A tela está cortada embaixo. |
| **7** | **Horário das aulas** | Ver a grade da semana, de segunda a sexta, com um bloco por aula contendo código, nome da disciplina, professor e horário (ex.: 15:00-15:50). A imagem está cortada na sexta-feira. |

### 1.2 O que o SIGA oferece ao aluno (resumo)

Pelos prints, o SIGA permite:

- **Acompanhar o semestre:** notas (print 2), faltas e frequência (print 6), horário (print 7) e avaliações do mês (print 4).
- **Fazer pedidos:** o menu Solicitações lista AE, EP, AC, revisão de notas/faltas, regime domiciliar e rematrícula (print 1). Só vemos os botões, não os formulários.
- **Consultar documentos:** o menu tem "Documentos" (print 1). O subtítulo diz "Declarações e outros cursos" (print 5).
- **Ver dados acadêmicos:** cartão com status, curso, turno e unidade (print 5).
- Os itens **Disciplinas, Histórico e Exame Final** aparecem no menu (print 1), mas **não sabemos o que há dentro deles**.

### 1.3 Pontos fracos de usabilidade e experiência

**Navegação**

1. **Barra inferior inconsistente:** nos prints 1, 2, 5, 6 e 7 ela tem 3 itens (Início, Meu Curso, Sair). No print 4 (Avaliações) o "Início" some. O aluno perde o caminho de volta.
2. **"Início" e "Meu Curso" parecem a mesma coisa.** O nome "Meu curso" aparece na barra de baixo e também como bloco no menu (prints 1 e 5). **(Suposição):** os dois levam ao mesmo menu, mas isso não fica claro.
3. **A tela de Avaliações não aparece no menu.** No print 1 os atalhos de Meu curso são Notas, Faltas, Horário, Disciplinas, Histórico e Exame Final. Não vi como o aluno chega em Avaliações. **(Suposição):** pode ser por algum ícone do topo, mas nada indica isso.
4. **O botão "Voltar" fica na ponta esquerda e o título na ponta direita** (prints 2 e 6). Em tela larga os dois ficam muito distantes.
5. **Tudo é separado por telas.** Para saber "como estou no semestre" é preciso abrir Notas, Faltas, Horário e Avaliações, um de cada vez (prints 2, 4, 6 e 7).

**Organização das informações**

6. **Notas exigem um clique extra e não mostram nenhuma nota** na lista (print 2). Já Faltas mostra os números direto no cartão (print 6). As duas telas usam o mesmo tipo de cartão, mas se comportam de forma diferente.
7. **Cartões com muito espaço vazio:** nos prints 2 e 6 metade de cada cartão é uma área cinza sem conteúdo. Isso ocupa espaço e obriga a rolar a tela (o print 6 mostra só 6 disciplinas, e o print 2 mostra 7).
8. **O horário repete informação:** no print 7, cada aula de 50 minutos repete código, nome e professor. Uma disciplina de 4 aulas seguidas vira 4 blocos iguais (ex.: IAL011 na segunda-feira).
9. **O horário não destaca o dia de hoje nem a próxima aula** (print 7), e não mostra sala ou local. **(Suposição):** pode existir em outra tela, mas nesta imagem não aparece.
10. **O cartão do aluno fica escondido embaixo** dos menus (print 5) e, com os menus abertos, é preciso rolar para chegar nele (print 1).
11. **A tela Avaliações vazia** (print 4) não explica por que está vazia nem oferece um próximo passo.

**Clareza dos textos**

12. **Siglas sem explicação:** "AE, EP, AC" (prints 1 e 5). Um aluno novo pode não saber o que são. **(Suposição):** veteranos provavelmente sabem.
13. **Selo "6" em Solicitações sem explicação** (prints 1 e 5). Não dá para saber se são pedidos abertos, respondidos ou novidades.
14. **Alerta de frequência sem explicação** (print 6): o triângulo aparece em 5 disciplinas, sem legenda, sem dizer qual é o limite e sem indicar quantas faltas ainda podem ser feitas. O mesmo triângulo aparece em 71.4 e em 42.9, então o aluno não distingue "atenção" de "reprovado por falta". **(Suposição):** o alerta indica frequência abaixo do mínimo, que costuma ser 75% no ensino superior. Isso precisa ser confirmado.
15. **Termos inconsistentes:** o menu diz "Faltas", mas a tela usa "Ausencia" (sem acento) e "Presença" (print 6). O texto da tela de Avaliações está em minúsculas, como texto provisório (print 4). "Ver índices e prazos" (print 5) é vago.
16. **Nos números de Aulas** (ex.: 24 aulas, 16 presenças), não fica claro se é o total do semestre ou só o que já aconteceu (print 6). **(Suposição):** as aulas dadas até agora.

**Fluxos confusos**

17. **Solicitações reúne muitos pedidos diferentes** (rematrícula, regime domiciliar, revisão de notas...) com o mesmo peso visual (print 1). Não há indicação do que é urgente ou de prazo.
18. **"Rev. Notas/Faltas" (revisar notas e faltas) está longe das telas de notas e faltas** (print 1 versus prints 2 e 6). Quem vê uma falta errada precisa voltar ao menu e procurar a solicitação. **(Suposição):** não existe atalho na tela de Faltas, pois nada aparece nela.

**Acessibilidade**

19. **Textos pequenos e cinza claro** sobre fundo branco: subtítulos dos menus, "Docente", rótulos do cartão do aluno (prints 1 e 5). Podem ter baixo contraste para quem tem baixa visão.
20. **Ícones do topo sem texto** (documento e sino, prints 1 a 7). Não dá para saber o que fazem sem clicar.
21. **Um cartão preto no meio dos cartões cinza** (ING086 no print 2). **(Suposição):** é o efeito de passar o mouse. Se for isso, a cor muda muito, e em telas de toque esse estado pode não existir.
22. **Ponto positivo:** o alerta de frequência usa **ícone e não só cor** (print 6), o que ajuda quem não distingue cores. Falta o texto explicativo.
23. **Ponto positivo:** o texto branco na barra escura de baixo tem bom contraste (todos os prints).

**Visual**

24. **A tela inicial é limpa e organizada em blocos** (prints 1 e 5), com ícones que ajudam a reconhecer cada item. Esse é um ponto forte.
25. **Aparência pouco consistente entre telas:** a tela inicial usa cartões brancos com ícones azuis (print 1); Notas e Faltas usam cartões cinza-azulados escuros (prints 2 e 6); Horário usa uma tabela (print 7); Avaliações é uma página em branco (print 4).
26. **Na tela de Horário, o título "Horário" aparece sobreposto pela barra do topo** (print 7). **(Suposição):** é um efeito de rolagem ou barra fixa. Se for um defeito do site, atrapalha a leitura.
27. **No desktop, o menu fica em uma coluna estreita no meio** e a barra inferior espalha os itens nas pontas (prints 1, 5 e 6). Sobra muito espaço em branco dos lados.

**Uso no celular**

Todos os prints parecem ser de **computador (tela larga)**. Sobre o celular, só posso fazer suposições:

28. **(Suposição)** A barra inferior e os menus em cartões parecem pensados para celular, o que é bom (todos os prints).
29. **(Suposição)** A tabela de horário tem até 6 colunas por dia (print 7). Em uma tela pequena isso provavelmente forçaria rolagem para o lado ou deixaria o texto minúsculo.
30. **(Suposição)** As listas de cartões de Notas e Faltas (prints 2 e 6) ficariam muito longas em uma coluna só, por causa da área cinza vazia de cada cartão.
31. **(Suposição)** Para saber a situação do semestre pelo celular (ex.: no ônibus), o aluno teria que fazer vários toques por tela. **Isso deve ser testado com alunos reais.**

---

## Passo 2 - Problema real

### 2.1 Três problemas que afetam os alunos

**Problema A - O aluno não consegue saber, de forma rápida e clara, se está em risco por faltas.**
Na tela de Faltas, 5 das 6 disciplinas visíveis têm um alerta, mas sem legenda, sem o limite e sem dizer quantas faltas ainda restam (print 6). Para ver a situação geral, ele ainda precisa abrir outras telas (prints 2, 4 e 7).

**Problema B - O aluno não entende as solicitações (siglas, selo "6" e prazos) e não sabe qual pedido fazer.**
"AE, EP, AC" não são explicados, o selo 6 não tem significado claro, e não aparecem prazos nem status na tela (prints 1 e 5).

**Problema C - O aluno tem dificuldade de consultar a rotina do semestre (horário e avaliações) de forma rápida, principalmente pelo celular.**
O horário repete informações e usa tabela larga (print 7), e a tela de Avaliações está vazia (print 4). **(Suposição):** no celular a situação é pior, mas não há print de celular para confirmar.

### 2.2 Problema escolhido: **C** (com foco no calendário de aulas)

**Justificativa:**
- **É uma necessidade do dia a dia:** o aluno consulta o horário toda semana, várias vezes, e não só em momentos de problema.
- **Tem evidência direta nos prints:** o horário repete código, disciplina e professor em cada aula de 50 minutos, em uma tabela com até 6 colunas por dia (print 7). A tela de Avaliações está vazia e sem explicação (print 4).
- **Os horários mudam de um dia para o outro** (print 7): por exemplo, segunda e terça começam às 15:00 e quarta e quinta começam às 13:10. Fica difícil decorar e é fácil se enganar.
- **É viável de prototipar**, porque usa dados que o SIGA já mostra (disciplina, professor e horário) e a estrutura de telas já existente.
- **Limite da análise:** todos os prints são de computador. **(Suposição):** no celular a situação é pior, mas não há print de celular para confirmar. Isso precisa ser verificado nas entrevistas e em testes.

---

## Passo 3 - 5 perguntas de entrevista

Perguntas abertas, sem induzir resposta, sobre o problema escolhido (consultar o calendário de aulas e a rotina do semestre):

1. **Me conta como você descobre, no dia a dia, que horas suas aulas começam e terminam. O que você faz para isso?**
2. **Pense na última vez que você precisou consultar seu horário ou uma prova. Onde você estava, o que usou e como foi?**
3. **Como você fica sabendo das datas de provas e trabalhos? O que você faz para não esquecer?**
4. **Me conta uma situação em que você se confundiu com um horário, uma aula ou uma data. O que aconteceu e o que você fez depois?**
5. **Se você pudesse mudar qualquer coisa na forma como vê sua rotina de aulas, o que seria e por quê?**

> **Dica de aplicação:** deixar o aluno falar, pedir "pode me dar um exemplo?" e "por que isso foi importante?", e não sugerir soluções durante a entrevista. Vale perguntar também com qual aparelho ele costuma abrir o SIGA, sem sugerir a resposta.

---

## Passo 4 - Persona

> **Atenção: esta persona é hipotética.** Foi criada a partir dos prints e de suposições, não de entrevistas reais. Ela deve ser **ajustada depois das entrevistas com alunos reais**.

**Nome:** Roberto Carlos
**Idade:** 21 anos
**Curso:** Tecnologia em Desenvolvimento de Software Multiplataforma (3º semestre, turno da tarde)
**Unidade:** FATEC (baseada no cartão do print 5, mas hipotética)

**Rotina:**
Trabalha de manhã em um estágio e vai de ônibus para a faculdade depois do almoço. Como os horários de aula mudam durante a semana (algumas aulas começam às 13h10 e outras às 15h), ele precisa reorganizar o trabalho e o transporte a cada dia. À noite faz os trabalhos em grupo, quase sempre pelo celular. Nos fins de semana estuda, descansa e joga com os amigos.

**Objetivos:**
- Chegar na hora certa em cada aula, sem perder o início nem se atrasar por causa do estágio.
- Não ser pego de surpresa por provas e entregas.
- Conciliar estágio e faculdade sem perder disciplinas.

**Frustrações:**
- Para ver o horário, precisa abrir uma tabela grande, com informações repetidas, e procurar o dia.
- Não encontra as provas do mês em um só lugar: a tela de Avaliações do SIGA aparece vazia.
- Já chegou tarde (ou cedo demais) porque confundiu o horário de um dia com o de outro.
- Acaba tirando print do horário ou anotando em outro aplicativo, porque abrir o SIGA é trabalhoso.

**Comportamento com tecnologia:**
Usa o celular para quase tudo (aplicativo de banco, transporte, mensagens, agenda). Está acostumado com aplicativos que mostram o essencial logo ao abrir e com notificações. Tem pouca paciência com telas que exigem zoom, rolagem para o lado ou muitos cliques. Usa o notebook para programar, mas consulta o SIGA principalmente no celular, entre uma tarefa e outra. **(Suposição)**

**Frase que a representa:**
> "Eu só preciso saber que horas é minha próxima aula e se tenho prova essa semana, sem ter que caçar na tabela."

---

## Passo 5 - Definição do problema

**Frase-problema:**

> **Roberto precisa de um calendário de aulas e avaliações simples e rápido de consultar, principalmente pelo celular, porque hoje o horário aparece em uma tabela larga com informações repetidas e a tela de Avaliações está vazia, então ele acaba criando os próprios lembretes fora do SIGA.**

**Pergunta "Como podemos...?":**

> **Como podemos ajudar o Roberto a ver, em poucos segundos e pelo celular, quais são as próximas aulas e avaliações da semana?**

---

## Passo 6 - 10 ideias de solução

**Ideias simples**

1. **Juntar aulas seguidas da mesma disciplina em um só bloco:** em vez de 4 blocos iguais, aparece um só com "15:00 às 18:30".
2. **Destacar o dia de hoje e a próxima aula:** o dia atual fica em evidência e a próxima aula aparece no topo da tela.
3. **Mostrar um dia por vez no celular:** uma lista vertical do dia, com botões de "dia anterior" e "próximo dia" no lugar da tabela larga.
4. **Cor por disciplina:** cada disciplina tem uma cor fixa, o que ajuda a reconhecer a aula rapidamente.
5. **Mensagem clara na tela de Avaliações:** quando não houver provas, mostrar "Nenhuma avaliação neste mês" e um caminho para o calendário completo.

**Ideias intermediárias**

6. **Calendário único de aulas e avaliações:** visão de dia, semana e mês, com as provas marcadas ao lado das aulas.
7. **Mostrar sala ou local da aula:** **(suposição)** se essa informação existir no sistema, mostrá-la junto do horário.
8. **Lembretes de provas por notificação ou e-mail:** aviso alguns dias antes e na véspera de cada avaliação.

**Ideias ousadas**

9. **Exportar para a agenda do celular:** botão para levar o horário e as provas para o Google Agenda ou outro aplicativo de calendário (arquivo .ics ou link de assinatura).
10. **Aplicativo instalável com acesso offline e widget:** o horário fica salvo no aparelho e um widget na tela inicial mostra a próxima aula, mesmo sem internet.

---

## Passo 7 - Solução escolhida

### Ideia escolhida

**Calendário "Minha semana"** (junção das ideias 1, 2, 3 e 6): uma tela que mostra o horário de forma mais limpa (aulas seguidas agrupadas), destaca hoje e a próxima aula, e coloca as avaliações no mesmo calendário. No celular, mostra um dia por vez.

### Justificativa

- **Impacto:** responde direto ao problema (ver aulas e provas em poucos segundos). Reduz o risco de se confundir com horários e de esquecer provas, e evita que o aluno precise criar lembretes fora do SIGA.
- **Viabilidade:** usa dados que o SIGA já tem (disciplina, professor e horário, print 7). A ideia de agrupar aulas seguidas é uma regra simples de exibição. **(Suposição):** a data das avaliações precisa existir no sistema para alimentar o calendário. Isso deve ser confirmado, porque o print 4 mostra a tela vazia.
- **Adequação ao SIGA:** mantém o mesmo padrão de cartões, o botão "Voltar" e a barra inferior, e substitui as telas de Horário e Avaliações (prints 7 e 4) sem criar um sistema novo. O acesso continua pelo menu "Meu curso" (print 1).

### As 3 telas necessárias

1. **Início com cartão "Hoje":** a tela inicial ganha, no topo, um cartão com a próxima aula, o horário e as avaliações dos próximos dias.
2. **Calendário "Minha semana":** mostra o horário por dia (um dia por vez no celular, semana inteira no computador), com aulas seguidas agrupadas, o dia de hoje em destaque e as avaliações marcadas.
3. **Detalhe da aula ou avaliação:** ao tocar em um item, mostra a disciplina, o professor, o horário e a data, com botões para ativar lembrete e adicionar à agenda do celular.

> **Próximos passos:** validar a persona com entrevistas reais, confirmar se as datas de avaliações existem no sistema e testar o protótipo com outro grupo (etapa 8 do desafio).

---

## Protótipo (HTML, CSS e JS)

Depois dos 7 passos, foi criado um protótipo navegável para mostrar a ideia funcionando. Ele usa só HTML, CSS e JavaScript simples (sem bibliotecas e sem servidor). Para ver, basta abrir o arquivo `index.html` no navegador.

### Telas feitas

| Arquivo | Tela | O que faz |
|---|---|---|
| `index.html` | **Login** | Botão "Entrar" leva direto para o início, mesmo com os campos vazios. O acesso é sempre como Roberto Carlos. |
| `inicio.html` | **Início** | Mostra os blocos Meu curso, Solicitações (com o selo 6) e Documentos, e o card do aluno com os dados dele. Cada bloco abre e fecha com animação. |
| `horario.html` | **Horário das aulas** | Mostra a semana de segunda a sexta, com as aulas seguidas agrupadas em um bloco só. |

### Como o protótipo responde ao problema

- **Horário sem repetição:** quatro aulas seguidas da mesma disciplina viram um bloco só (ex.: "15:00 a 18:30, 4 aulas"), corrigindo o ponto de que o horário repete informações (print 7).
- **Celular primeiro:** no celular e no tablet o horário mostra **um dia por vez**, com abas Seg a Sex. No computador aparece a semana inteira em 5 colunas.
- **Dia de hoje em destaque:** a aba e o dia atual ficam marcados. Aos sábados e domingos, abre a segunda-feira.
- **Cor por disciplina** para reconhecer a aula rápido.
- **Card do aluno no topo no celular**, em vez de escondido no fim da página (ponto fraco 10 do Passo 1).
- **Dropdowns:** abrir um bloco fecha os outros. No computador o card do aluno é independente; no celular ele também entra nessa regra.
- **Barra inferior** com Início, Horário e Sair (no SIGA atual, "Início" e "Meu Curso" parecem a mesma coisa).
- **Textos de 14px ou mais** (os pequenos do print eram um ponto fraco) e ícones sempre acompanhados de texto.

### Regras do protótipo

- Só as telas Login, Início e Horário existem. Os outros atalhos (Notas, Faltas, Solicitações etc.) e o clique na foto do perfil mostram o aviso "Esta tela não faz parte do protótipo".
- A foto do aluno vem de `img/roberto.png`. Enquanto esse arquivo não existir, aparecem as iniciais "RC".
- O login guarda só uma marca temporária no navegador. Se você abrir `inicio.html` ou `horario.html` sem entrar, volta para o login.
- As cores vêm dos prints: topo cinza-azulado, barra inferior escura, fundo lavanda, azul de destaque e faixa bege.

### Suposições e limites

- **Horários de sexta-feira inventados:** o print 7 corta a sexta. Só dá para ver 4 aulas de MET004 e 2 de ING086, então os horários dessa coluna (MET004 das 13:10 às 16:40 e ING086 das 16:50 às 18:30) são ilustrativos e precisam ser conferidos no SIGA.
- **RA fictício** (`0000000000000`), para não repetir o dado real que aparece no print.
- **Disciplinas e professores** vêm dos prints 2, 6 e 7. Os nomes dos professores foram escritos com letras maiúsculas e minúsculas, e não tudo em maiúsculas como no SIGA.
- **Não foi testado com alunos reais.** O uso no celular foi verificado só com o navegador simulando telas pequenas.

### Diferença em relação ao plano do Passo 7

O plano previa 3 telas: Início com cartão "Hoje", Calendário "Minha semana" e Detalhe da aula ou avaliação. O protótipo entregue tem **Login, Início e Horário**. Por isso, **ainda faltam**: o cartão "Hoje" na tela inicial, as avaliações dentro do calendário, a tela de detalhe com lembrete e o botão de adicionar à agenda.

### Arquivos do projeto

```
index.html      login
inicio.html     tela inicial
horario.html    horário das aulas
css/style.css   estilos (cores, layout e responsividade)
js/app.js       login de mentira, dropdowns, dia do horário e aviso rápido
img/            coloque aqui a foto roberto.png
desafio-fatec.md  este documento
```
