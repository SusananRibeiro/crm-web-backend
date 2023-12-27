package com.crm.dtos;
import com.crm.models.enums.EstagioDaOportunidade;
import lombok.Data;

@Data
public class GerenciamentoDeOportunidadeDeVendaRequest {

    private EstagioDaOportunidade estagioDaOportunidade;
    private double valorEstimadoDaVenda;
    private Long clienteId;
}
