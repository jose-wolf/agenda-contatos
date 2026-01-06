package com.example.agenda_contatos.controller;

import com.example.agenda_contatos.model.Contato;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContatosController {

    List<Contato> contatos = new ArrayList<Contato>();
    private int contadorId = 1;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contato", new Contato());
        model.addAttribute("contatos", contatos);
        return "agenda";
    }

    @PostMapping("/agenda/salvar")
    public String salvarContato(@Valid @ModelAttribute Contato parametroContato, BindingResult result, Model model) {

        Contato contatoParaEditar = contatos.stream()
                .filter(contatoId -> contatoId.getId() == parametroContato.getId())
                .findFirst()
                .orElse(null);

        if(result.hasErrors()) {
            model.addAttribute("contatos", contatos);
            return "agenda";
        }

        if(parametroContato.getId() != null) {
            contatos.removeIf(contatoId -> contatoId.getId() == parametroContato.getId());
        }else{

            parametroContato.setId(contadorId++);
        }

        contatos.add(parametroContato);
        System.out.println("Salvando contato:" +  parametroContato);

        return "redirect:/";
    }

    @GetMapping("/agenda/editar/{id}")
    public String editarContato(@PathVariable Integer id, Model model) {

        Contato contatoParaEditar = contatos.stream()
                .filter(contatoId -> contatoId.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("contato", contatoParaEditar);
        model.addAttribute("contatos", contatos);
        return "agenda";
    }

    @PostMapping("/agenda/excluir")
    public String excluirContato(@RequestParam int id) {
        System.out.println("Excluindo contato:" +  id);
        contatos.removeIf(idContato -> idContato.getId() == id);
        return "redirect:/";
    }
}
