package edu.altair.apiCRM.services;

import edu.altair.apiCRM.dtos.CursoRequestDTO;
import edu.altair.apiCRM.dtos.CursoResponseDTO;

import java.util.List;

public interface CursoService {
    List<CursoResponseDTO> obtenerTodos();
    CursoResponseDTO obtenerPorId(Long id);
    CursoResponseDTO crear(CursoRequestDTO requestDto);
    CursoResponseDTO actualizar(Long id, CursoRequestDTO requestDto);
    void eliminar(Long id);
}
