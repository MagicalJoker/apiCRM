package edu.altair.apiCRM.dtos;

// TIPO: Record
// FUNCIÓN: Datos que DEVUELVE la API sobre una titulación.
// Incluye el id, que ya existe una vez guardado en la base de datos.
public record TitulacionResponseDTO(
        Long idTitulacion,
        String codigo,
        String diminutivo,
        Integer horasTotales
) {}
