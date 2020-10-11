/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.model;

import java.io.Serializable;
import java.util.Calendar;

public class Ticket implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int ticketId;
    private String titulo;
    private String descricao;
    private TipoTicketEnum tipo;
    private StatusTicketEnum status;
    private int serveridade;
    private Calendar dataCriacao;
    private String solucao;

    public Ticket(int ticketId, String titulo, String descricao, TipoTicketEnum tipo, StatusTicketEnum status, int serveridade, Calendar dataCriacao, String solucao) {
        this.ticketId = ticketId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.status = status;
        this.serveridade = serveridade;
        this.dataCriacao = dataCriacao;
        this.solucao = solucao;
    }

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

    public int getServeridade() {
        return serveridade;
    }

    public void setServeridade(int serveridade) {
        this.serveridade = serveridade;
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
