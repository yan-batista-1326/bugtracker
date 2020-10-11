/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.model;

import java.io.Serializable;
import java.util.List;

public class Projeto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int idProject;
    private String nome;
    private String descricao;
    
    private List<Ticket> tickets;

    public Projeto(int idProject, String nome, String descricao) {
        this.idProject = idProject;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.idProject;
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
        if (this.idProject != other.idProject) {
            return false;
        }
        return true;
    }
    
}
