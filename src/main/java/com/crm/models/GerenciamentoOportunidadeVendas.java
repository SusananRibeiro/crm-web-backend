package com.crm.models;
import com.crm.models.enums.EstagioDaOportunidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "gerenciamento_oportunidade_vendas")
@AllArgsConstructor
@NoArgsConstructor
public class GerenciamentoOportunidadeVendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstagioDaOportunidade estagioDaOportunidade; // PROSPECT, QUALIFICAÇÃO, PROPOSTA, FECHAMENTO

    @Column
    private double valorEstimadoDaVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente clienteId;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso

}
