package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Jogos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message="O nome deverá ser informado!")
    private String nome;
    
    @Column(nullable = false)
    @NotEmpty(message="A quantidade deverá ser informada!")
    private String quantidade;
    
    @Column(nullable = false)
    @NotEmpty(message="Os consoles deverão ser informados!")
    private String consoles;
    
    @Column(nullable = false)
    @NotEmpty(message="O genero deverá ser informado!")
    private String generos;
    
    @Column(nullable = false)
    @NotEmpty(message="A idade mínima deverá ser informada!")
    private String idadem;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getConsoles() {
        return consoles;
    }

    public void setConsoles(String consoles) {
        this.consoles = consoles;
    }
    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }
    public String getIdadem() {
        return idadem;
    }

    public void setIdadem(String idadem) {
        this.idadem = idadem;
    }

}
