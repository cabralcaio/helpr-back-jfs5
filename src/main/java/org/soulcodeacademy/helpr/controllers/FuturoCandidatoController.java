package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.FuturoCandidato;
import org.soulcodeacademy.helpr.domain.dto.FuturoCandidatoDTO;
import org.soulcodeacademy.helpr.domain.enums.Setor;
import org.soulcodeacademy.helpr.services.FuturoCandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FuturoCandidatoController {

    @Autowired
    private FuturoCandidatoService futuroCandidatoService;

    @GetMapping("/candidatos")
    private List<FuturoCandidato> listarTodos() {
        return this.futuroCandidatoService.listaTodos();
    }

    @GetMapping("candidatos/{setor}")
    private List<FuturoCandidato> listarPorSetor(@RequestParam Setor setor) {
        return this.futuroCandidatoService.listarPorSetor(setor);
    }

    @GetMapping("/candidatos/email")
    private List<FuturoCandidato> listarPorEmail(@RequestParam String email) {
        return this.futuroCandidatoService.listarPorEmail(email);
    }

    @GetMapping("/candidatos/nome")
    private  List<FuturoCandidato> listarPorNome(@RequestParam String nomeCompleto) {
        return  this.futuroCandidatoService.listarPorNome(nomeCompleto);
    }

    @PostMapping("/candidatos")
    private FuturoCandidato salavarCandidato(@Valid @RequestBody FuturoCandidatoDTO dto) {
        FuturoCandidato candidatoAtualizado = this.futuroCandidatoService.salvarCandidato(dto);
        return candidatoAtualizado;
    }

    @DeleteMapping("/candidatos/{idFuturoCandidato}")
    private void deletarCandidato(@PathVariable Integer idFuturoCandidato) {
        this.futuroCandidatoService.deletarCandidato(idFuturoCandidato);
    }
}





//        /candidatos (GET): Listar todos
//        /candidatos/{setor} (GET): Filtrar por setor, usar RequestParam
//        /candidatos/email (GET): Filtrar por email, usar RequestParam
//        /ccandidatos/nome (GET): Filtrar por nome, usar RequestParam
//        /candidatos (POST): Adicionar colaborador (método público sem login)
//        /candidatos (DELETE): Deletar colaborador