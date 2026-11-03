package edu.altair.apiCRM.dtos;

// TIPO: Record
// FUNCIÓN: Datos que ENVÍA el cliente al crear o modificar un departamento.
// No lleva id porque lo genera la base de datos (en el PUT viaja en la URL).
public record DepartamentoRequestDTO(
        String codigo,
        String nombre,
        String descripcion,
        String telefono
) {}