package com.crm.mappers;

import com.crm.dtos.ClienteRequest;
import com.crm.dtos.ClienteResponse;
import com.crm.models.Cliente;

public class ClienteMapper {
    // Request - Método para converter ClientesRequestDom para Clientes
    public static Cliente clientesRequestDomToClientes(ClienteRequest clienteRequest){
        Cliente out = new Cliente();
        out.setNomeCompleto(clienteRequest.getNomeCompleto());
        out.setCpf(clienteRequest.getCpf());
        out.setDataNascimento(clienteRequest.getDataNascimento()); // Convertendo a data de nascimento
        out.setRua(clienteRequest.getRua());
        out.setNumero(clienteRequest.getNumero());
        out.setBairro(clienteRequest.getBairro());
        out.setCidade(clienteRequest.getCidade());
        out.setUf(clienteRequest.getUf());
        out.setCep(clienteRequest.getCep());
        out.setPais(clienteRequest.getPais());
        out.setTelefone(clienteRequest.getTelefone());
        out.setEmail(clienteRequest.getEmail());
        out.setComplemento(clienteRequest.getComplemento());
        return out;
    }
    // Response
    public static ClienteResponse clientesToClientesResponseDom(Cliente clientes){
        ClienteResponse out = new ClienteResponse();
        out.setId(clientes.getId());
        out.setNomeCompleto(clientes.getNomeCompleto());
        out.setCpf(clientes.getCpf());
        out.setDataNascimento(clientes.getDataNascimento());
        out.setRua(clientes.getRua());
        out.setNumero(clientes.getNumero());
        out.setBairro(clientes.getBairro());
        out.setCidade(clientes.getCidade());
        out.setUf(clientes.getUf());
        out.setCep(clientes.getCep());
        out.setPais(clientes.getPais());
        out.setTelefone(clientes.getTelefone());
        out.setEmail(clientes.getEmail());
        out.setComplemento(clientes.getComplemento());

        return out;
    }

}
