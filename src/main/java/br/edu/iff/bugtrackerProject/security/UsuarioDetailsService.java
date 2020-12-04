package br.edu.iff.bugtrackerProject.security;

import br.edu.iff.bugtrackerProject.model.Permissao;
import br.edu.iff.bugtrackerProject.model.Usuario;
import br.edu.iff.bugtrackerProject.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = repo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return new User(user.getEmail(), user.getSenha(), getAuthorities(user.getPermissoes()));
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> lista) {
        List<GrantedAuthority> l = new ArrayList<>();
        for(Permissao p : lista) {
            l.add(new SimpleGrantedAuthority("ROLE_" + p.getNome()));
        }
        return l;
    }
    
}
