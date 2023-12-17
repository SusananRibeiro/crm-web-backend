package com.crm.dtos;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor // vai configurar um construtor com todos os parâmetros
@NoArgsConstructor
public class RegistroDeInteracoesDTO {

    @NotBlank // campo obrigatório, é uma validação
    private String descrição;
    @NotBlank // campo obrigatório, é uma validação
    private LocalDateTime dataDaInteracao;

//    @NotBlank // campo obrigatório, é uma validação
//    private Cliente clienteId;

}
