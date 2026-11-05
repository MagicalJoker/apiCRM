package edu.altair.apiCRM.services.impl;

import edu.altair.apiCRM.dtos.TitulacionRequestDTO;
import edu.altair.apiCRM.dtos.TitulacionResponseDTO;
import edu.altair.apiCRM.entities.Titulacion;
import edu.altair.apiCRM.exceptions.DuplicateResourceException;
import edu.altair.apiCRM.exceptions.ResourceNotFoundException;
import edu.altair.apiCRM.repositories.TitulacionRepository;
import edu.altair.apiCRM.services.TitulacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TitulacionServiceImpl implements TitulacionService {

    private final TitulacionRepository titulacionRepository;

    // Inyección de dependencias por constructor
    public TitulacionServiceImpl(TitulacionRepository titulacionRepository) {
        this.titulacionRepository = titulacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TitulacionResponseDTO> obtenerTodos() {
        return titulacionRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TitulacionResponseDTO obtenerPorId(Long id) {
        Titulacion titulacion = titulacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Titulación no encontrada con el ID: " + id));
        return mapToResponseDto(titulacion);
    }

    @Override
    @Transactional
    public TitulacionResponseDTO crear(TitulacionRequestDTO dto) {
        // Validación de regla de negocio: código y diminutivo únicos
        if (titulacionRepository.existsByCodigo(dto.codigo())) {
            throw new DuplicateResourceException("Ya existe una titulación con el código: " + dto.codigo());
        }
        if (titulacionRepository.existsByDiminutivo(dto.diminutivo())) {
            throw new DuplicateResourceException("Ya existe una titulación con el diminutivo: " + dto.diminutivo());
        }

        Titulacion nuevaTitulacion = new Titulacion(
                null,
                dto.codigo(),
                dto.diminutivo(),
                dto.horasTotales()
        );

        Titulacion guardado = titulacionRepository.save(nuevaTitulacion);
        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional
    public TitulacionResponseDTO actualizar(Long id, TitulacionRequestDTO dto) {
        Titulacion titulacionExistente = titulacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Titulación no encontrada con el ID: " + id));

        // Si se modifica el código o el diminutivo, comprobar que no pertenezcan a otra titulación
        if (!titulacionExistente.getCodigo().equals(dto.codigo()) && titulacionRepository.existsByCodigo(dto.codigo())) {
            throw new DuplicateResourceException("El código " + dto.codigo() + " ya está en uso por otra titulación.");
        }
        if (!titulacionExistente.getDiminutivo().equals(dto.diminutivo()) && titulacionRepository.existsByDiminutivo(dto.diminutivo())) {
            throw new DuplicateResourceException("El diminutivo " + dto.diminutivo() + " ya está en uso por otra titulación.");
        }
        titulacionExistente.setCodigo(dto.codigo());
        titulacionExistente.setDiminutivo(dto.diminutivo());
        titulacionExistente.setHorasTotales(dto.horasTotales());

        Titulacion actualizado = titulacionRepository.save(titulacionExistente);
        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!titulacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Imposible eliminar. Titulación no encontrada con el ID: " + id);
        }
        titulacionRepository.deleteById(id);
    }

    // Método auxiliar de mapeo de Entidad a DTO
    private TitulacionResponseDTO mapToResponseDto(Titulacion titulacion) {
        return new TitulacionResponseDTO(
                titulacion.getIdTitulacion(),
                titulacion.getCodigo(),
                titulacion.getDiminutivo(),
                titulacion.getHorasTotales()
        );
    }
}
