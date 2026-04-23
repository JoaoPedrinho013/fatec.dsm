from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.contrib.auth.models import User
from app.models import Categoria, Contato, Produto
from app.forms import FormCategoria, FormContato, ProdutoForm, FormUsuario, FormEditarUsuario

def index(request):
    return render(request, 'index.html')

def quemSomos(request):
    usuarios = User.objects.all()[:3]
    return render(request, 'quem-somos.html', {'usuarios': usuarios})

def loja(request):
    produtos = Produto.objects.all()
    return render(request, 'loja.html', {'produtos': produtos})

def cadastrarUsuario(request):
    formulario = FormUsuario(request.POST or None)
    if request.method == 'POST':
        if formulario.is_valid():
            formulario.save()
            return redirect('login')
    return render(request, 'cadastro.html', {'form': formulario})

def loginUsuario(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        usuario = authenticate(request, username=username, password=password)
        if usuario is not None:
            login(request, usuario)
            return redirect('index')
        else:
            return render(request, 'login.html', {'erro': 'Usuário ou senha inválidos.'})
    return render(request, 'login.html')

def logoutUsuario(request):
    logout(request)
    return redirect('login')

@login_required(login_url='login')
def editarUsuario(request):
    formulario = FormEditarUsuario(request.POST or None, instance=request.user)
    if request.method == 'POST':
        if formulario.is_valid():
            formulario.save()
            return redirect('index')
    return render(request, 'edit-usuario.html', {'form': formulario})


# ─── Categoria ───

def listarCategoria(request):
    _categorias = Categoria.objects.all().values()
    return render(request, 'categoria.html', {'categorias': _categorias})

def delCategoria(request, id_cat):
    _categoria = Categoria.objects.get(id=id_cat)
    _categoria.delete()
    return redirect('categoria')

def addCategoria(request):
    formulario = FormCategoria(request.POST or None)
    if request.POST:
        if formulario.is_valid():
            formulario.save()
            return redirect('categoria')
    return render(request, 'add-categoria.html', {'form': formulario})

def editCategoria(request, id_cat):
    _categoria = Categoria.objects.get(id=id_cat)
    formulario = FormCategoria(request.POST or None, instance=_categoria)
    if request.POST:
        if formulario.is_valid():
            formulario.save()
            return redirect('categoria')
    return render(request, 'edit-categoria.html', {'form': formulario})


# ─── Contato ───

def listarContato(request):
    contatos = Contato.objects.all()
    return render(request, 'contato.html', {'contatos': contatos})

def delContato(request, id_contato):
    _contato = Contato.objects.get(id=id_contato)
    _contato.delete()
    return redirect('contato')

def addContato(request):
    formulario = FormContato(request.POST or None)
    if request.POST:
        if formulario.is_valid():
            formulario.save()
            return redirect('contato')
    return render(request, 'add-contato.html', {'form': formulario})


# ─── Produto ───

def listarProduto(request):
    _produtos = Produto.objects.all()
    return render(request, 'produto.html', {'produtos': _produtos})

def addProduto(request):
    if request.method == 'POST':
        form = ProdutoForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect('produto')
    else:
        form = ProdutoForm()
    return render(request, 'add-produto.html', {'form': form})

def editProduto(request, id_prod):
    _produto = get_object_or_404(Produto, id=id_prod)
    form = ProdutoForm(request.POST or None, request.FILES or None, instance=_produto)
    if request.method == 'POST':
        if form.is_valid():
            form.save()
            return redirect('produto')
    return render(request, 'edit-produto.html', {'form': form, 'produto': _produto})

def delProduto(request, id_prod):
    _produto = get_object_or_404(Produto, id=id_prod)
    _produto.delete()
    return redirect('produto')


# ─── Dashboard ───

def dashboard(request):
    total_produtos = Produto.objects.count()
    total_categorias = Categoria.objects.count()
    total_contatos = Contato.objects.count()
    total_usuarios = User.objects.count()
    return render(request, 'dashboard.html', {
        'total_produtos': total_produtos,
        'total_categorias': total_categorias,
        'total_contatos': total_contatos,
        'total_usuarios': total_usuarios,
    })

def listarUsuarios(request):
    usuarios = User.objects.all().order_by('date_joined')
    return render(request, 'usuarios.html', {'usuarios': usuarios})

def editUsuarioAdmin(request, id_user):
    _usuario = get_object_or_404(User, id=id_user)
    formulario = FormEditarUsuario(request.POST or None, instance=_usuario)
    if request.method == 'POST':
        if formulario.is_valid():
            formulario.save()
            return redirect('usuarios')
    return render(request, 'edit-usuario-admin.html', {'form': formulario, 'usuario': _usuario})

def delUsuario(request, id_user):
    _usuario = get_object_or_404(User, id=id_user)
    _usuario.delete()
    return redirect('usuarios')
