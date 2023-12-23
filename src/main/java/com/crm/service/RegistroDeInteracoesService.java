package com.crm.service;
import com.crm.dtos.RegistroDeInteracoesDTO;
import com.crm.exceptions.ResourceNotFoundException;
import com.crm.models.RegistroDeInteracoes;
import com.crm.repositories.RegistroDeInteracoesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroDeInteracoesService {
    @Autowired
    private RegistroDeInteracoesRepository registroDeInteracoesRepository;

    // GET de todos os Registro de Interações
    public Iterable<RegistroDeInteracoes> getRegistroDeInteracoes() {
        return this.registroDeInteracoesRepository.findAll();
    }

    // GET (com ID específico)
    public RegistroDeInteracoes getRegistroDeInteracoesById(int idRegistroDeInteracoes) {
        Optional<RegistroDeInteracoes> registroDeInteracoes = this.registroDeInteracoesRepository.findById(idRegistroDeInteracoes);

        if (registroDeInteracoes.isEmpty()) {
            throw new ResourceNotFoundException("Registro de Interações não existe");
            // Lança uma exceção caso o cliente não seja encontrado
        }
        return registroDeInteracoes.get();
    }

    // POST
    public RegistroDeInteracoes addRegistroDeInteracoes(RegistroDeInteracoesDTO registroDeInteracoesDTO) {
        this.validateRegistroDeInteracoes(registroDeInteracoesDTO);
        ModelMapper mapper = new ModelMapper();
        RegistroDeInteracoes registroDeInteracoesToPersist = mapper.map(registroDeInteracoesDTO, RegistroDeInteracoes.class);
        // Mapeia o RegistroDeInteracoesDTO para a entidade RegistroDeInteracoes e salva no repositório
        this.registroDeInteracoesRepository.save(registroDeInteracoesToPersist);
        return registroDeInteracoesToPersist;
    }

    // PUT
    public RegistroDeInteracoes updateRegistroDeInteracoes(int idRegistroDeInteracoes, RegistroDeInteracoesDTO registroDeInteracoesDTO) {
        this.validateRegistroDeInteracoes(registroDeInteracoesDTO);
        Optional<RegistroDeInteracoes> optionalRegistroDeInteracoes = this.registroDeInteracoesRepository.findById(idRegistroDeInteracoes);

        if (optionalRegistroDeInteracoes.isEmpty()) {
            throw new ResourceNotFoundException("Registro de Interações não existe");
        }
        RegistroDeInteracoes registroDeInteracoes = optionalRegistroDeInteracoes.get();
        ModelMapper mapper = new ModelMapper();
        mapper.map(registroDeInteracoesDTO, registroDeInteracoes);
        this.registroDeInteracoesRepository.save(registroDeInteracoes);
        return registroDeInteracoes;
    }

    // DELETE
    public void deleteRegistroDeInteracoesById(int idRegistroDeInteracoes) {
        if (!this.registroDeInteracoesRepository.existsById(idRegistroDeInteracoes)) {
            throw new ResourceNotFoundException("Registro de Interações não existe");
        }
        this.registroDeInteracoesRepository.deleteById(idRegistroDeInteracoes);
    }

    // Método para validação
    private void validateRegistroDeInteracoes(RegistroDeInteracoesDTO registroDeInteracoesDTO) {
        // Lida com os erros de validação, por exemplo, lançando uma exceção ou tratando os erros de outra forma
        if(registroDeInteracoesDTO.getDescrição().isEmpty()) {
            throw new ResourceNotFoundException("A descrição é obrigatório.");
        }
        if (registroDeInteracoesDTO.getDataDaInteracao() != null) {
            throw new ResourceNotFoundException("Data da Interação é obrigatório.");
        }
    }

}
