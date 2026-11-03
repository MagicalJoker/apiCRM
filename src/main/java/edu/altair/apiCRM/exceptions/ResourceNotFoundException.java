package edu.altair.apiCRM.exceptions;

// Se lanza cuando se busca un registro (por id, por ejemplo) que no existe.
// Extiende RuntimeException: es "unchecked", no obliga a poner try/catch ni "throws".
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);   // el mensaje viaja hasta el manejador global
    }
}