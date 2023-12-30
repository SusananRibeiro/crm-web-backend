package com.crm.rest;
import com.crm.dtos.RegistroDeInteracoesRequest;
import com.crm.dtos.RegistroDeInteracoesResponse;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.exceptions.utils.ResponseUtil;
import com.crm.service.RegistroDeInteracoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/registros")
public class RegistroDeInteracoesController {
    @Autowired
    private RegistroDeInteracoesService registroDeInteracoesService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/listarRegistro")
    public ResponseEntity<List<RegistroDeInteracoesResponse>> carregarRegistroDeInteracoes(){
        return ResponseEntity.ok(registroDeInteracoesService.carregarRegistroDeInteracoes());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/listarRegistro/{id}")
    public ResponseEntity<RegistroDeInteracoesResponse> carregarRegistroDeInteracoesById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(registroDeInteracoesService.carregarRegistroDeInteracoesById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/cadastrarRegistro")
    public ResponseEntity<?> criarRegistroDeInteracoes(@RequestBody RegistroDeInteracoesRequest registroDeInteracoesRequest){

        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(registroDeInteracoesService.criarRegistroDeInteracoes(registroDeInteracoesRequest));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/atualizarRegistro/{id}")
    public ResponseEntity<?> atualizarRegistroDeInteracoes
            (@PathVariable Long id,
             @RequestBody RegistroDeInteracoesRequest registroDeInteracoesRequest){
        try {
            return ResponseEntity.ok(
                    registroDeInteracoesService.atualizarRegistroDeInteracoes(id, registroDeInteracoesRequest));
        } catch (ResourceNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/excluirRegistro/{id}")
    public ResponseEntity<Void> deletarRegistroDeInteracoes(@PathVariable Long id){
        registroDeInteracoesService.deletarRegistroDeInteracoes(id);
        return ResponseEntity.ok(null);
    }

}
