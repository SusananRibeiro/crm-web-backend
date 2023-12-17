package com.crm.repositories;
import com.crm.models.GerenciamentoOportunidadeVendas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenciamentoOportunidadeVendasRepository extends CrudRepository<GerenciamentoOportunidadeVendas, Integer> {
}
