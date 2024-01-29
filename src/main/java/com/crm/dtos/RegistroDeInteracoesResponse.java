package com.crm.dtos;
import com.crm.models.enums.StatusDaInterferencia;
import lombok.Data;
import java.time.LocalDate;
@Data
public class RegistroDeInteracoesResponse {
    private Long id;
    private LocalDate dataDaInteracao;
    private String canalDeComunicacao; // ex: telefone, e-mail, chat online, pessoalmente ou por outro meio
    private String responsavelPelaInteracao;
    private String descricao;
    private StatusDaInterferencia statusDaInterferencia;
}
