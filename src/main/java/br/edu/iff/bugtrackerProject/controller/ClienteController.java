package br.edu.iff.bugtrackerProject.controller;

import br.edu.iff.bugtrackerProject.model.Usuario;
import br.edu.iff.bugtrackerProject.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/usuarios")
public class ClienteController {
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping(path="/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Usuario usuario) {
        usuario.setIdUser(null);
        service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    
    @PutMapping(path="/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        usuario.setIdUser(null);
        service.update(usuario, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path="/{id}/alterarSenha")
    public ResponseEntity alterarSenha(@PathVariable("id") Long id, 
            @RequestParam(name="senhaAtual", defaultValue="", required=true) String senhaAtual,
            @RequestParam(name="novaSenha", defaultValue="", required=true) String novaSenha,
            @RequestParam(name="confirmarNovaSenha", defaultValue="", required=true) String confirmarNovaSenha) {
        Usuario u = service.findById(id);
        service.update(u, senhaAtual, novaSenha, confirmarNovaSenha);
        return ResponseEntity.ok().build();
    }
}
