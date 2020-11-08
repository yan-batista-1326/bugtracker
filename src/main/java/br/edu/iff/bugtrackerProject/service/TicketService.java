package br.edu.iff.bugtrackerProject.service;

import br.edu.iff.bugtrackerProject.model.Ticket;
import br.edu.iff.bugtrackerProject.repository.TicketRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository repo;
    
    public Ticket findById(Long id) {
       Optional<Ticket> result = repo.findById(id);
       if(result.isEmpty()) {
           throw new RuntimeException("Ticket não existe");
       }
       return result.get();
    }
    
    public List<Ticket> findTicketsByProjectId(Long projectId) {
        return repo.findByProjetoId(projectId);
    }
    
    public List<Ticket> findAll() {
       return repo.findAll();
    }
    
    public Ticket save(Ticket ticket) {
        if(ticket.getTicketId() != null) {
            throw new RuntimeException("Id diferente de nulo");
        } else {
            try {
                ticket.setDataCriacao(Calendar.getInstance());
                return repo.save(ticket);
            } catch (Exception e) {
                throw new RuntimeException("Falha ao salvar ticket");
            }
        }
    }
    
    public Ticket update(Ticket ticket) {
        //Verifica se o ticket existe
        Ticket obj = findById(ticket.getTicketId());
        
        try {
            ticket.setDataCriacao(obj.getDataCriacao());
            ticket.setTitulo(obj.getTitulo());
            return repo.save(ticket);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar ticket");
        }
    }
    
    public void delete(Long id) {
        Ticket obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar ticket");
        }
    }
}
