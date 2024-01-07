package com.crm.models;
import com.crm.models.enums.StatusDaInterferencia;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Data
@Entity(name = "registro_interacoes")
@SQLDelete(sql = "UPDATE registro_interacoes SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class RegistroDeInteracoes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime dataDaInteracao;
    @Column
    private String canalDeComunicacao; // ex: telefone, e-mail, chat online, pessoalmente ou por outro meio
    @Column
    private String responsavelPelaInteracao;
    @Column
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusDaInterferencia statusDaInterferencia;
    @Column
    private LocalDateTime deletedAt;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso

}
