package com.crm.dtos;
import com.crm.models.Cliente;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // vai configurar um construtor com todos os parâmetros
@NoArgsConstructor
public class GerenciamentoOportunidadeVendasDTO {

    @NotBlank // campo obrigatório, é uma validação
    private String estagioDaOportunidade;

    @NotBlank // campo obrigatório, é uma validação
    private double valorEstimadoDaVenda;
    @NotBlank // campo obrigatório, é uma validação
    private Cliente clienteId;

}
