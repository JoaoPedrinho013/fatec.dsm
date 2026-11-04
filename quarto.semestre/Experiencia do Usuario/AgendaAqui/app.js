"use strict";
/* ================= Utilidades ================= */
const $ = (s, r = document) => r.querySelector(s);
const $$ = (s, r = document) => [...r.querySelectorAll(s)];
const esc = s => String(s).replace(/[&<>"']/g, c => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" }[c]));
const store = {
  get(k, d) { try { const v = localStorage.getItem(k); return v ? JSON.parse(v) : d; } catch (e) { return d; } },
  set(k, v) { try { localStorage.setItem(k, JSON.stringify(v)); } catch (e) { /* sem armazenamento */ } }
};
const ico = (n, cls = "") => `<svg class="i ${cls}" aria-hidden="true"><use href="#i-${n}"/></svg>`;
const money = v => "R$ " + v.toFixed(2).replace(".", ",").replace(",00", "");
const fmtDia = d => d.toLocaleDateString("pt-BR", { weekday: "short", day: "2-digit", month: "2-digit" }).replace(".", "");
const fmtDiaLongo = d => d.toLocaleDateString("pt-BR", { weekday: "long", day: "numeric", month: "long" });
const live = msg => { const l = $("#live"); l.textContent = ""; setTimeout(() => l.textContent = msg, 50); };
function toast(msg) {
  const t = document.createElement("div"); t.className = "toast"; t.textContent = msg; $("#toasts").append(t);
  live(msg); setTimeout(() => t.remove(), 4000);
}
function confirmar(titulo, texto, rotulo) {
  return new Promise(res => {
    const d = $("#dlg"); $("#dlg-t").textContent = titulo; $("#dlg-p").textContent = texto; $("#dlg-yes").textContent = rotulo;
    const fim = v => { d.close(); res(v); };
    $("#dlg-yes").onclick = () => fim(true); $("#dlg-no").onclick = () => fim(false);
    d.oncancel = () => res(false); d.showModal(); $("#dlg-no").focus();
  });
}

/* ================= Dados (fictícios) ================= */
const CORES = ["#9F1C48", "#27614E", "#7A4B12", "#3B4A8C", "#6B2E7E", "#0F6B76"];
const EMPRESAS = [
  { id: 1, nome: "Studio Bela Unha", cat: "Manicure", lat: -23.5505, lng: -46.6333, end: "Rua das Flores, 120 – Centro", tel: "(11) 3000-1001", novo: false,
    sobre: "Esmaltação em gel, alongamento e nail art. Ambiente climatizado e materiais esterilizados.", horario: "Seg a sáb, 9h às 18h",
    serv: [["Manicure", 35, 45], ["Pedicure", 40, 50], ["Manicure + Pedicure", 70, 90], ["Esmaltação em gel", 80, 60]],
    com: [["Ana Paula", 5, "Atendimento excelente e o esmalte durou mais de três semanas.", "2026-10-12"], ["Beatriz", 4, "Muito bom, só atrasou uns 10 minutos.", "2026-09-30"], ["Fernanda", 5, "Ambiente limpo e organizado.", "2026-09-18"]] },
  { id: 2, nome: "Barbearia do Zé", cat: "Barbearia", lat: -23.5560, lng: -46.6400, end: "Av. Central, 450 – Vila Nova", tel: "(11) 3000-1002", novo: false,
    sobre: "Corte clássico, degradê e barba com toalha quente. Atendimento com hora marcada.", horario: "Ter a sáb, 10h às 19h",
    serv: [["Corte masculino", 40, 40], ["Barba", 30, 30], ["Corte + Barba", 60, 60]],
    com: [["Carlos", 5, "Melhor corte da região, sem espera.", "2026-10-20"], ["Rafael", 5, "Pontual e preço justo.", "2026-10-02"], ["Lucas", 4, "Muito bom, voltarei.", "2026-08-25"]] },
  { id: 3, nome: "Espaço Cabelo & Cia", cat: "Cabeleireiro", lat: -23.5450, lng: -46.6250, end: "Rua Verde, 88 – Jardim", tel: "(11) 3000-1003", novo: false,
    sobre: "Cortes, escovas, mechas e coloração com produtos profissionais.", horario: "Seg a sáb, 9h às 18h",
    serv: [["Corte feminino", 70, 60], ["Escova", 50, 45], ["Coloração", 150, 120], ["Hidratação", 90, 60]],
    com: [["Marina", 4, "Gostei muito do resultado da coloração.", "2026-10-08"], ["Tatiana", 3, "Bom serviço, mas o salão estava cheio.", "2026-09-05"]] },
  { id: 4, nome: "Nova Estética Sol", cat: "Estética", lat: -23.5530, lng: -46.6450, end: "Rua Nova, 15 – Bairro Alto", tel: "(11) 3000-1004", novo: true,
    sobre: "Limpeza de pele, design de sobrancelha e depilação. Acabamos de abrir!", horario: "Seg a sex, 9h às 18h",
    serv: [["Limpeza de pele", 90, 60], ["Design de sobrancelha", 35, 30], ["Depilação", 45, 40]],
    com: [["Júlia", 5, "Empresa nova e já virou minha favorita.", "2026-10-25"], ["Paula", 5, "Super caprichosa e atenciosa.", "2026-10-22"]] },
  { id: 5, nome: "Pés & Mãos Express", cat: "Manicure", lat: -23.5600, lng: -46.6280, end: "Rua do Comércio, 300 – Centro", tel: "(11) 3000-1005", novo: false,
    sobre: "Manicure e pedicure rápidas, sem frescura e com preço acessível.", horario: "Seg a sáb, 8h às 17h",
    serv: [["Manicure", 25, 30], ["Pedicure", 30, 40]],
    com: [["Sônia", 3, "Barato, mas costuma lotar.", "2026-09-10"]] },
  { id: 6, nome: "Ateliê Fio de Ouro", cat: "Cabeleireiro", lat: -23.5480, lng: -46.6500, end: "Alameda das Rosas, 210 – Bairro Alto", tel: "(11) 3000-1006", novo: true,
    sobre: "Especialistas em cabelos cacheados e crespos: corte, finalização e tratamento.", horario: "Qua a sáb, 10h às 19h",
    serv: [["Corte cacheado", 110, 75], ["Finalização", 60, 45], ["Cronograma capilar", 130, 90]],
    com: [["Larissa", 5, "Finalmente alguém que entende de cacho!", "2026-10-28"]] }
];
const HORARIOS = ["09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00", "17:00"];

