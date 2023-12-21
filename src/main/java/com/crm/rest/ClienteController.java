package com.crm.rest;

import com.crm.dtos.ClienteDTO;
import com.crm.models.Cliente;
import com.crm.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService; // variavel para armazenar o ClientService

    @GetMapping("/listarClientes")// Obtém todos os clientes
    public ResponseEntity<Iterable<Cliente>> getClients() {
        return ResponseEntity.ok(clienteService.getClients());
    }

    @GetMapping("/listarClientes/{id}") // Obtém um cliente por ID
    public ResponseEntity<Cliente> getClientById(@PathVariable("id") int idCliente) {
        return ResponseEntity.ok(clienteService.getClientById(idCliente));
    }

    @PostMapping(("/cadastrarCliente")) // Adiciona um novo cliente
    public ResponseEntity<Cliente> addClient(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.addClient(clienteDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarCliente/{id}") // Atualiza um cliente por ID
    public ResponseEntity<Cliente> updateClient(@PathVariable("id") int idCliente, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente updatedCliente = clienteService.updateClient(idCliente, clienteDTO);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/excluirCliente/{id}") // Deleta um cliente por ID
    public ResponseEntity<Void> deleteClientById(@PathVariable("id") int idCliente) {
        clienteService.deleteClientById(idCliente);
        return ResponseEntity.noContent().build();
    }
}
