package br.edu.iff.bugtrackerProject.repository;

import br.edu.iff.bugtrackerProject.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
