package com.crm.service;
import com.crm.dtos.ClienteDTO;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.models.Cliente;
import com.crm.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    // O construtor injeta o ClienteRepository para o serviço
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // GET todos os clientes - Método para retornar todos os clientes, o nome do método precisa ser em inglês
    public Iterable<Cliente> getClients() {
        return this.clienteRepository.findAll();
    }

    // GET (com ID específico) - Método para obter um cliente por ID, o nome do método precisa ser em inglês
    public Cliente getClientById(int idCliente) {
        Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);

        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente não existe");
            // Lança uma exceção caso o cliente não seja encontrado
        }

        return cliente.get();
    }

    // POST - Método para adicionar um cliente, o nome do método precisa ser em inglês
    public Cliente addClient(ClienteDTO clienteDTO) {
        this.validateCliente(clienteDTO);
        ModelMapper mapper = new ModelMapper();
        Cliente clienteToPersist = mapper.map(clienteDTO, Cliente.class);
        // Mapeia o ClienteDTO para a entidade Cliente e salva no repositório
        this.clienteRepository.save(clienteToPersist);
        return clienteToPersist;
    }

    // PUT - Método para atualizar um cliente por ID, o nome do método precisa ser em inglês
    public Cliente updateClient(int idCliente, ClienteDTO clienteDTO) {
        this.validateCliente(clienteDTO);
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(idCliente);

        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente não existe");
        }
        Cliente cliente = optionalCliente.get();
        ModelMapper mapper = new ModelMapper();
        mapper.map(clienteDTO, cliente);
        this.clienteRepository.save(cliente);
        return cliente;
    }

    // DELETE - Método para excluir um cliente por ID, o nome do método precisa ser em inglês
    public void deleteClientById(int idCliente) {
        if (!this.clienteRepository.existsById(idCliente)) {
            throw new ResourceNotFoundException("Cliente não existe");
        }
        this.clienteRepository.deleteById(idCliente);
    }

    // Método para validação
    private void validateCliente(ClienteDTO clienteDTO) {

        if (clienteDTO.getCpf().isEmpty()) {
            // Lida com os erros de validação, por exemplo, lançando uma exceção ou tratando os erros de outra forma
            throw new ResourceNotFoundException("Dados do cliente inválidos");
        }
    }
}
