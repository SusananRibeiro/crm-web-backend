package com.crm.dtos;

import com.crm.models.enums.EstadosDoBrasil;
import lombok.Data;
import java.time.LocalDate;
@Data
public class ClienteResponse {
    private Long id;
    
    private String nomeCompleto;

    private String cpf;

    private LocalDate dataNascimento;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private EstadosDoBrasil uf;

    private int cep;

    private String pais;

    private String telefone;

    private String email;

    private String complemento;
}
