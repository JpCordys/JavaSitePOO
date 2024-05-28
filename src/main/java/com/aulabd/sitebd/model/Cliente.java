package com.aulabd.sitebd.model;

//POJO - Plain Old Java Object
public class Cliente {

    private int id;
    private String nome, cpf;

    public Cliente(){
        
    }

    public Cliente(int id, String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
    }

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
}
