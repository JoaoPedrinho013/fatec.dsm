from django.shortcuts import render, redirect
from app.models import Categoria, Contato, Produto
from app.forms import FormCategoria, FormContato, ProdutoForm

# Create your views here.
#A view renderiza a pagina

def index(request):
    return render(request, 'index.html')
    
def listarCategoria(request):
    _categorias = Categoria.objects.all().values()
    return render(request,'categoria.html',{'categorias':_categorias})

def delCategoria(request, id_cat):
    _categoria = Categoria.objects.get(id=id_cat)
    _categoria.delete()
    return redirect('categoria')

def addCategoria(request):
    #Verifica se teve requizi tipo post ou se n teve se n teve mostra apenas o formulario no navegador se teve ira mostrar a logica
    formulario = FormCategoria(request.POST or None)
    if request.POST:
        if formulario.is_valid():
            formulario.save()
            return redirect('categoria')

    return render(request, 'add-categoria.html',{'form':formulario})

def editCategoria(request, id_cat):
    _categoria = Categoria.objects.get(id=id_cat)
    formulario = FormCategoria(request.POST or None, instance=_categoria)
    if request.POST:
        if formulario.is_valid():
            formulario.save()
            return redirect('categoria')
    return render(request, 'edit-categoria.html',{'form':formulario})

def listarContato(request):
    contatos = Contato.objects.all()
    return render(request,'contato.html',{'contatos':contatos})

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
    return render(request, 'add-contato.html',{'form':formulario})

def listarProduto(request):
    _produtos = Produto.objects.all()
    return render(request,'produto.html',{'produtos':_produtos})

def addProduto(request):
    if request.method == 'POST':
        form = ProdutoForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect('produto')
    else:
        form = ProdutoForm()

    return render(request, 'add-produto.html', {'form': form})


