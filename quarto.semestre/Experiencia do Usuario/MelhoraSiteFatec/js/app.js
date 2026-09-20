/* SIGA - protótipo: JS só para o que precisa de interação simples
   (login de mentira, dropdowns, dia do horário e aviso rápido). */

(function () {
  var KEY = "siga-usuario";
  var page = document.currentScript && document.currentScript.dataset.page;

  document.documentElement.classList.add("js");

  function getUser() {
    try { return sessionStorage.getItem(KEY); } catch (e) { return "roberto"; }
  }
  function setUser() {
    try { sessionStorage.setItem(KEY, "roberto"); } catch (e) { /* segue sem guardar */ }
  }
  function clearUser() {
    try { sessionStorage.removeItem(KEY); } catch (e) { /* ignora */ }
  }

  // Telas internas só abrem depois do login
  if (page !== "login" && !getUser()) {
    location.replace("index.html");
    return;
  }

  document.addEventListener("DOMContentLoaded", function () {
    initLogin();
    initAccordions();
    initDayTabs();
    initSoonLinks();
    initLogout();
  });

  /* ---------- login ---------- */
  function initLogin() {
    var form = document.getElementById("login-form");
    if (!form) return;

    var pass = document.getElementById("senha");
    var toggle = document.getElementById("toggle-senha");
    var btn = form.querySelector("button[type=submit]");

    toggle.addEventListener("click", function () {
      var show = pass.type === "password";
      pass.type = show ? "text" : "password";
      toggle.setAttribute("aria-pressed", String(show));
      toggle.setAttribute("aria-label", show ? "Ocultar senha" : "Mostrar senha");
      toggle.querySelector("use").setAttribute("href", show ? "#i-eyeoff" : "#i-eye");
    });

    form.addEventListener("submit", function (e) {
      e.preventDefault();
      // Protótipo: os campos podem ficar vazios
      btn.disabled = true;
      btn.classList.add("is-loading");
      btn.firstElementChild.textContent = "Entrando";
      // Protótipo: qualquer dado preenchido entra como Roberto Carlos
      setTimeout(function () {
        setUser();
        location.href = "inicio.html";
      }, 700);
    });
  }

  /* ---------- dropdowns (Meu curso, Solicitações, Documentos, card do aluno) ---------- */
  function initAccordions() {
    var panels = document.querySelectorAll("[data-accordion]");
    var wideQuery = window.matchMedia("(min-width: 900px)");

    // Grupo de um painel: abrir um painel fecha os outros do mesmo grupo.
    // Os 3 blocos do menu sempre estão no grupo "menu". O card do aluno só entra
    // nesse grupo no celular (data-mobile-group); no computador ele é independente.
    function groupOf(panel) {
      var group = panel.getAttribute("data-accordion");
      if (group) return group;
      return wideQuery.matches ? "" : panel.getAttribute("data-mobile-group") || "";
    }

    panels.forEach(function (panel) {
      var head = panel.querySelector(".panel__head");
      // O card do aluno já nasce aberto no computador e fechado no celular
      if (panel.hasAttribute("data-open-wide") && wideQuery.matches) setOpen(panel, head, true);

      head.addEventListener("click", function () {
        var open = !panel.classList.contains("is-open");
        setOpen(panel, head, open);

        var group = groupOf(panel);
        if (open && group) {
          panels.forEach(function (other) {
            if (other !== panel && groupOf(other) === group) {
              setOpen(other, other.querySelector(".panel__head"), false);
            }
          });
        }
      });
    });
  }

  function setOpen(panel, head, open) {
    panel.classList.toggle("is-open", open);
    head.setAttribute("aria-expanded", String(open));
  }

  /* ---------- horário: um dia por vez no celular ---------- */
  function initDayTabs() {
    var tabs = document.querySelectorAll(".daytab");
    var days = document.querySelectorAll(".day");
    if (!tabs.length) return;

    var names = ["dom", "seg", "ter", "qua", "qui", "sex", "sab"];
    var today = names[new Date().getDay()];
    var hasToday = document.querySelector('.day[data-day="' + today + '"]');

    function select(day) {
      tabs.forEach(function (t) {
        var on = t.dataset.day === day;
        t.classList.toggle("is-active", on);
        t.setAttribute("aria-pressed", String(on));
      });
      days.forEach(function (d) { d.classList.toggle("is-active", d.dataset.day === day); });
    }

    if (hasToday) {
      hasToday.classList.add("is-today");
      document.querySelector('.daytab[data-day="' + today + '"]').classList.add("is-today");
    }

    tabs.forEach(function (t) {
      t.addEventListener("click", function () { select(t.dataset.day); });
    });

    select(hasToday ? today : "seg");
  }

  /* ---------- telas que ainda não existem no protótipo ---------- */
  function initSoonLinks() {
    var toast = document.getElementById("toast");
    var timer;
    document.querySelectorAll("[data-soon]").forEach(function (a) {
      a.addEventListener("click", function (e) {
        e.preventDefault();
        if (!toast) return;
        toast.textContent = "Esta tela não faz parte do protótipo.";
        toast.classList.add("is-visible");
        clearTimeout(timer);
        timer = setTimeout(function () { toast.classList.remove("is-visible"); }, 2200);
      });
    });
  }

  /* ---------- sair ---------- */
  function initLogout() {
    document.querySelectorAll("[data-logout]").forEach(function (a) {
      a.addEventListener("click", clearUser);
    });
  }
})();
