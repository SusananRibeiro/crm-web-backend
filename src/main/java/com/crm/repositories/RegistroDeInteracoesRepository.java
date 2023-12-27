package com.crm.repositories;
import com.crm.models.RegistroDeInteracoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDeInteracoesRepository extends JpaRepository<RegistroDeInteracoes, Long> {
}