/* ================= Estado ================= */
let favs = store.get("aa_fav", []);
let agend = store.get("aa_agend", []);
let extras = store.get("aa_rev", {});           // avaliações feitas pelo usuário
let pos = { lat: -23.5505, lng: -46.6333, real: false };
let filtro = { q: "", cat: "Todos", ordem: "aval", min: "0", fav: false, view: "lista" };
let mapa = null, marcas = {};
let wiz = null;

const revs = e => [...(extras[e.id] || []), ...e.com];
const media = e => { const r = revs(e); return r.reduce((a, c) => a + c[1], 0) / r.length; };
function dist(e) {
  const R = 6371, rad = Math.PI / 180, dLa = (e.lat - pos.lat) * rad, dLo = (e.lng - pos.lng) * rad;
  const a = Math.sin(dLa / 2) ** 2 + Math.cos(pos.lat * rad) * Math.cos(e.lat * rad) * Math.sin(dLo / 2) ** 2;
  return 2 * R * Math.asin(Math.sqrt(a));
}
const precoMin = e => Math.min(...e.serv.map(s => s[1]));
const destaque = e => media(e) >= 4.7 && revs(e).length >= 1;
const cor = e => CORES[e.id % CORES.length];
const iniciais = n => n.replace(/[&]/g, "").split(/\s+/).filter(Boolean).slice(0, 2).map(p => p[0]).join("").toUpperCase();
const estrelas = n => `<span class="stars-row" aria-hidden="true">${[1, 2, 3, 4, 5].map(i => `<svg class="i ${i <= Math.round(n) ? "fill" : ""}" style="${i <= Math.round(n) ? "" : "opacity:.3"}"><use href="#i-star"/></svg>`).join("")}</span>`;
const fmtKm = d => d < 1 ? Math.round(d * 1000) + " m" : d.toFixed(1).replace(".", ",") + " km";

// disponibilidade determinística (simula agenda da empresa)
function ocupado(e, d, h) {
  if (agend.some(a => a.emp === e.id && a.data === chave(d) && a.hora === h && !a.cancelado)) return true;
  const n = (e.id * 31 + d.getDate() * 17 + d.getMonth() * 7 + parseInt(h) * 13) % 7;
  return n === 0 || n === 3;
}
const chave = d => d.toISOString().slice(0, 10);
function proximosDias(n = 10) {
  const r = []; const d = new Date(); d.setHours(12, 0, 0, 0);
  while (r.length < n) { d.setDate(d.getDate() + 1); if (d.getDay() !== 0) r.push(new Date(d)); }
  return r;
}
function proximoHorario(e) {
  for (const d of proximosDias(7)) for (const h of HORARIOS) if (!ocupado(e, d, h)) return `${fmtDia(d)} às ${h}`;
  return null;
}

