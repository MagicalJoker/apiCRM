package edu.altair.apiCRM.services.impl;

import edu.altair.apiCRM.dtos.CursoRequestDTO;
import edu.altair.apiCRM.dtos.CursoResponseDTO;
import edu.altair.apiCRM.entities.Curso;
import edu.altair.apiCRM.exceptions.DuplicateResourceException;
import edu.altair.apiCRM.exceptions.ResourceNotFoundException;
import edu.altair.apiCRM.repositories.CursoRepository;
import edu.altair.apiCRM.services.CursoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    // Inyección de dependencias por constructor
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponseDTO> obtenerTodos() {
        return cursoRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponseDTO obtenerPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con el ID: " + id));
        return mapToResponseDto(curso);
    }

    @Override
    @Transactional
    public CursoResponseDTO crear(CursoRequestDTO dto) {
        // Validación de regla de negocio: diminutivo único
        if (cursoRepository.existsByDiminutivo(dto.diminutivo())) {
            throw new DuplicateResourceException("Ya existe un curso con el diminutivo: " + dto.diminutivo());
        }

        Curso nuevoCurso = new Curso(
                null,
                dto.diminutivo(),
                dto.descripcion(),
                dto.titulacion()
        );

        Curso guardado = cursoRepository.save(nuevoCurso);
        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional
    public CursoResponseDTO actualizar(Long id, CursoRequestDTO dto) {
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con el ID: " + id));

        // Si se modifica el diminutivo, comprobar que el nuevo diminutivo no pertenezca a otro curso
        if (!cursoExistente.getDiminutivo().equals(dto.diminutivo()) && cursoRepository.existsByDiminutivo(dto.diminutivo())) {
            throw new DuplicateResourceException("El diminutivo " + dto.diminutivo() + " ya está en uso por otro curso.");
        }
        cursoExistente.setDiminutivo(dto.diminutivo());
        cursoExistente.setDescripcion(dto.descripcion());
        cursoExistente.setTitulacion(dto.titulacion());

        Curso actualizado = cursoRepository.save(cursoExistente);
        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Imposible eliminar. Curso no encontrado con el ID: " + id);
        }
        cursoRepository.deleteById(id);
    }

    // Método auxiliar de mapeo de Entidad a DTO
    private CursoResponseDTO mapToResponseDto(Curso curso) {
        return new CursoResponseDTO(
                curso.getIdCurso(),
                curso.getDiminutivo(),
                curso.getDescripcion(),
                curso.getTitulacion()
        );
    }
}
