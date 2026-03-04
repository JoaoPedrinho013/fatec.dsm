package com.azien.first_springboot.controller;

import com.azien.first_springboot.domain.User;
import com.azien.first_springboot.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController /*Existe o @Controller e @ResponseBody, esse @RestController usa os dois, pq eu posso usar só o @Controller caso n seja necessario uma response Json/XML*/
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired // ao usar isso eu n preciso usar o construtor, esse @Atuowired, pq o private eu preciso sim, e o spring que instancia a classe
    private HelloWorldService helloWorldService;


    //construtor para o spring instanciar a classe service
    /*
    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
    */

    @GetMapping// aqui eu posso por um parametro para aumentar a url tipo ("/get") ai a URL ficaria "/hello-world/get"
    public String helloWorld() {
        //return "Hello World Controller!";
        return helloWorldService.helloWorld("Azien");
    }

    // POST http://localhost:3000/hello-world
    @PostMapping("")
    public String helloWorldPost(@RequestBody User body) {
        return "Hello World " + body.getName() + " Email: " + body.getEmail();
    }


    // POST http://localhost:3000/hello-world/1/simple
    @PostMapping("/{id}/simple")
    public String helloWorldPost2(
            @PathVariable String id,
            @RequestBody User body) {

        return "Hello World "
                + body.getName()
                + " Email: " + body.getEmail()
                + " ID: " + id;
    }

    // POST http://localhost:3000/hello-world/1?filter=MacOS
    // POST http://localhost:3000/hello-world/1 aqui vem com defaultValue aplicado
    @PostMapping("/{id}")
    public String helloWorldPost(
            @PathVariable String id,
            @RequestParam(value = "filter", defaultValue = "nenhum") String filter,
            @RequestBody User body) {

        return "Hello World "
                + body.getName()
                + " Email: " + body.getEmail()
                + " ID: " + id
                + " Filtro: " + filter;
    }
}
