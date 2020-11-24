package br.edu.iff.bugtrackerProject.controller.view;

import br.edu.iff.bugtrackerProject.model.StatusTicketEnum;
import br.edu.iff.bugtrackerProject.model.Ticket;
import br.edu.iff.bugtrackerProject.model.TipoTicketEnum;
import br.edu.iff.bugtrackerProject.service.ProjetoService;
import br.edu.iff.bugtrackerProject.service.TicketService;
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
@RequestMapping(path = "projetos/{projId}/tickets")
public class TicketViewController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ProjetoService projService;
    
    @GetMapping
    public String getAll(@PathVariable("projId") Long projId, Model model) {
        model.addAttribute("tickets", ticketService.findTicketsByProjectId(projId));
        model.addAttribute("project", projService.findById(projId));
        return "tickets";
    }
    
    @GetMapping(path="/ticket")
    public String cadastro(@PathVariable("projId") Long projId, Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("projId", projId);
        model.addAttribute("tiposTicket", TipoTicketEnum.values());
        model.addAttribute("statusTicket", StatusTicketEnum.values());
        return("formTicket");
    }
    
    @PostMapping(path="/ticket")
    public String save(@PathVariable("projId") Long projId, @ModelAttribute Ticket ticket, BindingResult result, Model model) {
        //Valores de retorno padrão
        
        if(result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formTicket";
        }
        
        ticket.setTicketId(null);
        try {
            ticket.setProjeto(projService.findById(projId));
            ticketService.save(ticket);
            model.addAttribute("msgSucesso", "Ticket cadastrado com sucesso");
            //model.addAttribute("usuario", new Usuario());
            return "formTicket";
        } catch(Exception e) {
            model.addAttribute("msgErros", new ObjectError("Ticket", e.getMessage()));
            return "formTicket";
        }
    }
    
    @GetMapping(path="ticket/{id}")
    public String alterar(@PathVariable("projId") Long projId, @PathVariable("id") Long id, Model model) {
        model.addAttribute("ticket", ticketService.findById(id));
        model.addAttribute("projId", projId);
        model.addAttribute("tiposTicket", TipoTicketEnum.values());
        model.addAttribute("statusTicket", StatusTicketEnum.values());
        return "formTicket";
    }
    
    @PostMapping(path="ticket/{id}")
    public String update(@PathVariable("projId") Long projId,
            @PathVariable("id") Long id,
            @ModelAttribute Ticket ticket,
            BindingResult result, Model model) {
        model.addAttribute("ticket", ticket);
        
        if(result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formTicket";
        }
        
        ticket.setTicketId(id);
        try {
            ticket.setProjeto(projService.findById(projId));
            ticketService.update(ticket);
            model.addAttribute("msgSucesso", "Ticket atualizado com sucesso");
            //model.addAttribute("ticket", new Ticket());
            return "formTicket";
        } catch(Exception e) {
            model.addAttribute("msgErros", new ObjectError("Ticket", e.getMessage()));
            return "formTicket";
        }
        
    }
    
    @GetMapping(path="{id}/deletar")
    public String deletar(@PathVariable("id") Long id) {
        ticketService.delete(id);
        return "redirect:/projetos/{projId}/tickets";
    }
}
