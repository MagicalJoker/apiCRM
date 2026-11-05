package edu.altair.apiCRM.dtos;

// TIPO: Record
// FUNCIÓN: Datos que ENVÍA el cliente al crear o modificar una titulación.
// No lleva id porque lo genera la base de datos (en el PUT viaja en la URL).
public record TitulacionRequestDTO(
        String codigo,
        String diminutivo,
        Integer horasTotales
) {}
