/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Projeto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProject;
    
    @Column(nullable = false, length = 20, unique = true)
    @NotBlank(message="O nome do projeto é obrigatório")
    @Length(max=20, message="O nome do projeto deve ter no máximo 20 caracteres")
    private String nome;
    
    @Column(length = 100)
    @Length(max=100, message="A descrição deve ter no máximo 100 caracteres")
    private String descricao;
    
    //Atributos de Relacionamento
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message="Projeto deve ter um usuario")
    private Usuario usuario;
    
    @JsonIgnore
    @OneToMany(mappedBy = "projeto", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @Valid
    private List<Ticket> tickets = new ArrayList<>();

    //Construtor
    public Projeto() {}
    
    //Getters and Setters
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {    
        this.usuario = usuario;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //Hash and Equals
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.idProject);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projeto other = (Projeto) obj;
        if (!Objects.equals(this.idProject, other.idProject)) {
            return false;
        }
        return true;
    } 
}
