# AgendaAqui

Protótipo de um aplicativo que **centraliza empresas de beleza e estética** (manicure, cabeleireiro, barbearia, estética) e permite **encontrar, avaliar e agendar** serviços online, com mapa, avaliações em estrelas e comentários.

Projeto da disciplina de **Experiência do Usuário (UX)** — curso de Desenvolvimento de Software Multiplataforma (DSM).
Professor: Leandro Sanches · Equipe: **The Leaky Cauldron**

**Integrantes:** Caio Edimar Santos Silva, Geovanny Ferreira Elias e João Pedro de Oliveira

> 🔗 **Demonstração:** https://caioedimarsantossilva.github.io/agendaaqui/
> 🖼️ **Wireframes:** `wireframes.html`

## Problema

Quem busca serviços de estética hoje depende de indicações e redes sociais, e as empresas novas têm dificuldade de conseguir clientes. O AgendaAqui reúne todas na mesma página e deixa as **avaliações dos usuários** darem destaque a quem tem bom desempenho, inclusive empresas recém-chegadas.

**Público-alvo:** mulheres e homens que procuram empresas de estética próximas e as empresas que querem ser encontradas e receber agendamentos.

## Funcionalidades

- **Mapa com GPS:** localização das empresas, distância calculada a partir da posição do usuário (botão “Usar minha localização”).
- **Busca e filtros:** por nome, serviço e categoria; ordenação por melhor avaliação, distância ou preço; filtro por nota mínima; favoritos.
- **Avaliações:** nota em estrelas, gráfico de distribuição das notas e área de comentários, com formulário para publicar a própria avaliação.
- **Destaque por desempenho:** selos “Novo na plataforma” e “Em destaque”.
- **Agendamento em 4 etapas:** serviço → data e horário → dados → revisão e confirmação. Horários ocupados ficam indisponíveis.
- **Meus agendamentos:** lista de horários marcados, com cancelamento e opção de adicionar ao calendário (arquivo `.ics`).

## Acessibilidade e usabilidade

- Modo **claro e escuro** (segue o sistema e pode ser trocado manualmente).
- **Tamanho do texto** ajustável em três níveis.
- Navegação completa por **teclado**, foco visível e link “Pular para o conteúdo”.
- Compatível com **leitores de tela** (rótulos, regiões e avisos de mudança de etapa e de erro).
- Contraste alto, alvos de toque de pelo menos 44 px e respeito à preferência de menos animação.
- Mensagens de erro em texto, ligadas ao campo, com o foco movido para o primeiro erro.
- Layout responsivo (celular e computador). No celular o site vira um **modo app**: barra de abas fixa embaixo (Explorar, Agendamentos, Configurações), filtros em uma folha deslizante (botão “Filtros”), alternância Lista/Mapa flutuante e tela de **Configurações** com tamanho do texto e aparência (automático, claro ou escuro).

## Como usar

Abra o site pelo link da demonstração, ou rode localmente:

```bash
python -m http.server 5173
```

e acesse http://localhost:5173

## Tecnologias

HTML, CSS e JavaScript puros, sem build. Mapa com [Leaflet](https://leafletjs.com/) e dados do [OpenStreetMap](https://www.openstreetmap.org/copyright). Fontes Inter e Fraunces (Google Fonts).

## Observações

- Empresas, avaliações e horários são **fictícios**, criados para a demonstração.
- Agendamentos, favoritos e avaliações ficam salvos apenas no navegador de quem usa (`localStorage`). Para zerar entre testes, use uma janela anônima.
- O GPS requer HTTPS (o GitHub Pages já fornece) e a permissão do usuário.
- O mapa, as fontes e a biblioteca do mapa são carregados da internet.
