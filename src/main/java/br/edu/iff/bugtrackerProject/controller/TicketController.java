/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.controller;

import br.edu.iff.bugtrackerProject.model.Ticket;
import br.edu.iff.bugtrackerProject.service.TicketService;
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
@RequestMapping(path = "/api/tickets")
public class TicketController {
    @Autowired
    private TicketService service;
    
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping(path="/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Ticket ticket) {
        ticket.setTicketId(null);
        service.save(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }
    
    @PutMapping(path="/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Ticket ticket) {
        ticket.setTicketId(id);
        service.update(ticket);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
