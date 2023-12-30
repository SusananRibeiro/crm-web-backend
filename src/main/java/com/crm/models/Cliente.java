package com.crm.models;
import com.crm.models.enums.EstadosDoBrasil;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "clientes")
@SQLDelete(sql = "UPDATE clientes SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String rua;

    @Column
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private EstadosDoBrasil uf;

    @Column(nullable = false, length = 8)
    private int cep;

    @Column(nullable = false)
    private String pais;

    @Column(length = 11)
    private String telefone;

    @Column
    private String email;

    @Column
    private String complemento;

    @Column
    private LocalDateTime deletedAt;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso

}
