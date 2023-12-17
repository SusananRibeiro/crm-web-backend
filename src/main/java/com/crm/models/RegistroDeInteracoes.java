package com.crm.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "registro_interacoes")
@AllArgsConstructor // vai configurar um construtor com todos os parâmetros
@NoArgsConstructor // essa anotação é responsável por gerar um construtor sem parâmetros
public class RegistroDeInteracoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descrição;

    @Column
    private LocalDateTime dataDaInteracao;

//    @ManyToOne
//    @JoinColumn(name = "cliente_id", nullable = false)
//    private Cliente clienteId;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso
}
