package edu.altair.apiCRM.services.impl;

import edu.altair.apiCRM.dtos.DepartamentoRequestDTO;
import edu.altair.apiCRM.dtos.DepartamentoResponseDTO;
import edu.altair.apiCRM.entities.Departamento;
import edu.altair.apiCRM.exceptions.DuplicateResourceException;
import edu.altair.apiCRM.exceptions.ResourceNotFoundException;
import edu.altair.apiCRM.repositories.DepartamentoRepository;
import edu.altair.apiCRM.services.DepartamentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    // Inyección de dependencias por constructor
    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartamentoResponseDTO> obtenerTodos() {
        return departamentoRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DepartamentoResponseDTO obtenerPorId(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con el ID: " + id));
        return mapToResponseDto(departamento);
    }

    @Override
    @Transactional
    public DepartamentoResponseDTO crear(DepartamentoRequestDTO dto) {
        // Validación de regla de negocio: código único
        if (departamentoRepository.existsByCodigo(dto.codigo())) {
            throw new DuplicateResourceException("Ya existe un departamento con el código: " + dto.codigo());
        }

        Departamento nuevoDepartamento = new Departamento(
                null,
                dto.codigo(),
                dto.nombre(),
                dto.descripcion(),
                dto.telefono()
        );

        Departamento guardado = departamentoRepository.save(nuevoDepartamento);
        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional
    public DepartamentoResponseDTO actualizar(Long id, DepartamentoRequestDTO dto) {
        Departamento departamentoExistente = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con el ID: " + id));

        // Si se modifica el código, comprobar que el nuevo código no pertenezca a otro departamento
        if (!departamentoExistente.getCodigo().equals(dto.codigo()) && departamentoRepository.existsByCodigo(dto.codigo())) {
            throw new DuplicateResourceException("El código " + dto.codigo() + " ya está en uso por otro departamento.");
        }
        departamentoExistente.setCodigo(dto.codigo());
        departamentoExistente.setNombre(dto.nombre());
        departamentoExistente.setDescripcion(dto.descripcion());
        departamentoExistente.setTelefono(dto.telefono());

        Departamento actualizado = departamentoRepository.save(departamentoExistente);
        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Imposible eliminar. Departamento no encontrado con el ID: " + id);
        }
        departamentoRepository.deleteById(id);
    }

    // Método auxiliar de mapeo de Entidad a DTO
    private DepartamentoResponseDTO mapToResponseDto(Departamento departamento) {
        return new DepartamentoResponseDTO(
                departamento.getIdDepartamento(),
                departamento.getCodigo(),
                departamento.getNombre(),
                departamento.getDescripcion(),
                departamento.getTelefono()
        );
    }
}
