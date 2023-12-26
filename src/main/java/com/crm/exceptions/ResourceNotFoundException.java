package com.crm.exceptions;
import java.util.ArrayList;
import java.util.List;

public class ResourceNotFoundException extends RuntimeException {
    private List<String> messages = new ArrayList<>();
    public ResourceNotFoundException(String message) {
        super(message);
    }
// Retornar uma lista de erro
    public ResourceNotFoundException(List<String> msg){
        this.messages = msg;
    }

    public List<String> getMessages() {
        if(messages.isEmpty()){
            messages.add(super.getMessage());
        }

        return messages;
    }

    public String getMessage(){
        if(messages.isEmpty()){
            return super.getMessage();
        }

        return messages.toString();
    }
}
