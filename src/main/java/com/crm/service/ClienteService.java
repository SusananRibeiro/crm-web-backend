package com.crm.service;
import com.crm.dtos.ClienteRequest;
import com.crm.dtos.ClienteResponse;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.exceptions.utils.StringUtil;
import com.crm.mappers.ClienteMapper;
import com.crm.models.Cliente;
import com.crm.models.enums.EstadosDoBrasil;
import com.crm.repositories.ClienteRelatorioRepository;
import com.crm.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteRelatorioRepository clienteRelatorioRepository;
// GET de todos
    public List<ClienteResponse> carregarClientes() {
        List<Cliente> clientesList = clienteRepository.findAll();

        List<ClienteResponse> out = clientesList
                .stream()
                .map(ClienteMapper:: clientesToClientesResponseDom)
                .collect(Collectors.toList());
        return out;
    }

// POST
    public ClienteResponse criarCliente(ClienteRequest clienteRequest) throws ResourceNotFoundException {
        this.validacaoManutencaoCliente(clienteRequest);

        if (validarCPF(clienteRequest.getCpf())) {
            throw new ResourceNotFoundException("CPF já cadastrado para outro cliente!");
        }
        Cliente clienteRetorno = clienteRepository.save(ClienteMapper
                .clientesRequestDomToClientes(clienteRequest));
        return ClienteMapper.clientesToClientesResponseDom(clienteRetorno);
    }

// PUT
    public ClienteResponse atualizarCliente(Long id, ClienteRequest clientesRequestDom) throws ResourceNotFoundException {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            // Implemente suas próprias validações aqui, se necessário
            validacaoManutencaoCliente(clientesRequestDom);

            // Verificar se o CPF do cliente existente corresponde ao CPF informado na requisição
            if (!cliente.getCpf().equals(clientesRequestDom.getCpf())) {
                // CPF informado é diferente do CPF armazenado para este cliente, então valida o CPF existente
                if (validarCPF(clientesRequestDom.getCpf())) {
                    throw new ResourceNotFoundException("CPF já cadastrado para outro cliente!");
                }
            }
            // Impedir a alteração do CPF
            if (!cliente.getCpf().equals(clientesRequestDom.getCpf())) {
                throw new ResourceNotFoundException("Não é permitido alterar o CPF do cliente!");
            }
            // Atualizar os dados do cliente (exceto CPF)
            cliente.setNomeCompleto(clientesRequestDom.getNomeCompleto());
            cliente.setRua(clientesRequestDom.getRua());
            cliente.setNumero(clientesRequestDom.getNumero());
            cliente.setBairro(clientesRequestDom.getBairro());
            cliente.setCidade(clientesRequestDom.getCidade());
            cliente.setUf(clientesRequestDom.getUf());
            cliente.setCep(clientesRequestDom.getCep());
            cliente.setPais(clientesRequestDom.getPais());
            cliente.setTelefone(clientesRequestDom.getTelefone());
            cliente.setEmail(clientesRequestDom.getEmail());
            cliente.setComplemento(clientesRequestDom.getComplemento());

            // Salvar as alterações no banco de dados
            Cliente clienteAtualizado = clienteRepository.save(cliente);

            // Criar e retornar a resposta de sucesso com os dados atualizados do cliente
            ClienteResponse out = ClienteMapper.clientesToClientesResponseDom(clienteAtualizado);
            return out;
        } else {
            throw new ResourceNotFoundException("Cliente informado não existe!");
        }
    }
// DELETE
    public void deletarCliente(Long id) {
        if (!this.clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não existe");
        }
        clienteRepository.deleteById(id);
    }
// GET por ID
    public ClienteResponse carregarClienteById(Long id) throws ResourceNotFoundException {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if(!optionalCliente.isPresent()) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        Cliente cliente = optionalCliente.get();

        ClienteResponse out = ClienteMapper.clientesToClientesResponseDom(cliente);
        return out;
    }

    // Validações
    private void validacaoManutencaoCliente(ClienteRequest clienteRequest) throws ResourceNotFoundException {
        if(StringUtil.validarString(clienteRequest.getNomeCompleto())){
            throw new ResourceNotFoundException("O nome do cliente é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getCpf()) || !clienteRequest.getCpf().matches("\\d{11}")){
            throw new ResourceNotFoundException("O CPF do cliente é obrigatório.");
        }

        if (clienteRequest.getDataNascimento() == null) {
            throw new ResourceNotFoundException("A data de nascimento é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getRua())){
            throw new ResourceNotFoundException("A rua é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getBairro())){
            throw new ResourceNotFoundException("O bairro é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getBairro())){
            throw new ResourceNotFoundException("A cidade é obrigatório.");
        }
        if(StringUtil.validarString(String.valueOf(clienteRequest.getUf()))){
            throw new ResourceNotFoundException("A sigla do estado é obrigatório.");
        }
        if(verificarUF(String.valueOf(clienteRequest.getUf())) == false){
            throw new ResourceNotFoundException("Estado inválido!");
        }
        if(StringUtil.validarString(String.valueOf(clienteRequest.getCep()))){
            throw new ResourceNotFoundException("O CEP é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getPais())){
            throw new ResourceNotFoundException("O país é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getTelefone()) || !clienteRequest.getTelefone().matches("\\d{11}")){
            throw new ResourceNotFoundException("O telefone é obrigatório.");
        }

    }

    // Validação do CPF
    public boolean validarCPF(String cpf) {
        return clienteRelatorioRepository.existsByCpf(cpf);
    }

    // Validação estados
    public boolean verificarUF(String uf) {
        EstadosDoBrasil estadosDoBrasil = EstadosDoBrasil.valueOf(uf);
        try {
            estadosDoBrasil.valueOf(uf);
            return true; // O estado é válido
        } catch (IllegalArgumentException e) {
            return false; // O estado é inválido
        }
    }


}
