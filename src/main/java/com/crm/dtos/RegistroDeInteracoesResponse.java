package com.crm.dtos;
import com.crm.models.enums.StatusDaInterferencia;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class RegistroDeInteracoesResponse {
    private Long id;
    private LocalDateTime dataDaInteracao;
    private String canalDeComunicacao; // ex: telefone, e-mail, chat online, pessoalmente ou por outro meio
    private String responsavelPelaInteracao;
    private String descricao;
    private StatusDaInterferencia statusDaInterferencia;
}
