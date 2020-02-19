package com.example.demo.controller;

import com.example.demo.model.IUsuarioService;
import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping
public class ControladorCrud {

    @Autowired
    private IUsuarioService service;

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listar());
        return "crud/lista";
    }

    @GetMapping("/listar/{id}")
    public String listarId(@PathVariable int id,Model model) {
        model.addAttribute("usuario", service.listarId(id));
        return "crud/nuevo";
    }

    @GetMapping("/volver")
    public String volver(Model model) {
        model.addAttribute("usuarios", service.listar());
        return "crud/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crud/nuevo";
    }

    @PostMapping("/crear")
    public String save(@Valid Usuario p, Model model, BindingResult bindingResult) {
        int id=service.save(p);
        if(id==0 && bindingResult.hasErrors()) {
            return "crud/nuevo";
        }else{
            return "crud/creado";
        }

    }

    @GetMapping("/borrar/{id}")
    public String eliminar(@PathVariable int id, Model model) {
        service.delete(id);
        return "redirect:/volver";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Optional<Usuario>usuario= service.listarId(id);
        model.addAttribute("usuario", usuario);
        return "crud/editar";

    }


}