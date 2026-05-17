/**
 * Converte o título do livro no nome do arquivo de capa.
 * Ex: "Harry Potter e a Pedra Filosofal" → "harry_potter_e_a_pedra_filosofal"
 */
export function titleToFilename(titulo = "") {
  return titulo
    .normalize("NFD")                        // separa letras dos acentos
    .replace(/[̀-ͯ]/g, "")         // remove os acentos
    .toLowerCase()
    .trim()
    .replace(/[^a-z0-9\s]/g, "")            // remove pontuação e especiais
    .replace(/\s+/g, "_");                   // espaços → underline
}

/**
 * Retorna o caminho da imagem de capa.
 * As imagens ficam em /public/covers/ e são acessadas via /covers/nome.png
 */
export function getCoverUrl(titulo = "") {
  const filename = titleToFilename(titulo);
  return `/covers/${filename}.png`;
}
