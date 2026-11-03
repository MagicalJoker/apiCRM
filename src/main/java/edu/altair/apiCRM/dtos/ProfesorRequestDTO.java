package edu.altair.apiCRM.dtos;

import edu.altair.apiCRM.entities.Departamento;

public record ProfesorRequestDTO(

//Las request no llevan Id ya que lo genera la propia base de datos
        String nombre,
        String apellidos,
        String dni,
        String emailCentro,
        String emailPersonal,
        Integer telefonoPersonal,
        Departamento departamento
) { }
