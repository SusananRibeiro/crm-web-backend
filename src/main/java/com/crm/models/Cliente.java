package com.crm.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "clientes")
@AllArgsConstructor // vai configurar um construtor com todos os parâmetros
@NoArgsConstructor // essa anotação é responsável por gerar um construtor sem parâmetros
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomeCompleto;

    @Column(length = 11)
    private String cpf;

    @Column(length = 3)
    private int idade;

    @Column
    private String endereco;

    @Column(length = 11)
    private String telefone;

    @Column
    private String email;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso

}