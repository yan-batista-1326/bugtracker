package br.edu.iff.bugtrackerProject;

import br.edu.iff.bugtrackerProject.model.Permissao;
import br.edu.iff.bugtrackerProject.model.Projeto;
import br.edu.iff.bugtrackerProject.model.StatusTicketEnum;
import br.edu.iff.bugtrackerProject.model.Ticket;
import br.edu.iff.bugtrackerProject.model.TipoTicketEnum;
import br.edu.iff.bugtrackerProject.model.Usuario;
import br.edu.iff.bugtrackerProject.repository.PermissaoRepository;
import br.edu.iff.bugtrackerProject.repository.ProjectRepository;
import br.edu.iff.bugtrackerProject.repository.TicketRepository;
import br.edu.iff.bugtrackerProject.repository.UserRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BugtrackerProjectApplication implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private ProjectRepository projRepo;
    @Autowired
    private PermissaoRepository permissaoRepo;
    
    public static void main(String[] args) {
	SpringApplication.run(BugtrackerProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Permissão
        Permissao per1 = new Permissao();
        per1.setNome("ADMIN");
        Permissao per2 = new Permissao();
        per2.setNome("FUNC");
        permissaoRepo.saveAll(List.of(per1, per2));
        
        //Ticket
        Ticket t1 = new Ticket();
        t1.setTitulo("Erro1");
        t1.setDescricao("Descricao-t1");
        t1.setTipo(TipoTicketEnum.ERRO);
        t1.setStatus(StatusTicketEnum.ABERTO);
        t1.setSeveridade(3);
        t1.setSolucao("Solucao1");
        t1.setDataCriacao(Calendar.getInstance());
        
        Ticket t2 = new Ticket();
        t2.setTitulo("Erro2");
        t2.setDescricao("Descricao-t2");
        t2.setTipo(TipoTicketEnum.ERRO);
        t2.setStatus(StatusTicketEnum.EM_PROGRESSO);
        t2.setSeveridade(5);
        t2.setSolucao("Solucao2");
        t2.setDataCriacao(Calendar.getInstance());
        
        Ticket t3 = new Ticket();
        t3.setTitulo("Bug");
        t3.setDescricao("Descricao-t3");
        t3.setTipo(TipoTicketEnum.BUG);
        t3.setStatus(StatusTicketEnum.FECHADO);
        t3.setSeveridade(3);
        t3.setSolucao("Solucao3");
        t3.setDataCriacao(Calendar.getInstance());
        
        //Projeto
        Projeto p1 = new Projeto();
        p1.setNome("projeto1");
        p1.setDescricao("Descricao1");
        p1.setTickets(List.of(t1, t2));
        
        
        Projeto p2 = new Projeto();
        p2.setNome("projeto2");
        p2.setDescricao("Descricao2");
        p2.setTickets(List.of(t3));
        
        
        //Usuário
        Usuario user1 = new Usuario();
        user1.setPermissoes(List.of(per1, per2));
        user1.setNome("José");
        user1.setSobrenome("Silva");
        user1.setEmail("joseSilva@hotmail.com");
        user1.setSenha(new BCryptPasswordEncoder().encode("Aa1234#6"));
        user1.setProjetos(List.of(p1, p2));
        
        userRepo.save(user1);
        
        p1.setUsuario(user1);
        p2.setUsuario(user1);
        
        projRepo.save(p1);
        projRepo.save(p2);
        
        t1.setProjeto(p1);
        t2.setProjeto(p1);
        t3.setProjeto(p2);
        
        ticketRepo.save(t1);
        ticketRepo.save(t2);
        ticketRepo.save(t3);
    }
}
