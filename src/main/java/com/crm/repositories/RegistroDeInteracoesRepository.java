package com.crm.repositories;
import com.crm.models.RegistroDeInteracoes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDeInteracoesRepository extends CrudRepository<RegistroDeInteracoes, Integer> {
}
