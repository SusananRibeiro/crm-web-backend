package com.crm.mappers;
import com.crm.dtos.GerenciamentoDeOportunidadeDeVendaRequest;
import com.crm.dtos.GerenciamentoDeOportunidadeDeVendaResponse;
import com.crm.models.Cliente;
import com.crm.models.GerenciamentoDeOportunidadeDeVenda;

public class GerenciamentoDeOportunidadeDeVendaMapper {
    // Request - Método para converter GerenciamentoDeOportunidadeDeVendaRequest para GerenciamentoDeOportunidadeDeVenda
    public static GerenciamentoDeOportunidadeDeVenda gerenciamentoDeOportunidadeDeVendaRequestToGerenciamento
            (GerenciamentoDeOportunidadeDeVendaRequest gerenciamentoDeOportunidadeDeVendaRequest, Cliente cliente){

        GerenciamentoDeOportunidadeDeVenda out = new GerenciamentoDeOportunidadeDeVenda();
        out.setEstagioDaOportunidade(gerenciamentoDeOportunidadeDeVendaRequest.getEstagioDaOportunidade());
        out.setValorEstimadoDaVenda(gerenciamentoDeOportunidadeDeVendaRequest.getValorEstimadoDaVenda());
        out.setClienteId(cliente);
        return out;
    }

    // Response
    public static GerenciamentoDeOportunidadeDeVendaResponse gerenciamentoToGerenciamentoDeOportunidadeDeVendaResponse
            (GerenciamentoDeOportunidadeDeVenda gerenciamentoDeOportunidadeDeVenda){

        GerenciamentoDeOportunidadeDeVendaResponse out = new GerenciamentoDeOportunidadeDeVendaResponse();
        out.setId(gerenciamentoDeOportunidadeDeVenda.getId());
        out.setEstagioDaOportunidade(gerenciamentoDeOportunidadeDeVenda.getEstagioDaOportunidade());
        out.setValorEstimadoDaVenda(gerenciamentoDeOportunidadeDeVenda.getValorEstimadoDaVenda());

        if (gerenciamentoDeOportunidadeDeVenda.getClienteId() != null)
        {
            out.setClienteId(gerenciamentoDeOportunidadeDeVenda.getClienteId().getId());
            out.setCliente(ClienteMapper.clientesToClientesResponseDom(gerenciamentoDeOportunidadeDeVenda.getClienteId()));
        }

        return out;
    }

    public static void atualizaGerenciamentoDeOportunidadeDeVenda
            (GerenciamentoDeOportunidadeDeVenda gerenciamentoDeOportunidadeDeVenda,
             Cliente cliente, GerenciamentoDeOportunidadeDeVendaRequest gerenciamentoDeOportunidadeDeVendaRequest){

        gerenciamentoDeOportunidadeDeVenda.setEstagioDaOportunidade(gerenciamentoDeOportunidadeDeVendaRequest.getEstagioDaOportunidade());
        gerenciamentoDeOportunidadeDeVenda.setValorEstimadoDaVenda(gerenciamentoDeOportunidadeDeVendaRequest.getValorEstimadoDaVenda());
        gerenciamentoDeOportunidadeDeVenda.setClienteId(cliente);
    }
}
