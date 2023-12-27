package com.crm.models;
import com.crm.models.enums.EstagioDaOportunidade;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Data
@Entity(name = "gerenciamento_oportunidade_vendas")
@SQLDelete(sql = "UPDATE gerenciamento_oportunidade_vendas SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class GerenciamentoDeOportunidadeDeVenda {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstagioDaOportunidade estagioDaOportunidade;
    @Column
    private double valorEstimadoDaVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clienteId;

    @Column
    private LocalDateTime deletedAt;
}
