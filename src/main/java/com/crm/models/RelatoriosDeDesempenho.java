package com.crm.models;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Data
@Entity(name = "relatorios_de_desempenho")
@SQLDelete(sql = "UPDATE relatorios_de_desempenho SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class RelatoriosDeDesempenho {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "registro_id")
    private RegistroDeInteracoes registroId; // número total de interações registradas
    @ManyToOne
    @JoinColumn(name = "gerenciamento_id")
    private GerenciamentoDeOportunidadeDeVenda gerenciamentoId; // oportunidades de vendas em cada estágio e valores totais das oportunidades
}
