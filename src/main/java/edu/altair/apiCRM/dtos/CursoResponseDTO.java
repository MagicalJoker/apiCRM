package edu.altair.apiCRM.dtos;

import edu.altair.apiCRM.entities.Titulacion;

// TIPO: Record
// FUNCIÓN: Datos que DEVUELVE la API sobre un curso.
// Incluye el id, que ya existe una vez guardado en la base de datos.
public record CursoResponseDTO(
        Long idCurso,
        String diminutivo,
        String descripcion,
        Titulacion titulacion
) {}
