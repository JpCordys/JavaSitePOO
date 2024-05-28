package com.aulabd.sitebd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aulabd.sitebd.model.Cliente;
import com.aulabd.sitebd.model.ClienteService;

@Controller
@ComponentScan("com.aulabd.sitebd.model")
public class IndexController {
    
    @Autowired
	private ApplicationContext context;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("cliente",new Cliente());
        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Model model, @ModelAttribute Cliente cliente){
        ClienteService cs = context.getBean(ClienteService.class);
		cs.inserirCliente(cliente);
		return "sucesso";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        ClienteService cs = context.getBean(ClienteService.class);
        List<Map<String,Object>> lista = cs.listarClientes();
        model.addAttribute("lista",lista);
        return "listar";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizar(@PathVariable("id") int id, Model model){
        ClienteService cs = context.getBean(ClienteService.class);
        Map<String,Object> cliente = cs.obterCliente(id).get(0);
        String nome = (String) cliente.get("nome");
        String cpf = (String) cliente.get("cpf");
        model.addAttribute("cliente", new Cliente(nome,cpf));
        model.addAttribute("nome", nome);
        model.addAttribute("cpf", cpf);
        return "atualiza";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable("id") int id
        , Model model
        , @ModelAttribute Cliente cli){
            ClienteService cs = context.getBean(ClienteService.class);
            cs.atualizarCliente(id, cli);
            return "redirect:/listar";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id){
        ClienteService cs = context.getBean(ClienteService.class);
        cs.apagarCliente(id);
        return "redirect:/listar";
    }

}
