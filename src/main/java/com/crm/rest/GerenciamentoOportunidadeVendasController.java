package com.crm.rest;

import com.crm.dtos.ClienteDTO;
import com.crm.dtos.GerenciamentoOportunidadeVendasDTO;
import com.crm.models.Cliente;
import com.crm.models.GerenciamentoOportunidadeVendas;
import com.crm.service.GerenciamentoOportunidadeVendasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerenciamento")
public class GerenciamentoOportunidadeVendasController {
    @Autowired
    private GerenciamentoOportunidadeVendasService gerenciamentoOportunidadeVendasService;

    @GetMapping("/listarGerenciamento")// Obtém todos os clientes
    public ResponseEntity<Iterable<GerenciamentoOportunidadeVendas>> getGerenciamentoOportunidadeVendas() {
        return ResponseEntity.ok(gerenciamentoOportunidadeVendasService.getGerenciamentoOportunidadeVendas());
    }

    @GetMapping("/listarGerenciamento/{id}") // Obtém um cliente por ID
    public ResponseEntity<GerenciamentoOportunidadeVendas> getGerenciamentoOportunidadeVendasById(@PathVariable("id") int idGerenciamentoOportunidadeVendas) {
        return ResponseEntity.ok(gerenciamentoOportunidadeVendasService.getGerenciamentoOportunidadeVendasById(idGerenciamentoOportunidadeVendas));
    }

    @PostMapping(("/cadastrarGerenciamento")) // Adiciona um novo cliente
    public ResponseEntity<GerenciamentoOportunidadeVendas> addClient(@Valid @RequestBody GerenciamentoOportunidadeVendasDTO gerenciamentoOportunidadeVendasDTO) {
        GerenciamentoOportunidadeVendas gerenciamentoOportunidadeVendas = gerenciamentoOportunidadeVendasService.addGerenciamentoOportunidadeVendas(gerenciamentoOportunidadeVendasDTO);
        return new ResponseEntity<>(gerenciamentoOportunidadeVendas, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarGerenciamento/{id}") // Atualiza um cliente por ID
    public ResponseEntity<GerenciamentoOportunidadeVendas> updateGerenciamentoOportunidadeVendas(@PathVariable("id") int idGerenciamentoOportunidadeVendas,
                                                   @Valid @RequestBody GerenciamentoOportunidadeVendasDTO gerenciamentoOportunidadeVendasDTO) {
        GerenciamentoOportunidadeVendas updateGerenciamentoOportunidadeVendas =
                gerenciamentoOportunidadeVendasService.updateGerenciamentoOportunidadeVendas(idGerenciamentoOportunidadeVendas, gerenciamentoOportunidadeVendasDTO);
        return ResponseEntity.ok(updateGerenciamentoOportunidadeVendas);
    }

    @DeleteMapping("/excluirGerenciamento/{id}") // Deleta um cliente por ID
    public ResponseEntity<Void> deleteGerenciamentoOportunidadeVendasById(@PathVariable("id") int idGerenciamentoOportunidadeVendas) {
        gerenciamentoOportunidadeVendasService.deleteGerenciamentoOportunidadeVendasById(idGerenciamentoOportunidadeVendas);
        return ResponseEntity.noContent().build();
    }
}
