package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Usuario;
import org.soulcodeacademy.helpr.repositories.UsuarioRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> filtrarPorNome (String nome) {
        return this.usuarioRepository.findByNomeContaining(nome);
    }

    public Usuario filtrarPorEmail (String email) {
        return this.usuarioRepository.findByEmail(email).orElseThrow(() -> new RecursoNaoEncontradoError("Usuário não encontrado"));
    }

    public Optional<Usuario> filtrarPorCpf (String cpf) {
        return this.usuarioRepository.findByCpf(cpf);
    }

    public Usuario getUsuario(String cpf){
        Optional<Usuario> usuario = this.usuarioRepository.findByCpf(cpf);
        if (usuario.isEmpty()) {
            // lançar exceção
            throw new RecursoNaoEncontradoError("Usuário não encontrado!");
        } else {
            return usuario.get();
        }
    }

    public Usuario getUsuarioById(Integer idUsuario){
        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty()) {
            // lançar exceção
            throw new RecursoNaoEncontradoError("Usuário não encontrado!");
        } else {
            return usuario.get();
        }
    }

    public Integer retornaId(Usuario usuario) {
        return usuario.getId();
    }

}
