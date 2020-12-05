package br.edu.iff.bugtrackerProject.service;

import br.edu.iff.bugtrackerProject.exception.NotFoundException;
import br.edu.iff.bugtrackerProject.model.Permissao;
import br.edu.iff.bugtrackerProject.model.Usuario;
import br.edu.iff.bugtrackerProject.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            removePermissoesNulas(user);
            try {
                user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
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
        
        removePermissoesNulas(user);
        
        //Verifica alteração de senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try {
            user.setEmail(obj.getEmail());
            user.setSenha(obj.getSenha());
            return repo.save(user);
        } catch(Exception e) {
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
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
            if(!crypt.matches(senhaAtual, obj.getSenha())) {
                throw new RuntimeException("Senha atual está incorreta");
            }
            if(!novaSenha.equals(confirmarNovaSenha)) {
                throw new RuntimeException("As senhas devem ser iguais");
            }
            obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        }
    }
    
    public void removePermissoesNulas(Usuario r){
        r.getPermissoes().removeIf( (Permissao p) -> {
            return p.getId()==null;
        });
        if(r.getPermissoes().isEmpty()){
            throw new RuntimeException("Usuário deve conter no mínimo 1 permissão.");
        }
    }
}