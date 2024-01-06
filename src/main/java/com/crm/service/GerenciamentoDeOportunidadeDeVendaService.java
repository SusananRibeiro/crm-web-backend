package com.crm.service;
import com.crm.mappers.GerenciamentoDeOportunidadeDeVendaMapper;
import com.crm.dtos.GerenciamentoDeOportunidadeDeVendaRequest;
import com.crm.dtos.GerenciamentoDeOportunidadeDeVendaResponse;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.models.Cliente;
import com.crm.models.GerenciamentoDeOportunidadeDeVenda;
import com.crm.models.enums.EstagioDaOportunidade;
import com.crm.repositories.GerenciamentoDeOportunidadeDeVendaClienteRepository;
import com.crm.repositories.GerenciamentoDeOportunidadeDeVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GerenciamentoDeOportunidadeDeVendaService {
    @Autowired
    private GerenciamentoDeOportunidadeDeVendaRepository gerenciamentoDeOportunidadeDeVendaRepository;
    @Autowired
    private GerenciamentoDeOportunidadeDeVendaClienteRepository gerenciamentoDeOportunidadeDeVendaClienteRepository;
    // GET de todos
    public GerenciamentoDeOportunidadeDeVendaResponse criarGerenciamentoDeOportunidadeDeVenda
            (GerenciamentoDeOportunidadeDeVendaRequest gerenciamentoDeOportunidadeDeVendaRequest) throws Exception {
        this.validacaoManutencaoGerenciamentoDeOportunidadeDeVenda(gerenciamentoDeOportunidadeDeVendaRequest);

        Optional<Cliente> cliente = gerenciamentoDeOportunidadeDeVendaClienteRepository.findById(gerenciamentoDeOportunidadeDeVendaRequest.getClienteId());
        if(!cliente.isPresent()){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        GerenciamentoDeOportunidadeDeVenda audienciaRetorno = gerenciamentoDeOportunidadeDeVendaRepository
                .save(GerenciamentoDeOportunidadeDeVendaMapper
                .gerenciamentoDeOportunidadeDeVendaRequestToGerenciamento(gerenciamentoDeOportunidadeDeVendaRequest, cliente.get()));

        return GerenciamentoDeOportunidadeDeVendaMapper
                .gerenciamentoToGerenciamentoDeOportunidadeDeVendaResponse(audienciaRetorno);
    }

    // POST
    public List<GerenciamentoDeOportunidadeDeVendaResponse> carregarGerenciamentoDeOportunidadeDeVenda() {
        List<GerenciamentoDeOportunidadeDeVenda> audienciaList = gerenciamentoDeOportunidadeDeVendaRepository.findAll();

        List<GerenciamentoDeOportunidadeDeVendaResponse> out = audienciaList.stream()
                .map(GerenciamentoDeOportunidadeDeVendaMapper::gerenciamentoToGerenciamentoDeOportunidadeDeVendaResponse)
                .collect(Collectors.toList());
        return out;
    }

    // PUT
    public boolean atualizarGerenciamentoDeOportunidadeDeVenda
            (Long id, GerenciamentoDeOportunidadeDeVendaRequest gerenciamentoDeOportunidadeDeVendaRequest) throws ResourceNotFoundException {
        this.validacaoManutencaoGerenciamentoDeOportunidadeDeVenda(gerenciamentoDeOportunidadeDeVendaRequest);

        Optional<Cliente> cliente = gerenciamentoDeOportunidadeDeVendaClienteRepository
                .findById(gerenciamentoDeOportunidadeDeVendaRequest.getClienteId());
        if(!cliente.isPresent()){
            throw new ResourceNotFoundException("Cliente não encontrado!");
        }

        Optional<GerenciamentoDeOportunidadeDeVenda> gerenciamentoDeOportunidadeDeVenda =
                gerenciamentoDeOportunidadeDeVendaRepository.findById(id).map(record ->{
            record.setClienteId(cliente.get());
            record.setEstagioDaOportunidade(gerenciamentoDeOportunidadeDeVendaRequest.getEstagioDaOportunidade());
            record.setValorEstimadoDaVenda(gerenciamentoDeOportunidadeDeVendaRequest.getValorEstimadoDaVenda());

            return gerenciamentoDeOportunidadeDeVendaRepository.save(record);
        });

        if(!gerenciamentoDeOportunidadeDeVenda.isPresent()){
            throw new ResourceNotFoundException("Gerenciamento de oportunidade de venda não existe!");
        }

        GerenciamentoDeOportunidadeDeVendaResponse out = GerenciamentoDeOportunidadeDeVendaMapper
                .gerenciamentoToGerenciamentoDeOportunidadeDeVendaResponse(gerenciamentoDeOportunidadeDeVenda.get());
        return true;
    }
    // DELETE
    public void deletarGerenciamentoDeOportunidadeDeVenda(Long id) {
        if (!this.gerenciamentoDeOportunidadeDeVendaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Gerenciamento de oportunidade de venda não existe");
        }
        gerenciamentoDeOportunidadeDeVendaRepository.deleteById(id);
    }

    // GET por ID
    public GerenciamentoDeOportunidadeDeVendaResponse carregarGerenciamentoDeOportunidadeDeVendaById(Long id)
            throws ResourceNotFoundException {
        Optional<GerenciamentoDeOportunidadeDeVenda> optionalGerenciamento = gerenciamentoDeOportunidadeDeVendaRepository.findById(id);


        if(!optionalGerenciamento.isPresent()){
            throw new ResourceNotFoundException("Gerenciamento de oportunidade de venda não informado!");
        }

        GerenciamentoDeOportunidadeDeVenda gerenciamentoDeOportunidadeDeVenda = optionalGerenciamento.get();

        GerenciamentoDeOportunidadeDeVendaResponse out = GerenciamentoDeOportunidadeDeVendaMapper
                .gerenciamentoToGerenciamentoDeOportunidadeDeVendaResponse(gerenciamentoDeOportunidadeDeVenda);

        return out;
    }

    private void validacaoManutencaoGerenciamentoDeOportunidadeDeVenda(GerenciamentoDeOportunidadeDeVendaRequest gerenciamento)
            throws ResourceNotFoundException{

        if (gerenciamento.getEstagioDaOportunidade() == null){
            throw new ResourceNotFoundException("Estagio da oportunidade não foi informado!");
        }

        if (gerenciamento.getValorEstimadoDaVenda() == 0){
            throw new ResourceNotFoundException("Valor estimado da venda não foi informado!");
        }

        if(verificarEstagioDaOportunidade(gerenciamento.getEstagioDaOportunidade()) == false) {
            throw new ResourceNotFoundException("Estagio da oportunidade invalido!");
        }
    }

    // Validação Estagio Da Oportunidade
    public boolean verificarEstagioDaOportunidade(EstagioDaOportunidade estagio) {
        try {
            // Verifica se o enum contém o valor passado
            EstagioDaOportunidade.valueOf(estagio.name());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

//    public boolean verificarEstagioDaOportunidade(String estagio) {
//        EstagioDaOportunidade estagioDaOportunidade = EstagioDaOportunidade.valueOf(estagio);
//        try {
//            estagioDaOportunidade.valueOf(estagio);
//            return true;
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }
}
