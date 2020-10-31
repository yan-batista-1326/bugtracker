/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Ticket implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;
    
    @Column(length = 20, unique = true, nullable = false)
    @NotBlank(message="O título é obrigatório")
    @Length(max = 20, message = "O título deve ter no máximo 20 caracteres")
    private String titulo;
    
    @Column(length = 50)
    @Length(max = 50, message="A descrição deve ter no máximo 50 caracteres")
    private String descricao;
    
    @Column(length = 5, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message="O tipo de ticket é obrigatório")
    @Length(max=5, message="O tipo de ticket deve ter no máximo 5 caracteres")
    private TipoTicketEnum tipo;
    
    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message="O status do ticket é obrigatório")
    @Length(max=15, message="O status do ticket deve ter no máximo 15 caracteres")
    private StatusTicketEnum status;
    
    @Column(nullable = false)
    @NotBlank(message="A severidade do ticket é obrigatória")
    @Min(0) @Max(5)
    private int severidade;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotBlank(message="A data de criação é obrigatória")
    @PastOrPresent(message="A data deve ser feita no momento de criação")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Calendar dataCriacao;
    
    @Column(length = 50)
    @Length(max = 50, message="A solução deve conter no máximo 50 caracteres")
    private String solucao;

    //Construtor
    public Ticket() {}

    //Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTicketEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoTicketEnum tipo) {
        this.tipo = tipo;
    }

    public StatusTicketEnum getStatus() {
        return status;
    }

    public void setStatus(StatusTicketEnum status) {
        this.status = status;
    }

    public int getSeveridade() {
        return severidade;
    }

    public void setSeveridade(int severidade) {
        this.severidade = severidade;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    //Hash and Equals
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ticketId;
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
        final Ticket other = (Ticket) obj;
        if (this.ticketId != other.ticketId) {
            return false;
        }
        return true;
    }
    
}
