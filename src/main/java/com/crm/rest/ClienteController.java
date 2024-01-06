package com.crm.rest;

import com.crm.dtos.ClienteRequest;
import com.crm.dtos.ClienteResponse;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.exceptions.utils.ResponseUtil;
import com.crm.repositories.ClienteRepository;
import com.crm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/listarClientes")
    public ResponseEntity<List<ClienteResponse>> carregarClientes(){
        return ResponseEntity.ok(clienteService.carregarClientes());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/listarClientes/{id}")
    public ResponseEntity<ClienteResponse> carregarClienteById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(clienteService.carregarClienteById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/cadastrarCliente")
    public ResponseEntity<?> criarCliente(@RequestBody ClienteRequest clienteRequest){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(clienteRequest));
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
    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<?> atualizarCliente
            (@PathVariable Long id,
             @RequestBody ClienteRequest clienteRequest){
        try {
            return ResponseEntity.ok(
                    clienteService.atualizarCliente(id, clienteRequest));
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
    @DeleteMapping("/excluirCliente/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);

        return ResponseEntity.ok(null);
    }
}
