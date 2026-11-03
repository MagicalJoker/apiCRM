package edu.altair.apiCRM.services;

import edu.altair.apiCRM.dtos.DepartamentoRequestDTO;
import edu.altair.apiCRM.dtos.DepartamentoResponseDTO;
import edu.altair.apiCRM.entities.Departamento;
import edu.altair.apiCRM.exceptions.DuplicateResourceException;
import edu.altair.apiCRM.exceptions.ResourceNotFoundException;
import edu.altair.apiCRM.repositories.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    //Busca el departamento por id; si no existe, lanza ResourceNotFoundException
    private Departamento obtenerEntidad (Long id) {
        return departamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Departamento no encontrado con id " + id));

    }
    // Convierte lo que envía el cliente en una entidad. El id va a null: lo asigna la BD
    private Departamento toEntity(DepartamentoRequestDTO request) {
        return new Departamento(null, request.codigo(), request.nombre(),
                request.telefono(), request.descripcion());
    }

    // Convierte una entidad de la BD en el DTO que devuelve la API
    //toResponse: de entidad a Response
    private DepartamentoResponseDTO toResponse(Departamento d) {
        return new DepartamentoResponseDTO(d.getIdDepartamento(), d.getCodigo(),
                d.getNombre(), d.getTelefono(), d.getDescripcion());
    }

    //Ahora van los 5 métodos del CRUD

    //Listar
    public List<DepartamentoResponseDTO> listar() {
        return departamentoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //buscarPorId
        //Devuelve un departamento por id; si no existe, 404
    public DepartamentoResponseDTO buscarPorId (Long id) {
        return toResponse(obtenerEntidad(id));
    }

    //crear
        //Crea un departamento; si el código ya existe, lanza 409 (Conflict)
    public DepartamentoResponseDTO crear(DepartamentoRequestDTO request) {
        if (departamentoRepository.existsByCodigo(request.codigo())) {
            throw new DuplicateResourceException(
                    "Ya existe un departamento con el código " + request.codigo());
        }
        Departamento guardado  = departamentoRepository.save(toEntity(request));
        return toResponse(guardado);
    }

    //actualizar
        //Modifica un departamento existente; 404 si no existe, 409 si el código ya está en uso
    public DepartamentoResponseDTO actualizar (Long id, DepartamentoRequestDTO request) {
        Departamento departamento = obtenerEntidad(id);

        if (!departamento.getCodigo().equals(request.codigo())
            && departamentoRepository.existsByCodigo(request.codigo())) {
            throw new DuplicateResourceException(
                    "Ya existe un departamento con el codigo " + request.codigo());
        }
        departamento.setCodigo(request.codigo());
        departamento.setNombre(request.nombre());
        departamento.setDescripcion(request.descripcion());
        departamento.setTelefono(request.telefono());

        return toResponse(departamentoRepository.save(departamento));
    }

    //eliminar
        //Elimina un departamento; 404 si no existe
    public void eliminar (Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Departamento no encontrado con id " + id);
        }
        departamentoRepository.deleteById(id);
    }

}
