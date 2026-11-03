package edu.altair.apiCRM.exceptions;

// Se lanza cuando se intenta guardar un valor que ya existe en un campo UK
// (por ejemplo, un código de departamento repetido).
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}