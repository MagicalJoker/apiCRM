package edu.altair.apiCRM.dtos;

import edu.altair.apiCRM.entities.Departamento;

public record ProfesorResponseDTO(
        Long idProfesor,
        String nombre,
        String apellidos,
        String dni,
        String emailCentro,
        String emailPersonal,
        Integer telefonoPersonal,
        Departamento departamento
) {}
