package com.crm.rest;

import com.crm.dtos.ClienteDTO;
import com.crm.dtos.RegistroDeInteracoesDTO;
import com.crm.models.Cliente;
import com.crm.models.RegistroDeInteracoes;
import com.crm.service.RegistroDeInteracoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro")
public class RegistroDeInteracoesController {
    @Autowired
    private RegistroDeInteracoesService registroDeInteracoesService;

    @GetMapping("/listarRegistro")
    public ResponseEntity<Iterable<RegistroDeInteracoes>> getRegistroDeInteracoes() {
        return ResponseEntity.ok(registroDeInteracoesService.getRegistroDeInteracoes());
    }

    @GetMapping("/listarRegistro/{id}")
    public ResponseEntity<RegistroDeInteracoes> getRegistroDeInteracoesById(@PathVariable("id") int idRegistroDeInteracoes) {
        return ResponseEntity.ok(registroDeInteracoesService.getRegistroDeInteracoesById(idRegistroDeInteracoes));
    }

    @PostMapping(("/cadastrarRegistro"))
    public ResponseEntity<RegistroDeInteracoes> addRegistroDeInteracoes(@Valid @RequestBody RegistroDeInteracoesDTO registroDeInteracoesDTO) {
        RegistroDeInteracoes registroDeInteracoes = registroDeInteracoesService.addRegistroDeInteracoes(registroDeInteracoesDTO);
        return new ResponseEntity<>(registroDeInteracoes, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarRegistro/{id}")
    public ResponseEntity<RegistroDeInteracoes> updateRegistroDeInteracoes(@PathVariable("id") int idRegistroDeInteracoes,
                                                @Valid @RequestBody RegistroDeInteracoesDTO registroDeInteracoesDTO) {
        RegistroDeInteracoes updatedRegistroDeInteracoes = registroDeInteracoesService.updateRegistroDeInteracoes(idRegistroDeInteracoes,
                registroDeInteracoesDTO);
        return ResponseEntity.ok(updatedRegistroDeInteracoes);
    }

    @DeleteMapping("/excluirRegistro/{id}")
    public ResponseEntity<Void> deleteRegistroDeInteracoesById(@PathVariable("id") int idRegistroDeInteracoes) {
        registroDeInteracoesService.deleteRegistroDeInteracoesById(idRegistroDeInteracoes);
        return ResponseEntity.noContent().build();
    }
}
