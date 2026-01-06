package com.example.agenda_contatos.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Contato {

    private Integer id;
    private String nome;
    @NotBlank(message = "O email é obrigatório e não pode ser vazio")
    @Email(message = "O formato do email é inválido")
    private String email;
    @Pattern(regexp = "\\d{11}", message = "Digite apenas os 11 números (DDD + Número)")
    private String telefone;

    public Contato() {
    }

    public Contato(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
