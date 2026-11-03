package edu.altair.apiCRM.dtos;

// TIPO: Record
// FUNCIÓN: Datos que DEVUELVE la API sobre un departamento.
// Incluye el id, que ya existe una vez guardado en la base de datos.
public record DepartamentoResponseDTO(
        Long idDepartamento,
        String codigo,
        String nombre,
        String descripcion,
        String telefono
) {}