package br.edu.iff.bugtrackerProject.controller.view;

import br.edu.iff.bugtrackerProject.model.Projeto;
import br.edu.iff.bugtrackerProject.model.Usuario;
import br.edu.iff.bugtrackerProject.service.ProjetoService;
import br.edu.iff.bugtrackerProject.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "usuarios/{userId}/projetos")
public class ProjetoViewController {
    @Autowired
    private ProjetoService projService;
    @Autowired
    private UsuarioService userService;
    
    @GetMapping
    public String getAll(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("projetos", projService.findProjectsByUserId(userId));
        model.addAttribute("userId", userId);
        return "projetos";
    }
    
    @GetMapping(path="/projeto")
    public String cadastro(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("userId", userId);
        return("formProjeto");
    }
    
    @PostMapping(path="/projeto")
    public String save(@PathVariable("userId") Long userId, @ModelAttribute Projeto projeto, BindingResult result, Model model) {
        //Valores de retorno padrão
        
        if(result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formProjeto";
        }
        
        projeto.setIdProject(null);
        try {
            projeto.setUsuario(userService.findById(userId));
            projService.save(projeto);
            model.addAttribute("msgSucesso", "Projeto cadastrado com sucesso");
            model.addAttribute("usuario", new Usuario());
            return "formProjeto";
        } catch(Exception e) {
            model.addAttribute("msgErros", new ObjectError("Projeto", e.getMessage()));
            return "formProjeto";
        }
    }
    
    @GetMapping(path="projeto/{id}")
    public String alterar(@PathVariable("userId") Long userId, @PathVariable("id") Long id, Model model) {
        model.addAttribute("projeto", projService.findById(id));
        model.addAttribute("userId", userId);
        return "formProjeto";
    }
    
    @PostMapping(path="projeto/{id}")
    public String update(@PathVariable("userId") Long userId,
            @PathVariable("id") Long id,
            @ModelAttribute Projeto projeto,
            BindingResult result, Model model) {
        model.addAttribute("projeto", projeto);
        
        if(result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formProjeto";
        }
        
        projeto.setIdProject(id);
        try {
            projeto.setUsuario(userService.findById(userId));
            projService.update(projeto);
            model.addAttribute("msgSucesso", "Projeto atualizado com sucesso");
            model.addAttribute("projeto", projeto);
            return "formProjeto";
        } catch(Exception e) {
            model.addAttribute("msgErros", new ObjectError("Projeto", e.getMessage()));
            return "formProjeto";
        }
        
    }
    
    @GetMapping(path="/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) {
        projService.delete(id);
        return "redirect:/usuarios/{userId}/projetos";
    }
}
