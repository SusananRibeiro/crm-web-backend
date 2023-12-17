package com.crm.exceptions;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorMessage {

    private int statusCode; // Armazena o código de status HTTP que será retornado na resposta.
    private LocalDateTime timestamp; // Armazena o timestamp que indica o momento em que o erro ocorreu.
    private String message; // Armazena a mensagem de erro a ser exibida na tela.

    // A anotação @Data é do projeto Lombok e gera automaticamente métodos Getters, Setters, hashCode, equals e toString.

}
