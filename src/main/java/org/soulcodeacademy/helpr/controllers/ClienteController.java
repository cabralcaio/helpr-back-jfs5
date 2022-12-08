package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // coletar as requisições
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_FUNCIONARIO')")
    @GetMapping("/clientes")
    public List<Cliente> mostrarLista() {
        return this.clienteService.listarTodos();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_FUNCIONARIO')")
    @GetMapping("/clientes/{idCliente}")
    public Cliente getCliente(@PathVariable Integer idCliente){
        return this.clienteService.getCliente(idCliente);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_FUNCIONARIO')")
    @PostMapping("/clientes")
    public Cliente salvarCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = this.clienteService.salvar(dto);
        return cliente;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_FUNCIONARIO')")
    @PutMapping("/clientes/{idCliente}")
    public Cliente atualizar(@PathVariable Integer idCliente, @Valid @RequestBody ClienteDTO dto) {
        Cliente atualizado = this.clienteService.atualizar(idCliente, dto);
        return atualizado; // CORPO da Resposta em JSON
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_FUNCIONARIO')")
    @DeleteMapping("/clientes/{idCliente}")
    public void deletar(@PathVariable Integer idCliente) {
        this.clienteService.deletar(idCliente);
    }
}
// Deserializar => JSON -> Classe
// Serializar => Classe -> JSON