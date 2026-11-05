package edu.altair.apiCRM.services;

import edu.altair.apiCRM.dtos.TitulacionRequestDTO;
import edu.altair.apiCRM.dtos.TitulacionResponseDTO;
import edu.altair.apiCRM.entities.Titulacion;
import edu.altair.apiCRM.exceptions.DuplicateResourceException;
import edu.altair.apiCRM.exceptions.ResourceNotFoundException;
import edu.altair.apiCRM.repositories.TitulacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitulacionService {
    private final TitulacionRepository titulacionRepository;

    public TitulacionService(TitulacionRepository titulacionRepository) {
        this.titulacionRepository = titulacionRepository;
    }

    //Busca la titulación por id; si no existe, lanza ResourceNotFoundException
    private Titulacion obtenerEntidad (Long id) {
        return titulacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Titulación no encontrada con id " + id));

    }
    // Convierte lo que envía el cliente en una entidad. El id va a null: lo asigna la BD
    private Titulacion toEntity(TitulacionRequestDTO request) {
        return new Titulacion(
                null,
                request.codigo(),
                request.diminutivo(),
                request.horasTotales()
        );
    }

    // Convierte una entidad de la BD en el DTO que devuelve la API
    //toResponse: de entidad a Response
    private TitulacionResponseDTO toResponse(Titulacion t) {
        return new TitulacionResponseDTO(
                t.getIdTitulacion(),
                t.getCodigo(),
                t.getDiminutivo(),
                t.getHorasTotales()
        );
    }

    //Ahora van los 5 métodos del CRUD

    //Listar
    public List<TitulacionResponseDTO> listar() {
        return titulacionRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //buscarPorId
        //Devuelve una titulación por id; si no existe, 404
    public TitulacionResponseDTO buscarPorId (Long id) {
        return toResponse(obtenerEntidad(id));
    }

    //crear
        //Crea una titulación; si el código o el diminutivo ya existen, lanza 409 (Conflict)
    public TitulacionResponseDTO crear(TitulacionRequestDTO request) {
        if (titulacionRepository.existsByCodigo(request.codigo())) {
            throw new DuplicateResourceException(
                    "Ya existe una titulación con el código " + request.codigo());
        }
        if (titulacionRepository.existsByDiminutivo(request.diminutivo())) {
            throw new DuplicateResourceException(
                    "Ya existe una titulación con el diminutivo " + request.diminutivo());
        }
        Titulacion guardado  = titulacionRepository.save(toEntity(request));
        return toResponse(guardado);
    }

    //actualizar
        //Modifica una titulación existente; 404 si no existe, 409 si el código o el diminutivo ya están en uso
    public TitulacionResponseDTO actualizar (Long id, TitulacionRequestDTO request) {
        Titulacion titulacion = obtenerEntidad(id);

        if (!titulacion.getCodigo().equals(request.codigo())
            && titulacionRepository.existsByCodigo(request.codigo())) {
            throw new DuplicateResourceException(
                    "Ya existe una titulación con el codigo " + request.codigo());
        }
        if (!titulacion.getDiminutivo().equals(request.diminutivo())
            && titulacionRepository.existsByDiminutivo(request.diminutivo())) {
            throw new DuplicateResourceException(
                    "Ya existe una titulación con el diminutivo " + request.diminutivo());
        }
        titulacion.setCodigo(request.codigo());
        titulacion.setDiminutivo(request.diminutivo());
        titulacion.setHorasTotales(request.horasTotales());

        return toResponse(titulacionRepository.save(titulacion));
    }

    //eliminar
        //Elimina una titulación; 404 si no existe
    public void eliminar (Long id) {
        if (!titulacionRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Titulación no encontrada con id " + id);
        }
        titulacionRepository.deleteById(id);
    }

}
