from django.urls import path
from . import views

#Passa o ID como parametro para ele chamar a view
# sempre adicionar rota e chamar a view

urlpatterns = [
     path('', views.index, name="index"),
     path('Categoria', views.listarCategoria, name="categoria"),
     path('del-categoria/<int:id_cat>', views.delCategoria, name="delcategoria"),
     path('add-categoria', views.addCategoria, name="addcategoria"),
     path('edit-categoria/<int:id_cat>', views.editCategoria, name="editcategoria"),
     path('contato', views.listarContato, name="contato"),
     path('del-contato/<int:id_contato>', views.delContato, name="delcontato"),
     path('add-contato', views.addContato, name="addcontato"),

     path('add-produto', views.addProduto, name="addproduto"),
     path('produto', views.listarProduto, name="produto"),
]

