package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Dependente;

import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.DependenteDTO;
import org.soulcodeacademy.helpr.repositories.DependenteRepository;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {

    @Autowired
    private DependenteRepository dependenteRepository;


    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @Autowired
    private FuncionarioService funcionarioService;



    public List<Dependente> listarDependente(){
        return this.dependenteRepository.findAll();
    }

    public Dependente getDependente(Integer idDependente){
        Optional<Dependente> dependente = this.dependenteRepository.findById(idDependente);
        if(dependente.isEmpty()){
            throw new RuntimeException("Dependente não encontrado");
        }else {
            return dependente.get();
        }

    }

    public Dependente salvar(DependenteDTO dto){
        Funcionario funcionario = this.funcionarioService.getFuncionario(dto.getId_Responsavel());

        Dependente dependenteNovo = new Dependente( dto.getNome(), dto.getCpf(), LocalDate.parse(dto.getDataNascimento()),dto.getEscolaridade(),dto.getId_Responsavel());

        Period period = Period.between(LocalDate.parse(dto.getDataNascimento()), LocalDate.now());

        if(period.getYears() >= 18){
            throw new RuntimeException("Não é permitido registrar dependentes maiores de 18 anos.");
        }

        if(!funcionarioRepository.exists(Example.of(dependenteNovo.getFuncionario()))){
            funcionarioRepository.save(dependenteNovo.getFuncionario());
        }

        return this.dependenteRepository.save(dependenteNovo);
    }

    public Dependente atualizar(Integer idDependente, DependenteDTO dto){

        Dependente DependenteAtual = new Dependente();

        DependenteAtual.setId_dependente(idDependente);
        DependenteAtual.setNome(dto.getNome());
        DependenteAtual.setCpf(dto.getCpf());
        DependenteAtual.setDataDeNascimento();
        DependenteAtual.setEscolaridade("escolaridade");
        DependenteAtual.setFuncionario(new Funcionario());
        DependenteAtual.getFuncionario().setId(dto.getId_Responsavel());

        Dependente dadosAntigos = dependenteRepository.findById(idDependente).get();

        if(dadosAntigos == null || dadosAntigos.getFuncionario().getId() != DependenteAtual.getFuncionario().getId()){
            throw new RuntimeException("Não é permitido alterar o responsavel do dependente.");
        }

        return this.dependenteRepository.save(DependenteAtual);
    }

    //Deletar
    public void deletar(Integer idDependente) {
        this.dependenteRepository.deleteById(idDependente);
    }

    public Dependente getDependenteByCPF(String cpf){
        Dependente dependente = this.dependenteRepository.findByCpf(cpf);

        return dependente;
    }

    public List<Dependente> getDependenteByFuncionarioId(Integer idFuncionario){

        return this.dependenteRepository.findByFuncionarioId(idFuncionario);
    }

    public List<Dependente> getByFaixaData(LocalDate data1, LocalDate data2){

        return dependenteRepository.buscarEntreDatas(data1, data2);
    }

}


