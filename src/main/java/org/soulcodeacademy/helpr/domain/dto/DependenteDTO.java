package org.soulcodeacademy.helpr.domain.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class DependenteDTO { // Task feita no merge anterior


    @NotBlank(message = "O Campo Nome é Obrigatório!")
    private String nome;

    @CPF(message = "O Campo Cpf é Obrigatório!!")
    private String cpf;

    @NotNull
    private String dataNascimento;

    @NotBlank(message = "O Campo Escolaridade é Obrigatório!")
    private String escolaridade;

    @NotNull(message = "id_Responsavel")
    private Integer id_Responsavel;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Integer getId_Responsavel() {
        return id_Responsavel;
    }

    public void setId_Responsavel(Integer id_Responsavel) {
        this.id_Responsavel = id_Responsavel;
    }

}





