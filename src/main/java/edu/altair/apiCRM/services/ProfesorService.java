package edu.altair.apiCRM.services;

import edu.altair.apiCRM.dtos.ProfesorRequestDTO;
import edu.altair.apiCRM.dtos.ProfesorResponseDTO;

import java.util.List;

public interface ProfesorService {
    List<ProfesorResponseDTO> obtenerTodos();
    ProfesorResponseDTO obtenerPorId(Long id);
    ProfesorResponseDTO crear(ProfesorRequestDTO requestDto);
    ProfesorResponseDTO actualizar(Long id, ProfesorRequestDTO requestDto);
    void eliminar(Long id);
}
