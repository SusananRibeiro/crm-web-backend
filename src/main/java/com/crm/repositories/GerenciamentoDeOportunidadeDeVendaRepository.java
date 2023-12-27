package com.crm.repositories;
import com.crm.models.GerenciamentoDeOportunidadeDeVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenciamentoDeOportunidadeDeVendaRepository extends JpaRepository<GerenciamentoDeOportunidadeDeVenda, Long> {
}
