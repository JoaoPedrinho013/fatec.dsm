package br.edu.fatecpg.cadastroproduto.controller;

import br.edu.fatecpg.cadastroproduto.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProdutoController {

    private static final List<Produto> produtos = new ArrayList<>();

    @GetMapping("/")
    public String index(){
        return "redirect:/cadastrar";
    }
    @GetMapping("/cadastrar")
    public  String cadastrar(Model model){
        model.addAttribute("produto", new Produto());
        return "cadastrar";
    }
    @PostMapping("/cadastrar")
    public String cadastrarProduto(@ModelAttribute Produto produto){
        produtos.add(produto);
        return "redirect:/lista";
    }
    @GetMapping("/lista")
    public String exibirLista(Model model){
        model.addAttribute("produtos", produtos);
        return "lista";
    }
}
