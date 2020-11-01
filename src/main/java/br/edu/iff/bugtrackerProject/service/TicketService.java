/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.service;

import br.edu.iff.bugtrackerProject.model.Ticket;
import br.edu.iff.bugtrackerProject.repository.TicketRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

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
        //Verificar se já existe
        Long id = ticket.getTicketId();
        Optional<Ticket> obj = repo.findById(id);
        if(!obj.isEmpty()) {
            throw new RuntimeException("Ticket já existe");
        }
        
        try {
            return repo.save(ticket);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar ticket");
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
