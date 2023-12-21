package com.crm.service;
import com.crm.dtos.GerenciamentoOportunidadeVendasDTO;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.models.GerenciamentoOportunidadeVendas;
import com.crm.repositories.GerenciamentoOportunidadeVendasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GerenciamentoOportunidadeVendasService {
    @Autowired
    private GerenciamentoOportunidadeVendasRepository gerenciamentoOportunidadeVendasRepository;

    // GET todos os clientes
    public Iterable<GerenciamentoOportunidadeVendas> getGerenciamentoOportunidadeVendas() {
        return this.gerenciamentoOportunidadeVendasRepository.findAll();
    }

    // GET (com ID específico)
    public GerenciamentoOportunidadeVendas getGerenciamentoOportunidadeVendasById(int idGerenciamentoOportunidadeVendas) {
        Optional<GerenciamentoOportunidadeVendas> gerenciamentoOportunidadeVendas =
                this.gerenciamentoOportunidadeVendasRepository.findById(idGerenciamentoOportunidadeVendas);

        if (gerenciamentoOportunidadeVendas.isEmpty()) {
            throw new ResourceNotFoundException("Gerenciamento de Oportunidade Vendas não existe");
            // Lança uma exceção caso o cliente não seja encontrado
        }

        return gerenciamentoOportunidadeVendas.get();
    }

    // POST
    public GerenciamentoOportunidadeVendas addGerenciamentoOportunidadeVendas(GerenciamentoOportunidadeVendasDTO gerenciamentoOportunidadeVendasDTO) {
        this.validateGerenciamentoOportunidadeVendas(gerenciamentoOportunidadeVendasDTO);
        ModelMapper mapper = new ModelMapper();

        GerenciamentoOportunidadeVendas gerenciamentoOportunidadeVendasToPersist = mapper.map(gerenciamentoOportunidadeVendasDTO, GerenciamentoOportunidadeVendas.class);
        // Mapeia o GerenciamentoOportunidadeVendasDTO para a entidade GerenciamentoOportunidadeVendas e salva no repositório
        this.gerenciamentoOportunidadeVendasRepository.save(gerenciamentoOportunidadeVendasToPersist);
        return gerenciamentoOportunidadeVendasToPersist;
    }

    // PUT
    public GerenciamentoOportunidadeVendas updateGerenciamentoOportunidadeVendas(int idGerenciamentoOportunidadeVendas,
                                             GerenciamentoOportunidadeVendasDTO gerenciamentoOportunidadeVendasDTO) {
        this.validateGerenciamentoOportunidadeVendas(gerenciamentoOportunidadeVendasDTO);
        Optional<GerenciamentoOportunidadeVendas> optionalGerenciamentoOportunidadeVendas =
                this.gerenciamentoOportunidadeVendasRepository.findById(idGerenciamentoOportunidadeVendas);

        if (optionalGerenciamentoOportunidadeVendas.isEmpty()) {
            throw new ResourceNotFoundException("Gerenciamento de Oportunidade Vendas não existe");
        }
        GerenciamentoOportunidadeVendas gerenciamentoOportunidadeVendas = optionalGerenciamentoOportunidadeVendas.get();
        ModelMapper mapper = new ModelMapper();
        mapper.map(gerenciamentoOportunidadeVendasDTO, gerenciamentoOportunidadeVendas);
        this.gerenciamentoOportunidadeVendasRepository.save(gerenciamentoOportunidadeVendas);
        return gerenciamentoOportunidadeVendas;
    }

    // DELETE
    public void deleteGerenciamentoOportunidadeVendasById(int idGerenciamentoOportunidadeVendas) {
        if (!this.gerenciamentoOportunidadeVendasRepository.existsById(idGerenciamentoOportunidadeVendas)) {
            throw new ResourceNotFoundException("Gerenciamento de Oportunidade Vendas não existe");
        }
        this.gerenciamentoOportunidadeVendasRepository.deleteById(idGerenciamentoOportunidadeVendas);
    }

    // Método para validação
    private void validateGerenciamentoOportunidadeVendas(GerenciamentoOportunidadeVendasDTO gerenciamentoOportunidadeVendasDTO) {
        // Lida com os erros de validação, por exemplo, lançando uma exceção ou tratando os erros de outra forma
//        if(gerenciamentoOportunidadeVendasDTO.getNomeCompleto().isEmpty()) {
//            throw new ResourceNotFoundException("O nome do cliente é obrigatório.");
//        }
//        if (gerenciamentoOportunidadeVendasDTO.getCpf().isEmpty()) {
//            throw new ResourceNotFoundException("O CPF do cliente é obrigatório.");
//        }
//        if(!gerenciamentoOportunidadeVendasDTO.getCpf().matches("\\d{11}")) {
//            throw new ResourceNotFoundException("CPF do cliente inválidos");
//        }
//        if(gerenciamentoOportunidadeVendasDTO.getEndereco().isEmpty()) {
//            throw new ResourceNotFoundException("O endereço do cliente é obrigatório.");
//        }
//        if(!gerenciamentoOportunidadeVendasDTO.getTelefone().matches("\\d{11}")) {
//            throw new ResourceNotFoundException("Telefone do cliente inválidos");
//        }
    }
}
