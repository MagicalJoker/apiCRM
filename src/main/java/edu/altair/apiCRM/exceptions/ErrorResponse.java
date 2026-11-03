package edu.altair.apiCRM.exceptions;

import java.time.LocalDateTime;

// Estructura del JSON que verá el cliente cuando haya un error
public record ErrorResponse(
        int status,              // 404, 409...
        String error,            // "Not Found", "Conflict"...
        String message,          // el mensaje de la excepción
        LocalDateTime timestamp  // cuándo ocurrió
) {}