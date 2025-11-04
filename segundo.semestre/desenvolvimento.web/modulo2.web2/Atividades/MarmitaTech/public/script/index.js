// ===============================
// SCROLL SUAVE PARA O TOPO
// ===============================

window.addEventListener('beforeunload', () => {
    window.scrollTo(0, 0);
});
window.addEventListener('load', () => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});

// ===============================
// MENU HAMBURGUER TOGGLE
// ===============================
const menuToggle = document.getElementById('menuToggle');
const menuMobile = document.getElementById('menuMobile');

if (menuToggle && menuMobile) {
    menuToggle.addEventListener('click', () => {
        menuToggle.classList.toggle('active');
        menuMobile.classList.toggle('active');
        document.body.style.overflow = menuMobile.classList.contains('active') ? 'hidden' : 'auto';
    });
    document.querySelectorAll('.menu-mobile a, .menu-mobile button').forEach(item => {
        item.addEventListener('click', () => {
            menuToggle.classList.remove('active');
            menuMobile.classList.remove('active');
            document.body.style.overflow = 'auto';
        });
    });
}

// ===============================
// SMOOTH SCROLL PARA NAVEGAÇÃO
// ===============================
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            e.preventDefault();
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// ===============================
// ANIMAÇÃO DE ENTRADA AO ROLAR
// ===============================
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
        }
    });
}, observerOptions);

document.addEventListener('DOMContentLoaded', () => {
    const animatedElements = document.querySelectorAll('.sobre, .cardapio, .cadastro, .avaliacao, .produto-card, .avaliacao-card');
    if (animatedElements.length) {
        animatedElements.forEach(el => {
            el.style.opacity = '0';
            el.style.transform = 'translateY(30px)';
            el.style.transition = 'opacity 0.8s ease, transform 0.8s ease';
            observer.observe(el);
        });
    }

    // ===============================
    // MENSAGENS DE SUCESSO E ERRO
    // ===============================
    const sucesso = document.querySelector(".msg-sucesso");
    const erro = document.querySelector(".msg-erro");

    const esconderMensagem = (elemento) => {
        setTimeout(() => {
            elemento.style.opacity = "0";
            elemento.style.transition = "opacity 0.5s ease";
            setTimeout(() => elemento.remove(), 500);
        }, 2000);
    };

    if (sucesso) esconderMensagem(sucesso);
    if (erro) esconderMensagem(erro);

    const url = new URL(window.location);
    if (url.search) {
        url.search = "";
        window.history.replaceState({}, document.title, url);
    }
});
