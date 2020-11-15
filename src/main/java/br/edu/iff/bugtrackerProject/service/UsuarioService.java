package br.edu.iff.bugtrackerProject.service;

import br.edu.iff.bugtrackerProject.exception.NotFoundException;
import br.edu.iff.bugtrackerProject.model.Usuario;
import br.edu.iff.bugtrackerProject.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UserRepository repo;
    
    public List<Usuario> findAll() {
        return repo.findAll();
    }
    
    public Usuario findById(Long id) {
        Optional<Usuario> result = repo.findById(id);
        if(result.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return result.get();
    }
    
    public Usuario findByEmail(String email) {
        return repo.findByEmail(email);
    }
    
    public Usuario save(Usuario user) {
        if(user.getIdUser() != null) {
            throw new RuntimeException("Id diferente de nulo");
        } else {
            try {
                return repo.save(user);  
            } catch(Exception e) {
                throw new RuntimeException("Falha ao salvar o usuário");
            }
        }
    }
    
    public Usuario update(Usuario user, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        //Só é permitido alterar nome de exibição (não usado em login) e senha
        //Verifica se usuário já existe
        Usuario obj = findById(user.getIdUser());
        
        //Verifica alteração de senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try {
            user.setEmail(obj.getEmail());
            user.setSenha(obj.getSenha());
            return repo.save(user);
        } catch(Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if(t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar usuário");
        }
    }
    
    public void delete(Long id) {
        Usuario obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar usuário");
        }
    }
    
    private void alterarSenha(Usuario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
            if(!senhaAtual.equals(obj.getSenha())) {
                throw new RuntimeException("Senha atual está incorreta");
            }
            if(!novaSenha.equals(confirmarNovaSenha)) {
                throw new RuntimeException("As senhas devem ser iguais");
            }
            obj.setSenha(novaSenha);
        }
    }
}