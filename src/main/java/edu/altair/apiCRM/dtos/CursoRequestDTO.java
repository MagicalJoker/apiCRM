package edu.altair.apiCRM.dtos;

import edu.altair.apiCRM.entities.Titulacion;

// TIPO: Record
// FUNCIÓN: Datos que ENVÍA el cliente al crear o modificar un curso.
// No lleva id porque lo genera la base de datos (en el PUT viaja en la URL).
public record CursoRequestDTO(
        String diminutivo,
        String descripcion,
        Titulacion titulacion
) {}
