from django.urls import path
from . import views

urlpatterns = [
    path('produtos/', views.listarProdutosApi, name='apiProduto'),
    path('produtos/<int:id>/', views.buscarProdutoApi, name='apiBuscarProduto'),
    path('produtos/cadastrar/' , views.cadastrarProdutoApi, name='apiCadastrarProduto'),
    path('produtos/atualizar/<int:id>/', views.atualizarProdutosApi, name='apiAtualizarProduto'),
    path('produtos/deletar/<int:id>/', views.deletarProdutoApi, name='apiDeletarProduto'),
]
