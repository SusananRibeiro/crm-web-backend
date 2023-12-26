package com.crm.repositories;
import com.crm.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRelatorioRepository extends JpaRepository<Cliente, Long> {
    public boolean existsByCpf(String cpf);

}
