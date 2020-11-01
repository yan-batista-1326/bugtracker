/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.repository;

import br.edu.iff.bugtrackerProject.model.Ticket;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{    
    public Ticket findById (long id);
    
    @Query("SELECT DISTINCT(t) FROM Projeto p JOIN p.tickets t WHERE p.idProject = :projetoId")
    public List<Ticket> findByProjetoId (long projetoId);
}
