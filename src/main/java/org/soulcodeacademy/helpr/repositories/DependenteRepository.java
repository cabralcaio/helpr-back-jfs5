package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DependenteRepository extends JpaRepository<Dependente,Integer> {



    @Query(value = "SELECT * FROM dependente WHERE data_de_nascimento BETWEEN :data1 AND :data2", nativeQuery = true)
    public List<Dependente> buscarEntreDatas(LocalDate data1, LocalDate data2);

    public Dependente findByCpf(String cpf);

    public List<Dependente> findByEscolaridade(String escolaridade);

    @Query(value = "SELECT d.* FROM dependente d INNER JOIN helpr.usuarios u ON d.id_responsavel = u.id WHERE u.id = :id ", nativeQuery = true)
    public List<Dependente> findByFuncionarioId(Integer id);
}


