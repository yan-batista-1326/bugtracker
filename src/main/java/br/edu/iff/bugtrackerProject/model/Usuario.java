/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.bugtrackerProject.model;

import br.edu.iff.bugtrackerProject.annotation.EmailValidation;
import br.edu.iff.bugtrackerProject.annotation.PasswordValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    
    @Column(nullable = false, length = 50)
    @NotBlank(message="Nome é obrigatório.")
    @Length(max=50, message="Nome deve ter no máximo 50 caracteres")
    private String nome;
    
    @Column(nullable = false, length = 50)
    @NotBlank(message="Sobrenome é obrigatório.")
    @Length(max=50, message="Sobrenome deve ter no máximo 50 caracteres")
    private String sobrenome;
    
    @Column(nullable = false, length = 100, unique = true, updatable = false)
    @NotBlank(message="Email é obrigatório")
    @Length(max=100)
    @EmailValidation
    private String email;
    
    @Column(nullable = false)
    @NotBlank(message="Senha é obrigatória")
    @Length(min=6, message="A senha deve ter no mínimo 6 caracteres")
    @PasswordValidation(message="A senha deve conter uma letra maiúscula, uma minúscula, um caractere especial, um número, e um total de 6 caracteres. Ex: Aa@123")
    private String senha;

    //Atributos de relacionamento
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @Valid
    private List<Projeto> projetos = new ArrayList<>();

    //Construtor
    public Usuario() {}

    //Hash and Equals
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.idUser;
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
        final Usuario other = (Usuario) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    //Getters and Setters
    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
