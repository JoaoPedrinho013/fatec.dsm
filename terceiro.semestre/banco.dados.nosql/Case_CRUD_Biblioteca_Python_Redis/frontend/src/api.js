const API_URL = "http://127.0.0.1:8000";

export function createBasicToken(username, password) {
  return btoa(`${username}:${password}`);
}

async function parseResponse(response) {
  const contentType = response.headers.get("content-type") || "";
  const data = contentType.includes("application/json")
    ? await response.json()
    : await response.text();

  if (!response.ok) {
    const detail = typeof data === "object" ? data.detail : data;
    throw new Error(Array.isArray(detail) ? detail[0]?.msg : detail || "Erro na requisicao");
  }

  return data;
}

export async function apiRequest(path, options = {}, session = null) {
  const headers = {
    "Content-Type": "application/json",
    ...options.headers,
  };

  if (session?.token) {
    headers.Authorization = `Basic ${session.token}`;
  }

  const response = await fetch(`${API_URL}${path}`, {
    ...options,
    headers,
  });

  return parseResponse(response);
}

export const api = {
  cadastrar: (body) =>
    apiRequest("/auth/cadastro", {
      method: "POST",
      body: JSON.stringify(body),
    }),

  login: (session) => apiRequest("/auth/login", {}, session),
  listarUsuarios: (session) => apiRequest("/auth/usuarios", {}, session),
  deletarUsuario: (username, session) =>
    apiRequest(
      `/auth/usuarios/${username}`,
      {
        method: "DELETE",
      },
      session
    ),

  listarLivros: (session) => apiRequest("/livros", {}, session),
  buscarLivro: (id, session) => apiRequest(`/livros/${id}`, {}, session),

  criarLivro: (body, session) =>
    apiRequest(
      "/livros",
      {
        method: "POST",
        body: JSON.stringify(body),
      },
      session
    ),

  atualizarLivro: (id, body, session) =>
    apiRequest(
      `/livros/${id}`,
      {
        method: "PUT",
        body: JSON.stringify(body),
      },
      session
    ),

  deletarLivro: (id, session) =>
    apiRequest(
      `/livros/${id}`,
      {
        method: "DELETE",
      },
      session
    ),

  emprestar: (id, session) =>
    apiRequest(`/livros/${id}/emprestar`, { method: "POST" }, session),

  devolver: (id, session) =>
    apiRequest(`/livros/${id}/devolver`, { method: "POST" }, session),

  favoritar: (id, session) =>
    apiRequest(`/livros/${id}/favoritos`, { method: "POST" }, session),

  removerFavorito: (id, session) =>
    apiRequest(`/livros/${id}/favoritos`, { method: "DELETE" }, session),

  entrarEspera: (id, session) =>
    apiRequest(`/livros/${id}/espera`, { method: "POST" }, session),

  listarEmprestimos: (session) => apiRequest("/usuarios/me/emprestimos", {}, session),
  listarFavoritos: (session) => apiRequest("/usuarios/me/favoritos", {}, session),
  listarEspera: (session) => apiRequest("/usuarios/me/espera", {}, session),
  listarNotificacoes: (session) => apiRequest("/usuarios/me/notificacoes", {}, session),
  removerNotificacao: (index, session) =>
    apiRequest(
      `/usuarios/me/notificacoes/${index}`,
      {
        method: "DELETE",
      },
      session
    ),
};
