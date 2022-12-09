package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Usuario;
import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.soulcodeacademy.helpr.services.ChamadoService;
import org.soulcodeacademy.helpr.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ChamadoService chamadoService;


    @GetMapping("/usuarios/email/{email}")
    private Usuario  listarPorEmail(@PathVariable String email) {
        return this.usuarioService.filtrarPorEmail(email);
    }

    @GetMapping("/usuarios/cpf/{cpf}")
    private Optional<Usuario>  listarPorCPF(@PathVariable String cpf) {
        return this.usuarioService.filtrarPorCpf(cpf);
    }

    @GetMapping("/usuarios/busca")
    private List<Usuario> listarPorBusca(@RequestParam String nome) {
        return this.usuarioService.filtrarPorNome(nome);
    }

    @GetMapping("/usuarios/{idUsuario}")
    private Perfil buscaPerfil(@PathVariable Integer idUsuario) {
        Usuario usuario = this.usuarioService.getUsuarioById(idUsuario);
        return usuario.getPerfil();
    }

    @GetMapping("/usuarios/funcionarios/logado/chamados")
    private List<Chamado> ListaChamadosFuncionarioAtual (Principal principal) {
        Usuario usuario = this.usuarioService.filtrarPorEmail(principal.getName());
        Integer idUsuario = this.usuarioService.retornaId(usuario);//corrigir
        return this.chamadoService.listarPorFuncionario(idUsuario); //falta so colocar o idFuncionario de forma dinâmica
    }

    @GetMapping("/usuarios/clientes/logado/chamados")
    private List<Chamado> ListaChamadosClienteAtual (Principal principal) {
        Usuario usuario = this.usuarioService.filtrarPorEmail(principal.getName());
        Integer idUsuario = this.usuarioService.retornaId(usuario);
        return this.chamadoService.listarPorCliente(idUsuario);
    }

}
