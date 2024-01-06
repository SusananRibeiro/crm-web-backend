package com.crm.service;
import com.crm.dtos.RegistroDeInteracoesRequest;
import com.crm.dtos.RegistroDeInteracoesResponse;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.exceptions.utils.StringUtil;
import com.crm.mappers.RegistroDeInteracoesMapper;
import com.crm.models.RegistroDeInteracoes;
import com.crm.models.enums.StatusDaInterferencia;
import com.crm.repositories.RegistroDeInteracoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RegistroDeInteracoesService {
    @Autowired
    private RegistroDeInteracoesRepository registroDeInteracoesRepository;

    // GET de todos
    public List<RegistroDeInteracoesResponse> carregarRegistroDeInteracoes() {
        List<RegistroDeInteracoes> registroDeInteracoesList = registroDeInteracoesRepository.findAll();

        List<RegistroDeInteracoesResponse> out = registroDeInteracoesList
                .stream()
                .map(RegistroDeInteracoesMapper::registroToRegistroDeInteracoesResponse)
                .collect(Collectors.toList());
        return out;

    }

    // POST
    public RegistroDeInteracoesResponse criarRegistroDeInteracoes(RegistroDeInteracoesRequest registroDeInteracoesRequest) throws ResourceNotFoundException {
        this.validacaoManutencaoRegistroDeInteracoes(registroDeInteracoesRequest);

        RegistroDeInteracoes registroDeInteracoesRetorno = registroDeInteracoesRepository.save(RegistroDeInteracoesMapper
                .registroDeInteracoesRequestToRegistro(registroDeInteracoesRequest));
        return RegistroDeInteracoesMapper.registroToRegistroDeInteracoesResponse(registroDeInteracoesRetorno);
    }

    // PUT
    public RegistroDeInteracoesResponse atualizarRegistroDeInteracoes(Long id, RegistroDeInteracoesRequest registroDeInteracoesRequest)
            throws ResourceNotFoundException {
        Optional<RegistroDeInteracoes> registroDeInteracoesExistente = registroDeInteracoesRepository.findById(id);

        if (registroDeInteracoesExistente.isPresent()) {
            RegistroDeInteracoes registroDeInteracoes = registroDeInteracoesExistente.get();
            // Implemente suas próprias validações aqui, se necessário
            validacaoManutencaoRegistroDeInteracoes(registroDeInteracoesRequest);
            // Atualizar os dados do registroDeInteracoes (exceto CPF)
            registroDeInteracoes.setDataDaInteracao(registroDeInteracoesRequest.getDataDaInteracao());
            registroDeInteracoes.setCanalDeComunicacao(registroDeInteracoesRequest.getCanalDeComunicacao());
            registroDeInteracoes.setResponsavelPelaInteracao(registroDeInteracoesRequest.getResponsavelPelaInteracao());
            registroDeInteracoes.setDescricao(registroDeInteracoesRequest.getDescricao());
            registroDeInteracoes.setStatusDaInterferencia(registroDeInteracoesRequest.getStatusDaInterferencia());
            // Salvar as alterações no banco de dados
        RegistroDeInteracoes registroDeInteracoesAtualizado = registroDeInteracoesRepository.save(registroDeInteracoes);

            // Criar e retornar a resposta de sucesso com os dados atualizados do registroDeInteracoes
        RegistroDeInteracoesResponse out = RegistroDeInteracoesMapper
                                    .registroToRegistroDeInteracoesResponse(registroDeInteracoesAtualizado);
            return out;
        } else {
            throw new ResourceNotFoundException("Registro de interações não existe!");
        }
    }
    // DELETE
    public void deletarRegistroDeInteracoes(Long id) {
        if (!this.registroDeInteracoesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro de interações não existe");
        }
        registroDeInteracoesRepository.deleteById(id);
    }
    // GET por ID
    public RegistroDeInteracoesResponse carregarRegistroDeInteracoesById(Long id) throws ResourceNotFoundException {
        Optional<RegistroDeInteracoes> optionalRegistroDeInteracoes = registroDeInteracoesRepository.findById(id);

        if(!optionalRegistroDeInteracoes.isPresent()) {
            throw new ResourceNotFoundException("Registro de interações não encontrado");
        }
        RegistroDeInteracoes registroDeInteracoes = optionalRegistroDeInteracoes.get();

        RegistroDeInteracoesResponse out = RegistroDeInteracoesMapper.registroToRegistroDeInteracoesResponse(registroDeInteracoes);
        return out;
    }

    // Validações
    private void validacaoManutencaoRegistroDeInteracoes(RegistroDeInteracoesRequest registroDeInteracoesRequest)
            throws ResourceNotFoundException {
        if (registroDeInteracoesRequest.getDataDaInteracao() == null) {
            throw new ResourceNotFoundException("A data da interação é obrigatória.");
        }
        if(StringUtil.validarString(registroDeInteracoesRequest.getDescricao())){
            throw new ResourceNotFoundException("A descrição é obrigatória.");
        }

    }

//    // Validação Status da Interferência
//    public boolean verificarStatusDaInterferencia(String status) {
//        StatusDaInterferencia statusDaInterferencia = StatusDaInterferencia.valueOf(status);
//        try {
//            statusDaInterferencia.valueOf(status);
//            return true;
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }
}
