package br.edu.unipe.api.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponseDTO {

    private String error;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();;
    private String details;

    public ErrorResponseDTO(String error, String message, String details){
        this.error = error;
        this.message = message;
        this.details = details;
    }
}
