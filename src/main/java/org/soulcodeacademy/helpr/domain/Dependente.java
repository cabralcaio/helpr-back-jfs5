package org.soulcodeacademy.helpr.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Dependente { // Task feita no merge anterior

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_dependente;
    private  String nome;

    private String cpf;
    private LocalDate dataDeNascimento;

    private String escolaridade;

    @ManyToOne
    @JoinColumn(name="id_responsavel")
    private Funcionario funcionario;//MUITOS Dependentes para UM funcionario

    public Dependente (){}

    public Dependente(String nome, String cpf, LocalDate dataDeNascimento, String escolaridade,Integer id_reponsavel) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.escolaridade = escolaridade;

        this.funcionario = new Funcionario();
        this.funcionario.setId(id_reponsavel);


    }

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

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento() {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        escolaridade = escolaridade;
    }

    public Integer getId_dependente() {
        return id_dependente;
    }

    public void setId_dependente(Integer id_dependente) {
        this.id_dependente = id_dependente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id_dependente=" + id_dependente +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", escolaridade='" + escolaridade + '\'' +
                ", funcionario=" + funcionario +
                '}';
    }
}



