package com.crm.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor // vai configurar um construtor com todos os parâmetros
@NoArgsConstructor
public class ClienteDTO {
    @NotBlank // campo obrigatório, é uma validação
    private String nomeCompleto;

    @NotBlank // campo obrigatório, é uma validação
    private String cpf;

    private int idade;

    @NotBlank // campo obrigatório, é uma validação
    private String endereco;

    @NotBlank // campo obrigatório, é uma validação
    private String telefone;

    @NotBlank // campo obrigatório, é uma validação
    @Email
    private String email;
}
