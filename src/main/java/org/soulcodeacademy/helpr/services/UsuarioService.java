package org.soulcodeacademy.helpr.services;

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

    public Optional<Usuario> filtrarPorEmail (String email) {
        return this.usuarioRepository.findByEmail(email);
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

}
