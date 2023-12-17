package com.crm.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice // indica que a classe é um controlador de exceções global que trata exceções em todo o aplicativo.
@ResponseBody //  indica que os métodos deste controlador produzirão automaticamente resposta JSON.
public class ExceptionHandler {

    // O seguinte método lida com exceções da classe ResourceNotFoundException.
    // Isso significa que quando uma exceção ResourceNotFoundException for lançada no aplicativo,
    // este método será invocado para tratar a exceção.
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> notFound(ResourceNotFoundException exception, WebRequest request) {

        // Crie uma instância de ErrorMessage para encapsular os detalhes do erro.
        ErrorMessage errorMessage = new ErrorMessage();

        // Defina a mensagem de erro no objeto ErrorMessage com base na mensagem da exceção.
        errorMessage.setMessage(exception.getMessage());

        // Defina a marca de tempo (timestamp) atual.
        errorMessage.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime());

        // Defina o código de status HTTP na resposta como 404 (Not Found).
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());

        // Crie uma resposta ResponseEntity com o objeto ErrorMessage e o código de status 404.
        // Isso é o que será retornado ao cliente em resposta à exceção ResourceNotFoundException.
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
