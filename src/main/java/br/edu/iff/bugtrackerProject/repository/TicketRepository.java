/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.repository;

import br.edu.iff.bugtrackerProject.model.Ticket;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{    
    public Ticket findById (long id);
    
    @Query("select ticket.* from ticket join projeto where projeto.id_project = :id AND projeto.id_project = ticket.projeto_id")
    public List<Ticket> findTicketByProjectId (long id);
}
