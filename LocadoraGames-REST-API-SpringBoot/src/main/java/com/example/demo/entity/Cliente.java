package com.example.demo.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message="O nome deverá ser informado!")
    @Length(min=5, max=200, message="O nome deverá ter de 5 a 200 caracteres")
    private String nome;

    @Column(nullable = false)
    @NotEmpty(message="O endereço deverá ser informado!")
    @Length(min=5, max=200,  message="O endereço deverá ter de 5 a 200 caracteres")
    private String endereco;
    
    @Column(nullable = false)
    @NotEmpty(message="O sexo deverá ser informado!")
    private String sexo;
    
    @Column(nullable = false)
    @NotEmpty(message="A idade deverá ser informada!")
    private String idade;

    
    private Boolean error;

    

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }


}