/* ================= Tema e tamanho do texto ================= */
function temaAtual() {
  const t = document.documentElement.dataset.theme;
  return t || (matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light");
}
function aplicaTema() {
  const dark = temaAtual() === "dark";
  const b = $("#btn-theme");
  b.setAttribute("aria-pressed", dark); b.setAttribute("aria-label", dark ? "Modo escuro ativado. Mudar para claro" : "Modo claro ativado. Mudar para escuro");
  $("#theme-use").setAttribute("href", dark ? "#i-sun" : "#i-moon"); $("#theme-lbl").textContent = dark ? "Claro" : "Escuro";
  const salvo = document.documentElement.dataset.theme || "auto";
  $$("[data-tema]").forEach(x => x.setAttribute("aria-pressed", x.dataset.tema === salvo));
}
function setTema(v) { // "auto" | "light" | "dark"
  try {
    if (v === "auto") { delete document.documentElement.dataset.theme; localStorage.removeItem("aa_theme"); }
    else { document.documentElement.dataset.theme = v; localStorage.setItem("aa_theme", v); }
  } catch (e) { }
  aplicaTema();
}
$("#btn-theme").onclick = () => {
  const novo = temaAtual() === "dark" ? "light" : "dark";
  setTema(novo); live("Modo " + (novo === "dark" ? "escuro" : "claro") + " ativado");
};
const NOMES_TAM = ["normal", "grande", "muito grande"];
function aplicaTam() {
  const s = +(document.documentElement.dataset.size || 1); const b = $("#btn-size");
  b.setAttribute("aria-label", `Tamanho do texto: ${NOMES_TAM[s - 1]}. Clique para alterar`);
  $$("[data-tam]").forEach(x => x.setAttribute("aria-pressed", +x.dataset.tam === s));
}
function setTam(s) {
  document.documentElement.dataset.size = s; try { localStorage.setItem("aa_size", s); } catch (e) { }
  aplicaTam(); live("Tamanho do texto: " + NOMES_TAM[s - 1]);
}
$("#btn-size").onclick = () => setTam((+(document.documentElement.dataset.size || 1)) % 3 + 1);
matchMedia("(prefers-color-scheme: dark)").addEventListener?.("change", aplicaTema);

/* ================= Roteador ================= */
function rota() {
  const [, tela, a, b] = (location.hash || "#/").split("/");
  if (mapa && tela) { mapa.remove(); mapa = null; marcas = {}; }
  $$("[data-nav]").forEach(l => l.removeAttribute("aria-current"));
  const marca = n => $$(`[data-nav="${n}"]`).forEach(l => l.setAttribute("aria-current", "page"));
  let titulo = "AgendaAqui";
  if (!tela) { marca("home"); telaHome(); titulo = "Explorar serviços"; }
  else if (tela === "empresa") { const e = EMPRESAS.find(x => x.id === +a); if (e) { telaEmpresa(e); titulo = e.nome; } else telaHome(); }
  else if (tela === "agendar") { const e = EMPRESAS.find(x => x.id === +a); if (e) { telaAgendar(e, +b || 0); titulo = "Agendar em " + e.nome; } else telaHome(); }
  else if (tela === "meus") { marca("meus"); telaMeus(); titulo = "Meus agendamentos"; }
  else if (tela === "config") { marca("config"); telaConfig(); titulo = "Configurações"; }
  else telaHome();
  document.title = titulo + " — AgendaAqui";
  if (tela !== undefined && window.__navegou) { $("#main").focus({ preventScroll: true }); window.scrollTo(0, 0); }
  window.__navegou = true;
  atualizaBadge();
}
addEventListener("hashchange", rota);
function atualizaBadge() {
  const n = agend.filter(a => !a.cancelado && a.data >= chave(new Date())).length;
  [$("#nbook"), $("#nbook2")].forEach(b => { b.hidden = !n; b.textContent = n; b.setAttribute("aria-label", n + " agendamento(s) ativo(s)"); });
}

/* ================= TELA: Explorar ================= */
const OPC_ORDEM = [["aval", "Melhor avaliação"], ["dist", "Mais perto"], ["preco", "Menor preço"]];
const OPC_NOTA = [["0", "Qualquer"], ["4", "4 ou mais"], ["4.5", "4,5 ou mais"]];
function telaHome() {
  if (mapa) { mapa.remove(); mapa = null; marcas = {}; eu = null; }
  const cats = ["Todos", ...new Set(EMPRESAS.map(e => e.cat))];
  $("#main").innerHTML = `
  <section class="hero">
    <h1>Beleza e bem-estar, agendados em poucos toques</h1>
    <p>Encontre manicure, cabeleireiro, barbearia e estética perto de você, veja as avaliações de quem já foi e marque seu horário sem precisar de mensagem.</p>
  </section>
  <div class="fbar">
    <div class="searchrow">
      <div class="search"><label for="q" class="sr-only">Buscar por serviço ou empresa</label>${ico("search")}
        <input id="q" class="inp" type="search" placeholder="Buscar serviço ou empresa" autocomplete="off" value="${esc(filtro.q)}"></div>
      <button type="button" class="fbtn" id="fbtn" aria-haspopup="dialog">${ico("filter")}<span>Filtros</span><span class="badge-n" id="fcount" hidden></span></button>
    </div>
    <div class="filters">
      <div class="chips" role="group" aria-label="Categorias">${cats.map(c => `<button type="button" class="chip" aria-pressed="${filtro.cat === c}" data-c="${c}">${c}</button>`).join("")}</div>
      <div class="selects">
        <label>Ordenar por <select id="ordem" class="inp">${OPC_ORDEM.map(o => `<option value="${o[0]}">${o[1]}</option>`).join("")}</select></label>
        <label>Nota <select id="min" class="inp">${OPC_NOTA.map(o => `<option value="${o[0]}">${o[1]}</option>`).join("")}</select></label>
        <button type="button" class="chip" id="favf" aria-pressed="${filtro.fav}">${ico("heart")} Favoritos</button>
        <button type="button" class="btn ghost sm" id="gps">${ico("nav")} Usar minha localização</button>
      </div>
    </div>
  </div>
  <div class="seg" role="group" aria-label="Modo de visualização"><button type="button" data-v="lista" aria-pressed="${filtro.view === "lista"}">${ico("list")} Lista</button><button type="button" data-v="mapa" aria-pressed="${filtro.view === "mapa"}">${ico("map")} Mapa</button></div>
  <div class="split" data-view="${filtro.view}">
    <section class="results" aria-labelledby="res-t"><h2 id="res-t" class="sr-only">Resultados</h2><p class="count" id="count"></p><ul class="list" id="lista"></ul></section>
    <aside class="mapwrap" aria-label="Mapa das empresas"><div id="mapa" role="application" aria-label="Mapa interativo. As mesmas empresas estão na lista ao lado."></div></aside>
  </div>`;
  $("#ordem").value = filtro.ordem; $("#min").value = filtro.min;
  $("#q").oninput = e => { filtro.q = e.target.value; renderLista(); };
  $("#ordem").onchange = e => { filtro.ordem = e.target.value; renderLista(); };
  $("#min").onchange = e => { filtro.min = e.target.value; renderLista(); };
  $("#favf").onclick = () => { filtro.fav = !filtro.fav; renderLista(); };
  $$(".chips .chip").forEach(b => b.onclick = () => { filtro.cat = b.dataset.c; $$(".chips .chip").forEach(x => x.setAttribute("aria-pressed", x === b)); renderLista(); });
  $$(".seg button").forEach(b => b.onclick = () => {
    filtro.view = b.dataset.v; $(".split").dataset.view = filtro.view;
    $$(".seg button").forEach(x => x.setAttribute("aria-pressed", x === b));
    if (filtro.view === "mapa") setTimeout(ajustaMapa, 60); // o mapa estava oculto: recalcula tamanho e enquadramento
    window.scrollTo(0, 0);
  });
  $("#gps").onclick = localizar;
  $("#fbtn").onclick = abreFiltros;
  iniciaMapa(); renderLista();
}

/* Folha de filtros (modo app) */
function abreFiltros() {
  const d = $("#sheet");
  const grupo = (id, titulo, attr, ops) => `<div class="field"><h3 id="${id}">${titulo}</h3><div class="chips" role="group" aria-labelledby="${id}">${ops.map(o => `<button type="button" class="chip" data-${attr}="${o[0]}">${o[1]}</button>`).join("")}</div></div>`;
  d.innerHTML = `<div class="sheet-h"><h2 id="sheet-t">Filtros</h2><button type="button" class="iconbtn sheet-x" id="sh-x" aria-label="Fechar filtros">${ico("x")}</button></div>
  <div class="sheet-b">
    ${grupo("sh-o", "Ordenar por", "o", OPC_ORDEM)}
    ${grupo("sh-n", "Nota mínima", "n", OPC_NOTA)}
    <div class="field"><h3 id="sh-f">Mostrar</h3><div class="chips" role="group" aria-labelledby="sh-f"><button type="button" class="chip" id="sh-fav">${ico("heart")} Somente favoritos</button></div></div>
    <div class="field"><h3>Localização</h3><button type="button" class="btn ghost sm" id="sh-gps">${ico("nav")} Usar minha localização</button><p class="hint" id="sh-loc"></p></div>
  </div>
  <div class="sheet-f"><button type="button" class="btn ghost" id="sh-lim">Limpar</button><button type="button" class="btn" id="sh-ok"></button></div>`;
  const muda = () => renderLista();
  $$("[data-o]", d).forEach(b => b.onclick = () => { filtro.ordem = b.dataset.o; muda(); });
  $$("[data-n]", d).forEach(b => b.onclick = () => { filtro.min = b.dataset.n; muda(); });
  $("#sh-fav").onclick = () => { filtro.fav = !filtro.fav; muda(); };
  $("#sh-gps").onclick = localizar;
  $("#sh-lim").onclick = () => { filtro.ordem = "aval"; filtro.min = "0"; filtro.fav = false; muda(); };
  $("#sh-ok").onclick = $("#sh-x").onclick = () => d.close();
  d.onclick = ev => { if (ev.target === d) d.close(); };
  d.showModal(); sincronizaFiltros();
}
const nFiltros = () => (filtro.ordem !== "aval") + (filtro.min !== "0") + (filtro.fav ? 1 : 0);
function sincronizaFiltros() {
  const n = nFiltros(), fb = $("#fbtn");
  if (fb) {
    fb.dataset.on = n > 0; const c = $("#fcount"); c.hidden = !n; c.textContent = n;
    fb.setAttribute("aria-label", n ? `Filtros, ${n} ativo${n > 1 ? "s" : ""}` : "Filtros");
    $("#ordem").value = filtro.ordem; $("#min").value = filtro.min; $("#favf").setAttribute("aria-pressed", filtro.fav);
  }
  const d = $("#sheet"); if (!d.open) return;
  $$("[data-o]", d).forEach(b => b.setAttribute("aria-pressed", b.dataset.o === filtro.ordem));
  $$("[data-n]", d).forEach(b => b.setAttribute("aria-pressed", b.dataset.n === filtro.min));
  $("#sh-fav").setAttribute("aria-pressed", filtro.fav);
  $("#sh-lim").disabled = !n;
  $("#sh-loc").textContent = pos.real ? "Distâncias calculadas a partir da sua localização." : "Distâncias calculadas a partir do centro de São Paulo (região padrão).";
  const t = listaAtual.length; $("#sh-ok").textContent = t === 0 ? "Nenhum resultado" : t === 1 ? "Ver 1 resultado" : `Ver ${t} resultados`;
}
function localizar() {
  if (!navigator.geolocation) { toast("Seu navegador não permite localização. Usando uma região padrão."); return; }
  toast("Buscando sua localização…");
  navigator.geolocation.getCurrentPosition(p => {
    pos = { lat: p.coords.latitude, lng: p.coords.longitude, real: true }; renderLista(); toast("Localização atualizada. Distâncias recalculadas.");
  }, () => toast("Não conseguimos acessar sua localização. Verifique a permissão do navegador."), { timeout: 8000 });
}
let eu = null, listaAtual = [];
const mapaVisivel = () => !!mapa && $("#mapa").offsetWidth > 0;
function ajustaMapa() {
  if (!mapa || !mapaVisivel()) return;
  mapa.invalidateSize();
  mapa.fitBounds(L.latLngBounds([...listaAtual.map(e => [e.lat, e.lng]), [pos.lat, pos.lng]]).pad(.2), { maxZoom: 15 });
}
let _rz; addEventListener("resize", () => { clearTimeout(_rz); _rz = setTimeout(ajustaMapa, 150); });
function iniciaMapa() {
  if (typeof L === "undefined") { $("#mapa").innerHTML = '<p style="padding:1rem">Mapa indisponível sem conexão. Use a lista de empresas.</p>'; return; }
  mapa = L.map("mapa", { scrollWheelZoom: false }).setView([pos.lat, pos.lng], 13);
  L.tileLayer("https://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}", { maxZoom: 19, attribution: "Tiles © Esri" }).addTo(mapa);
  eu = L.marker([pos.lat, pos.lng], { icon: L.divIcon({ className: "", html: '<div class="me"></div>', iconSize: [16, 16] }), keyboard: false }).addTo(mapa).bindTooltip("Você está aqui");
}
function filtrar() {
  const q = filtro.q.trim().toLowerCase();
  let l = EMPRESAS.filter(e => (filtro.cat === "Todos" || e.cat === filtro.cat)
    && (!q || (e.nome + " " + e.cat + " " + e.serv.map(s => s[0]).join(" ")).toLowerCase().includes(q))
    && media(e) >= +filtro.min && (!filtro.fav || favs.includes(e.id)));
  const o = filtro.ordem;
  return l.sort((a, b) => o === "aval" ? media(b) - media(a) : o === "dist" ? dist(a) - dist(b) : precoMin(a) - precoMin(b));
}
function renderLista() {
  const l = filtrar(); listaAtual = l;
  $("#count").textContent = l.length === 1 ? "1 empresa encontrada" : l.length + " empresas encontradas";
  live($("#count").textContent);
  $("#lista").innerHTML = l.length ? l.map(cartao).join("") : `<li class="empty"><h3>Nada por aqui ainda</h3><p>Nenhuma empresa corresponde aos filtros escolhidos.</p><button class="btn ghost" id="limpar" type="button">Limpar filtros</button></li>`;
  $("#limpar") && ($("#limpar").onclick = () => { filtro = { ...filtro, q: "", cat: "Todos", ordem: "aval", min: "0", fav: false }; telaHome(); });
  $$(".fav").forEach(b => b.onclick = ev => {
    ev.stopPropagation(); const id = +b.dataset.id; const e = EMPRESAS.find(x => x.id === id);
    favs = favs.includes(id) ? favs.filter(x => x !== id) : [...favs, id]; store.set("aa_fav", favs);
    toast(favs.includes(id) ? e.nome + " salvo nos favoritos" : e.nome + " removido dos favoritos"); renderLista();
  });
  if (mapa) {
    Object.values(marcas).forEach(m => m.remove()); marcas = {};
    if (eu) eu.setLatLng([pos.lat, pos.lng]);
    l.forEach(e => {
      const m = L.marker([e.lat, e.lng], { icon: L.divIcon({ className: "", html: `<div class="pin">${money(precoMin(e))}</div>`, iconSize: [0, 0] }), title: e.nome, keyboard: false }).addTo(mapa);
      m.bindPopup(`<strong>${esc(e.nome)}</strong><br>${media(e).toFixed(1).replace(".", ",")} ★ · ${fmtKm(dist(e))}<br><a href="#/empresa/${e.id}">Ver detalhes</a>`);
      m.on("mouseover click", () => $(`#c${e.id}`)?.classList.add("sel")); m.on("mouseout popupclose", () => $(`#c${e.id}`)?.classList.remove("sel"));
      marcas[e.id] = m;
    });
    ajustaMapa();
  }
  sincronizaFiltros();
}
function cartao(e) {
  const r = revs(e).length, prox = proximoHorario(e), f = favs.includes(e.id);
  return `<li class="card" id="c${e.id}">
    <div class="avatar" style="background:${cor(e)}" aria-hidden="true">${iniciais(e.nome)}</div>
    <div class="card-body">
      <h3><a href="#/empresa/${e.id}">${esc(e.nome)}</a></h3>
      <div class="meta"><span class="rate">${ico("star", "fill")}${media(e).toFixed(1).replace(".", ",")}<span class="sr-only"> de 5,</span></span><span>${r} avaliaç${r === 1 ? "ão" : "ões"}</span><span>${e.cat}</span><span>${ico("pin")} ${fmtKm(dist(e))}</span></div>
      <div class="meta" style="margin-top:.2rem"><span>A partir de <strong style="color:var(--ink)">${money(precoMin(e))}</strong></span>${prox ? `<span>${ico("clock")} Próximo: ${prox}</span>` : ""}</div>
      <div class="tags">${e.novo ? '<span class="tag new">Novo na plataforma</span>' : ""}${destaque(e) ? '<span class="tag destaque">Em destaque</span>' : ""}</div>
    </div>
    <button class="fav" type="button" data-id="${e.id}" aria-pressed="${f}" aria-label="Favoritar ${esc(e.nome)}">${ico("heart")}</button>
  </li>`;
}

/* ================= TELA: Configurações ================= */
function telaConfig() {
  const tema = [["auto", "Automático"], ["light", "Claro"], ["dark", "Escuro"]];
  $("#main").innerHTML = `<h1>Configurações</h1>
  <section class="panel" aria-labelledby="cf-tam"><h2 id="cf-tam">Tamanho do texto</h2>
    <p class="hint">Aumente as letras para ler com mais conforto.</p>
    <div class="opts" role="group" aria-labelledby="cf-tam">${NOMES_TAM.map((n, i) => `<button type="button" class="optb" data-tam="${i + 1}"><span class="aa" aria-hidden="true" style="font-size:${[18, 23, 28][i]}px">Aa</span><span>${n[0].toUpperCase() + n.slice(1)}</span></button>`).join("")}</div>
    <p class="prev">Assim fica o texto do aplicativo: agende seu horário em poucos toques.</p></section>
  <section class="panel" aria-labelledby="cf-tema"><h2 id="cf-tema">Aparência</h2>
    <p class="hint">“Automático” segue o modo do seu celular.</p>
    <div class="opts" role="group" aria-labelledby="cf-tema">${tema.map(t => `<button type="button" class="optb" data-tema="${t[0]}"><span>${t[1]}</span></button>`).join("")}</div></section>
  <section class="panel" aria-labelledby="cf-sobre"><h2 id="cf-sobre">Sobre</h2>
    <p>AgendaAqui — protótipo acadêmico do Desafio de UX (DSM). Empresas, avaliações e horários são fictícios. Mapa: © Esri.</p>
    <p><a href="wireframes.html">Ver wireframes</a></p></section>`;
  $$("[data-tam]").forEach(b => b.onclick = () => setTam(+b.dataset.tam));
  $$("[data-tema]").forEach(b => b.onclick = () => { setTema(b.dataset.tema); live("Aparência: " + b.textContent); });
  aplicaTam(); aplicaTema();
}

/* ================= TELA: Empresa ================= */
function telaEmpresa(e) {
  const rs = revs(e), m = media(e);
  const cont = [5, 4, 3, 2, 1].map(n => [n, rs.filter(r => r[1] === n).length]);
  const f = favs.includes(e.id);
  $("#main").innerHTML = `
  <a class="crumb" href="#/">${ico("back")} Voltar para a lista</a>
  <div class="detail-head">
    <div class="avatar" style="background:${cor(e)}" aria-hidden="true">${iniciais(e.nome)}</div>
    <div class="grow"><h1 style="margin-bottom:.25rem">${esc(e.nome)}</h1>
      <div class="meta"><span class="rate">${ico("star", "fill")}${m.toFixed(1).replace(".", ",")}</span><span>${rs.length} avaliações</span><span>${e.cat}</span><span>${ico("pin")} ${fmtKm(dist(e))}</span></div>
      <div class="tags">${e.novo ? '<span class="tag new">Novo na plataforma</span>' : ""}${destaque(e) ? '<span class="tag destaque">Em destaque</span>' : ""}</div></div>
    <button class="btn ghost" id="favd" type="button" aria-pressed="${f}">${ico("heart")} <span>${f ? "Nos favoritos" : "Favoritar"}</span></button>
  </div>
  <div class="cols">
    <div>
      <section class="panel" aria-labelledby="s-serv"><h2 id="s-serv">Serviços e preços</h2>
        ${e.serv.map((s, i) => `<div class="serv"><div><strong>${esc(s[0])}</strong><div class="meta">${ico("clock")} ${s[2]} min</div></div>
          <div style="display:flex;gap:.9rem;align-items:center;flex-wrap:wrap"><span class="price">${money(s[1])}</span><a class="btn sm" href="#/agendar/${e.id}/${i}" aria-label="Agendar ${esc(s[0])} por ${money(s[1])}">Agendar</a></div></div>`).join("")}
      </section>
      <section class="panel" aria-labelledby="s-av"><h2 id="s-av">Avaliações</h2>
        <div style="display:flex;gap:1.5rem;flex-wrap:wrap;align-items:center">
          <div><div class="big">${m.toFixed(1).replace(".", ",")}</div>${estrelas(m)}<div class="hint">${rs.length} avaliações</div></div>
          <div class="bars" style="flex:1;min-width:220px">${cont.map(([n, c]) => `<div class="bar-row"><span>${n} estrelas</span><div class="bar" aria-hidden="true"><i style="width:${rs.length ? c / rs.length * 100 : 0}%"></i></div><span>${c}</span></div>`).join("")}</div>
        </div>
        <div id="revs">${rs.map(r => `<article class="review"><header><strong>${esc(r[0])}</strong><span class="hint">${new Date(r[3] + "T12:00").toLocaleDateString("pt-BR")}</span></header>
          <div>${estrelas(r[1])}<span class="sr-only">Nota ${r[1]} de 5</span></div><p style="margin-top:.35rem">${esc(r[2])}</p></article>`).join("")}</div>
      </section>
      <section class="panel" aria-labelledby="s-nova"><h2 id="s-nova">Deixe sua avaliação</h2>
        <form id="fr" novalidate>
          <fieldset class="field"><legend>Sua nota</legend>
            <div class="starpick">${[5, 4, 3, 2, 1].map(n => `<input type="radio" name="nota" id="n${n}" value="${n}"><label for="n${n}">${n} estrela${n > 1 ? "s" : ""}<svg class="i" aria-hidden="true"><use href="#i-star"/></svg></label>`).join("")}</div>
            <p class="err" id="e-nota"></p></fieldset>
          <div class="field"><label for="rn">Seu nome</label><input class="inp" id="rn" maxlength="40" autocomplete="name" aria-describedby="e-rn"><p class="err" id="e-rn"></p></div>
          <div class="field"><label for="rt">Comentário</label><textarea class="inp" id="rt" rows="4" maxlength="400" aria-describedby="h-rt e-rt"></textarea><p class="hint" id="h-rt">Conte como foi o atendimento (máx. 400 caracteres).</p><p class="err" id="e-rt"></p></div>
          <button class="btn" type="submit">Publicar avaliação</button>
        </form></section>
    </div>
    <aside class="sticky-aside"><section class="panel" aria-labelledby="s-info"><h2 id="s-info">Informações</h2>
      <p>${esc(e.sobre)}</p>
      <dl class="info"><div><dt>Endereço</dt><dd>${esc(e.end)}</dd></div><div><dt>Funcionamento</dt><dd>${esc(e.horario)}</dd></div><div><dt>Telefone</dt><dd><a href="tel:${e.tel.replace(/\D/g, "")}">${e.tel}</a></dd></div></dl>
      <p style="margin-top:1rem"><a class="btn ghost" style="width:100%" target="_blank" rel="noopener" href="https://www.openstreetmap.org/directions?to=${e.lat}%2C${e.lng}">${ico("nav")} Como chegar<span class="sr-only"> (abre em nova aba)</span></a></p>
      <p><a class="btn" style="width:100%" href="#/agendar/${e.id}/0">Agendar horário</a></p></section></aside>
  </div>`;
  $("#favd").onclick = () => { favs = favs.includes(e.id) ? favs.filter(x => x !== e.id) : [...favs, e.id]; store.set("aa_fav", favs); telaEmpresa(e); $("#favd").focus(); toast(favs.includes(e.id) ? "Salvo nos favoritos" : "Removido dos favoritos"); };
  $("#fr").onsubmit = ev => {
    ev.preventDefault(); const nota = $("input[name=nota]:checked"), nome = $("#rn").value.trim(), txt = $("#rt").value.trim(); const erros = [];
    const set = (id, msg, campo) => { $("#" + id).textContent = msg; if (campo) campo.setAttribute("aria-invalid", !!msg); if (msg) erros.push(campo || $("#n5")); };
    set("e-nota", nota ? "" : "Escolha uma nota de 1 a 5 estrelas.", null); if (!nota) erros.push($("#n5"));
    set("e-rn", nome ? "" : "Informe seu nome.", $("#rn")); set("e-rt", txt.length >= 10 ? "" : "Escreva pelo menos 10 caracteres.", $("#rt"));
    if (erros.length) { erros[0].focus(); live("O formulário tem erros. " + erros.length + " campo(s) precisam de atenção."); return; }
    extras[e.id] = [[nome, +nota.value, txt, chave(new Date())], ...(extras[e.id] || [])]; store.set("aa_rev", extras);
    telaEmpresa(e); toast("Avaliação publicada. Obrigado!"); $("#s-av").scrollIntoView({ block: "start" });
  };
}

/* ================= TELA: Agendar (4 passos) ================= */
function telaAgendar(e, si) {
  wiz = { emp: e.id, serv: Math.min(si, e.serv.length - 1), dia: null, hora: null, nome: wiz?.nome || "", tel: wiz?.tel || "", passo: 2 };
  desenhaPasso(e);
}
const NOMES_PASSO = ["Serviço", "Data e horário", "Seus dados", "Revisão"];
function desenhaPasso(e, foco = false) {
  const p = wiz.passo, s = e.serv[wiz.serv];
  const dias = proximosDias(10);
  const stepper = `<ol class="steps" aria-label="Etapas do agendamento">${NOMES_PASSO.map((n, i) => `<li class="${i + 1 < p ? "done" : i + 1 === p ? "cur" : ""}" ${i + 1 === p ? 'aria-current="step"' : ""}>${i + 1}. ${n}</li>`).join("")}</ol>`;
  let corpo = "", titulo = "";
  if (p === 1) {
    titulo = "Escolha o serviço";
    corpo = `<fieldset class="field"><legend class="sr-only">Serviço</legend>${e.serv.map((x, i) => `<label class="opt"><input type="radio" name="sv" value="${i}" ${i === wiz.serv ? "checked" : ""}><span class="grow"><strong>${esc(x[0])}</strong><span class="hint" style="display:block">${x[2]} min</span></span><span class="price">${money(x[1])}</span></label>`).join("")}</fieldset>`;
  } else if (p === 2) {
    titulo = "Escolha o dia e o horário";
    corpo = `<div class="field"><h3 id="dia-t">Dia</h3><div class="days" role="group" aria-labelledby="dia-t">${dias.map((d, i) => { const livre = HORARIOS.some(h => !ocupado(e, d, h)); return `<button type="button" class="slot" data-d="${i}" aria-pressed="${wiz.dia === chave(d)}" ${livre ? "" : "disabled"}>${fmtDia(d).split(",")[0]}<small>${fmtDia(d).split(",")[1] || ""}</small></button>`; }).join("")}</div></div>
    <div class="field"><h3 id="hr-t">Horário</h3><div class="times" role="group" aria-labelledby="hr-t" id="times"></div><p class="err" id="e-slot"></p></div>`;
  } else if (p === 3) {
    titulo = "Seus dados";
    corpo = `<div class="field"><label for="cn">Nome completo</label><input class="inp" id="cn" autocomplete="name" value="${esc(wiz.nome)}" aria-describedby="e-cn"><p class="err" id="e-cn"></p></div>
    <div class="field"><label for="ct">Telefone com DDD</label><input class="inp" id="ct" inputmode="tel" autocomplete="tel" placeholder="(11) 90000-0000" value="${esc(wiz.tel)}" aria-describedby="h-ct e-ct"><p class="hint" id="h-ct">Usado só para a empresa confirmar seu horário.</p><p class="err" id="e-ct"></p></div>`;
  } else if (p === 4) {
    const d = dias.find(x => chave(x) === wiz.dia) || new Date(wiz.dia + "T12:00");
    titulo = "Revise e confirme";
    corpo = `<div class="summary"><div><span>Empresa</span><strong>${esc(e.nome)}</strong></div><div><span>Serviço</span><strong>${esc(s[0])}</strong></div><div><span>Data</span><strong>${fmtDiaLongo(d)}</strong></div><div><span>Horário</span><strong>${wiz.hora}</strong></div><div><span>Duração</span><strong>${s[2]} min</strong></div><div><span>Local</span><strong style="text-align:right">${esc(e.end)}</strong></div><div><span>Cliente</span><strong>${esc(wiz.nome)}</strong></div><div style="border-top:1px solid var(--line);margin-top:.4rem;padding-top:.6rem"><span>Total (pagamento no local)</span><strong class="price">${money(s[1])}</strong></div></div>
    <p class="hint">Você poderá cancelar em “Meus agendamentos”.</p>`;
  }
  $("#main").innerHTML = `<div class="wizard"><a class="crumb" href="#/empresa/${e.id}">${ico("back")} Cancelar e voltar</a>${stepper}
    <h1 id="ptitle" tabindex="-1" style="font-size:1.7rem">${titulo}</h1><p class="hint" style="margin-bottom:1rem">${esc(e.nome)} · ${esc(s[0])} · ${money(s[1])}</p>
    <form id="fw" novalidate>${corpo}<div class="actions"><button class="btn ghost" type="button" id="prev" ${p <= 1 ? "hidden" : ""}>Voltar</button><button class="btn" type="submit" style="margin-left:auto">${p === 4 ? "Confirmar agendamento" : "Continuar"}</button></div></form></div>`;
  if (foco) { $("#ptitle").focus(); live(`Etapa ${p} de 4: ${titulo}`); }
  const rt = () => { const c = $("#e-slot"); return c; };
  function desenhaHoras() {
    const box = $("#times"); if (!box) return;
    if (!wiz.dia) { box.innerHTML = '<p class="hint">Escolha um dia para ver os horários disponíveis.</p>'; return; }
    const d = dias.find(x => chave(x) === wiz.dia);
    box.innerHTML = HORARIOS.map(h => `<button type="button" class="slot" data-h="${h}" aria-pressed="${wiz.hora === h}" ${ocupado(e, d, h) ? 'disabled aria-label="' + h + ', indisponível"' : ""}>${h}</button>`).join("");
    $$("#times .slot").forEach(b => b.onclick = () => { wiz.hora = b.dataset.h; $("#e-slot").textContent = ""; $$("#times .slot").forEach(x => x.setAttribute("aria-pressed", x === b)); });
  }
  if (p === 2) { desenhaHoras(); $$(".days .slot").forEach(b => b.onclick = () => { wiz.dia = chave(dias[+b.dataset.d]); wiz.hora = null; $$(".days .slot").forEach(x => x.setAttribute("aria-pressed", x === b)); desenhaHoras(); live(fmtDiaLongo(dias[+b.dataset.d]) + ", horários atualizados"); }); }
  if (p === 3) $("#ct").oninput = ev => { const n = ev.target.value.replace(/\D/g, "").slice(0, 11); ev.target.value = n.length > 10 ? `(${n.slice(0, 2)}) ${n.slice(2, 7)}-${n.slice(7)}` : n.length > 6 ? `(${n.slice(0, 2)}) ${n.slice(2, 6)}-${n.slice(6)}` : n.length > 2 ? `(${n.slice(0, 2)}) ${n.slice(2)}` : n; };
  $("#prev").onclick = () => { wiz.passo = Math.max(p === 2 ? 2 : 1, p - 1); if (p === 2) { location.hash = "#/empresa/" + e.id; return; } desenhaPasso(e, true); };
  $("#fw").onsubmit = ev => {
    ev.preventDefault();
    if (p === 1) wiz.serv = +($("input[name=sv]:checked")?.value ?? wiz.serv);
    if (p === 2) { if (!wiz.dia || !wiz.hora) { $("#e-slot").textContent = !wiz.dia ? "Escolha um dia." : "Escolha um horário."; live($("#e-slot").textContent); (wiz.dia ? $("#times .slot:not([disabled])") : $(".days .slot:not([disabled])"))?.focus(); return; } }
    if (p === 3) {
      const n = $("#cn").value.trim(), t = $("#ct").value.trim(); let primeiro = null;
      const set = (id, c, ok, msg) => { $("#" + id).textContent = ok ? "" : msg; c.setAttribute("aria-invalid", !ok); if (!ok && !primeiro) primeiro = c; };
      set("e-cn", $("#cn"), n.split(/\s+/).filter(Boolean).length >= 2, "Informe nome e sobrenome.");
      set("e-ct", $("#ct"), t.replace(/\D/g, "").length >= 10, "Informe um telefone válido com DDD.");
      if (primeiro) { primeiro.focus(); live("Corrija os campos destacados."); return; }
      wiz.nome = n; wiz.tel = t;
    }
    if (p === 4) { concluir(e); return; }
    wiz.passo = p + 1; desenhaPasso(e, true); window.scrollTo(0, 0);
  };
}
function concluir(e) {
  const s = e.serv[wiz.serv];
  const a = { id: Date.now(), emp: e.id, serv: s[0], preco: s[1], dur: s[2], data: wiz.dia, hora: wiz.hora, nome: wiz.nome, tel: wiz.tel, cancelado: false };
  agend.push(a); store.set("aa_agend", agend);
  const d = new Date(a.data + "T12:00"); wiz = null; atualizaBadge();
  $("#main").innerHTML = `<div class="success"><div class="ok">${ico("check")}</div><h1 id="ptitle" tabindex="-1" style="font-size:1.8rem">Agendamento confirmado!</h1>
    <p><strong>${esc(a.serv)}</strong> em <strong>${esc(e.nome)}</strong><br>${fmtDiaLongo(d)} às ${a.hora}<br>${esc(e.end)}</p>
    <p class="hint">Chegue com 5 minutos de antecedência. Pagamento direto no local.</p>
    <div style="display:flex;gap:.75rem;justify-content:center;flex-wrap:wrap;margin-top:1.25rem"><button class="btn ghost" id="ics" type="button">${ico("cal")} Adicionar ao calendário</button><a class="btn" href="#/meus">Ver meus agendamentos</a></div></div>`;
  $("#ptitle").focus(); live("Agendamento confirmado"); window.scrollTo(0, 0);
  $("#ics").onclick = () => baixaICS(a, e);
}
function baixaICS(a, e) {
  const ini = new Date(a.data + "T" + a.hora + ":00"), fim = new Date(ini.getTime() + a.dur * 60000);
  const f = d => d.getFullYear() + String(d.getMonth() + 1).padStart(2, "0") + String(d.getDate()).padStart(2, "0") + "T" + String(d.getHours()).padStart(2, "0") + String(d.getMinutes()).padStart(2, "0") + "00";
  const txt = ["BEGIN:VCALENDAR", "VERSION:2.0", "PRODID:-//AgendaAqui//PT", "BEGIN:VEVENT", "UID:" + a.id + "@agendaaqui", "DTSTAMP:" + f(new Date()), "DTSTART:" + f(ini), "DTEND:" + f(fim), `SUMMARY:${a.serv} – ${e.nome}`, "LOCATION:" + e.end, "END:VEVENT", "END:VCALENDAR"].join("\r\n");
  const u = URL.createObjectURL(new Blob([txt], { type: "text/calendar" })), l = document.createElement("a"); l.href = u; l.download = "agendamento.ics"; l.click(); URL.revokeObjectURL(u);
}

/* ================= TELA: Meus agendamentos ================= */
function telaMeus() {
  const hoje = chave(new Date());
  const ord = [...agend].sort((a, b) => (a.data + a.hora).localeCompare(b.data + b.hora));
  const prox = ord.filter(a => !a.cancelado && a.data >= hoje), outros = ord.filter(a => a.cancelado || a.data < hoje);
  const item = a => {
    const e = EMPRESAS.find(x => x.id === a.emp), d = new Date(a.data + "T12:00");
    return `<li class="appt ${a.cancelado ? "past" : ""}"><div><h3 style="margin:0">${esc(a.serv)}</h3><div class="meta"><span>${esc(e.nome)}</span><span>${ico("cal")} ${fmtDiaLongo(d)}, ${a.hora}</span><span>${money(a.preco)}</span></div>${a.cancelado ? '<div class="tags"><span class="tag" style="background:var(--danger-soft);color:var(--danger)">Cancelado</span></div>' : ""}</div>
      <div style="display:flex;gap:.5rem;flex-wrap:wrap"><a class="btn ghost sm" href="#/empresa/${e.id}">Ver empresa</a>${!a.cancelado && a.data >= hoje ? `<button type="button" class="btn danger sm" data-cancel="${a.id}" aria-label="Cancelar ${esc(a.serv)} em ${esc(e.nome)}">Cancelar</button>` : ""}</div></li>`;
  };
  $("#main").innerHTML = `<h1>Meus agendamentos</h1>
  ${!agend.length ? `<div class="empty"><h2>Você ainda não tem agendamentos</h2><p>Escolha uma empresa e marque seu primeiro horário.</p><a class="btn" href="#/">Explorar serviços</a></div>` : `
  <section aria-labelledby="h-prox"><h2 id="h-prox">Próximos</h2>${prox.length ? `<ul class="list">${prox.map(item).join("")}</ul>` : '<p class="hint">Nenhum agendamento ativo.</p>'}</section>
  ${outros.length ? `<section aria-labelledby="h-ant" style="margin-top:1.5rem"><h2 id="h-ant">Anteriores e cancelados</h2><ul class="list">${outros.map(item).join("")}</ul></section>` : ""}`}`;
  $$("[data-cancel]").forEach(b => b.onclick = async () => {
    if (await confirmar("Cancelar agendamento?", "O horário será liberado para outras pessoas.", "Sim, cancelar")) {
      const a = agend.find(x => x.id === +b.dataset.cancel); a.cancelado = true; store.set("aa_agend", agend); telaMeus(); atualizaBadge(); toast("Agendamento cancelado."); $("#main").focus();
    }
  });
}

/* ================= Início ================= */
aplicaTema(); aplicaTam(); rota();
