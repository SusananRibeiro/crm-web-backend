package com.crm.rest;
import com.crm.dtos.GerenciamentoDeOportunidadeDeVendaRequest;
import com.crm.dtos.GerenciamentoDeOportunidadeDeVendaResponse;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.exceptions.utils.ResponseUtil;
import com.crm.service.GerenciamentoDeOportunidadeDeVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gerenciamento")
public class GerenciamentoDeOportunidadeDeVendaController {
    @Autowired
    private GerenciamentoDeOportunidadeDeVendaService gerenciamentoDeOportunidadeDeVendaService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/listarGerenciamento")
    public ResponseEntity<List<GerenciamentoDeOportunidadeDeVendaResponse>> carregarGerenciamentoDeOportunidadeDeVenda(){
        return ResponseEntity.ok(gerenciamentoDeOportunidadeDeVendaService.carregarGerenciamentoDeOportunidadeDeVenda());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/listarGerenciamento/{id}")
    public ResponseEntity<GerenciamentoDeOportunidadeDeVendaResponse> carregarGerenciamentoDeOportunidadeDeVendaById
            (@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(gerenciamentoDeOportunidadeDeVendaService.carregarGerenciamentoDeOportunidadeDeVendaById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/cadastrarGerenciamento")
    public ResponseEntity<?> criarGerenciamentoDeOportunidadeDeVenda
            (@RequestBody GerenciamentoDeOportunidadeDeVendaRequest gerenciamentoDeOportunidadeDeVendaRequest) throws ResourceNotFoundException{
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(gerenciamentoDeOportunidadeDeVendaService
                    .criarGerenciamentoDeOportunidadeDeVenda(gerenciamentoDeOportunidadeDeVendaRequest));
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
    @PutMapping("/atualizarGerenciamento/{id}")
    public ResponseEntity<?> atualizarGerenciamentoDeOportunidadeDeVenda(@PathVariable  Long id,
                       @RequestBody GerenciamentoDeOportunidadeDeVendaRequest gerenciamentoDeOportunidadeDeVendaRequest){
        try {
            return ResponseEntity.ok(gerenciamentoDeOportunidadeDeVendaService
                    .atualizarGerenciamentoDeOportunidadeDeVenda(id, gerenciamentoDeOportunidadeDeVendaRequest));
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
    @DeleteMapping("/excluirGerenciamento/{id}")
    public ResponseEntity<Void> deletarGerenciamentoDeOportunidadeDeVenda(@PathVariable Long id){
        gerenciamentoDeOportunidadeDeVendaService.deletarGerenciamentoDeOportunidadeDeVenda(id);

        return ResponseEntity.ok(null);
    }

}
