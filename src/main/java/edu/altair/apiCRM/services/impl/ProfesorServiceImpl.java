package edu.altair.apiCRM.services.impl;

import edu.altair.apiCRM.dtos.ProfesorRequestDTO;
import edu.altair.apiCRM.dtos.ProfesorResponseDTO;
import edu.altair.apiCRM.entities.Profesor;
import edu.altair.apiCRM.exceptions.DuplicateResourceException;
import edu.altair.apiCRM.exceptions.ResourceNotFoundException;
import edu.altair.apiCRM.repositories.ProfesorRepository;
import edu.altair.apiCRM.services.ProfesorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    // Inyección de dependencias por constructor
    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfesorResponseDTO> obtenerTodos() {
        return profesorRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProfesorResponseDTO obtenerPorId(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con el ID: " + id));
        return mapToResponseDto(profesor);
    }

    @Override
    @Transactional
    public ProfesorResponseDTO crear(ProfesorRequestDTO dto) {
        // Validación de regla de negocio: DNI único
        if (dto.dni() != null && profesorRepository.existsByDni(dto.dni())) {
            throw new DuplicateResourceException("Ya existe un profesor registrado con el DNI: " + dto.dni());
        }

        Profesor nuevoProfesor = new Profesor(
                null,
                dto.nombre(),
                dto.apellidos(),
                dto.dni(),
                dto.emailCentro(),
                dto.emailPersonal(),
                dto.telefonoPersonal(),
                dto.departamento()
        );

        Profesor guardado = profesorRepository.save(nuevoProfesor);
        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional
    public ProfesorResponseDTO actualizar(Long id, ProfesorRequestDTO dto) {
        Profesor profesorExistente = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con el ID: " + id));

        // Si se modifica el DNI, comprobar que el nuevo DNI no pertenezca a otro profesor
        if (dto.dni() != null
                && (profesorExistente.getDni() == null || !profesorExistente.getDni().equalsIgnoreCase(dto.dni()))
                && profesorRepository.existsByDni(dto.dni())) {
            throw new DuplicateResourceException("El DNI " + dto.dni() + " ya está en uso por otro profesor.");
        }
        profesorExistente.setNombre(dto.nombre());
        profesorExistente.setApellidos(dto.apellidos());
        profesorExistente.setDni(dto.dni());
        profesorExistente.setEmailCentro(dto.emailCentro());
        profesorExistente.setEmailPersonal(dto.emailPersonal());
        profesorExistente.setTelefonoPersonal(dto.telefonoPersonal());
        profesorExistente.setDepartamento(dto.departamento());

        Profesor actualizado = profesorRepository.save(profesorExistente);
        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Imposible eliminar. Profesor no encontrado con el ID: " + id);
        }
        profesorRepository.deleteById(id);
    }

    // Método auxiliar de mapeo de Entidad a DTO
    private ProfesorResponseDTO mapToResponseDto(Profesor profesor) {
        return new ProfesorResponseDTO(
                profesor.getIdProfesor(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getDni(),
                profesor.getEmailCentro(),
                profesor.getEmailPersonal(),
                profesor.getTelefonoPersonal(),
                profesor.getDepartamento()
        );
    }
}
