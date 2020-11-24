package br.edu.iff.bugtrackerProject.controller.apirest;

import br.edu.iff.bugtrackerProject.model.Projeto;
import br.edu.iff.bugtrackerProject.service.ProjetoService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoService service;
    
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id)); //Abreviação de ResponseEntity.status(HttpStatus.OK).body(bodyElement);
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Projeto proj) {
        proj.setIdProject(null);
        service.save(proj);
        return ResponseEntity.status(HttpStatus.CREATED).body(proj);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Projeto proj) {
        proj.setIdProject(id);
        service.update(proj);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
