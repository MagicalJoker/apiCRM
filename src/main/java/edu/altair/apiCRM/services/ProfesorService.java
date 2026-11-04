package edu.altair.apiCRM.services;

import edu.altair.apiCRM.dtos.ProfesorRequestDTO;
import edu.altair.apiCRM.entities.Profesor;
import edu.altair.apiCRM.exceptions.ResourceNotFoundException;
import edu.altair.apiCRM.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    //Busca el profesor por id; si no existe, lanza ResourceNotFoundException

    private Profesor obtenerProfesor (Long id) {
        return profesorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Profesor no encontrado con id " + id));
    }

    //Convierte lo que envía el cliente en una entidad. El id va a null; lo asigna la BD
    private Profesor toEntity (ProfesorRequestDTO request) {
        return new Profesor (null, request.dni(), request.nombre(), request.apellidos(),
                request.emailCentro(), request.emailPersonal(), request.telefonoPersonal(), request.departamento());

    }


}
