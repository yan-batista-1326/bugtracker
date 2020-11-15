package br.edu.iff.bugtrackerProject.service;

import br.edu.iff.bugtrackerProject.exception.NotFoundException;
import br.edu.iff.bugtrackerProject.model.Projeto;
import br.edu.iff.bugtrackerProject.repository.ProjectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {
    @Autowired
    private ProjectRepository repo;
    
    public Projeto findById(Long id) {
        Optional<Projeto> result = repo.findById(id);
        if(result.isEmpty()) {
            throw new NotFoundException("Projeto não encontrado");
        }
        return result.get();
    }
    
    public List<Projeto> findAll() {
        return repo.findAll();
    }
    
    public List<Projeto> findProjectsByUserId(Long userId) {
        return repo.findByUsuarioId(userId);
    }
    
    public Projeto save(Projeto proj) {
        if(proj.getIdProject() != null) {
            throw new RuntimeException("Id diferente de nulo");
        } else {
            try {
                return repo.save(proj);
            } catch (Exception e) {
                throw new RuntimeException("Falha ao salvar projeto");     
            }
        }
    }
    
    public Projeto update(Projeto proj) {
        //Verifica se o projeto existe
        Projeto obj = findById(proj.getIdProject());
        
        try {
            proj.setNome(obj.getNome());
            return repo.save(proj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar projeto");
        }
    }
    
    public void delete(Long id) {
        Projeto obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar projeto");
        }
    }
}