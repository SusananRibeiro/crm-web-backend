package com.crm.dtos;
import com.crm.models.enums.StatusDaInterferencia;
import lombok.Data;
import java.time.LocalDate;
@Data
public class RegistroDeInteracoesRequest {

    private LocalDate dataDaInteracao; // dataDaInteracao
    private String canalDeComunicacao; // canalDeComunicacao ex: telefone, e-mail, chat online, pessoalmente ou por outro meio
    private String responsavelPelaInteracao; // responsavelPelaInteracao
    private String descricao; // descricao
    private StatusDaInterferencia statusDaInterferencia;

}
