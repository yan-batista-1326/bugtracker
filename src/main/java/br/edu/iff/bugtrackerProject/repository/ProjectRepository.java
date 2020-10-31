/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.repository;

import br.edu.iff.bugtrackerProject.model.Projeto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projeto, Long>{    
    public Projeto findById (long id);
    
    @Query("select projeto.* from projeto join usuario where projeto.user_id = :id AND projeto.user_id = usuario.id_user")
    public List<Projeto> findProjectByUserId (long id);
}
