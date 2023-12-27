package com.crm.mappers;
import com.crm.dtos.RegistroDeInteracoesRequest;
import com.crm.dtos.RegistroDeInteracoesResponse;
import com.crm.models.RegistroDeInteracoes;

public class RegistroDeInteracoesMapper {
// dataDaInteracao, canalDeComunicacao, responsavelPelaInteracao, descricao, statusDaInterferencia;
    // Request - Método para converter RegistroDeInteracoesRequest para RegistroDeInteracoes
    public static RegistroDeInteracoes registroDeInteracoesRequestToRegistro(RegistroDeInteracoesRequest registroDeInteracoesRequest){
        RegistroDeInteracoes out = new RegistroDeInteracoes();
        out.setDataDaInteracao(registroDeInteracoesRequest.getDataDaInteracao());
        out.setCanalDeComunicacao(registroDeInteracoesRequest.getCanalDeComunicacao());
        out.setResponsavelPelaInteracao(registroDeInteracoesRequest.getResponsavelPelaInteracao());
        out.setDescricao(registroDeInteracoesRequest.getDescricao());
        out.setStatusDaInterferencia(registroDeInteracoesRequest.getStatusDaInterferencia());
        return out;
    }
    // Response
    public static RegistroDeInteracoesResponse registroToRegistroDeInteracoesResponse(RegistroDeInteracoes registroDeInteracoes){
        RegistroDeInteracoesResponse out = new RegistroDeInteracoesResponse();
        out.setId(registroDeInteracoes.getId());
        out.setDataDaInteracao(registroDeInteracoes.getDataDaInteracao());
        out.setCanalDeComunicacao(registroDeInteracoes.getCanalDeComunicacao());
        out.setResponsavelPelaInteracao(registroDeInteracoes.getResponsavelPelaInteracao());
        out.setDescricao(registroDeInteracoes.getDescricao());
        out.setStatusDaInterferencia(registroDeInteracoes.getStatusDaInterferencia());
        return out;
    }
}
