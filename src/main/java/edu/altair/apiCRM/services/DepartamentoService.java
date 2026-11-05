package edu.altair.apiCRM.services;

import edu.altair.apiCRM.dtos.DepartamentoRequestDTO;
import edu.altair.apiCRM.dtos.DepartamentoResponseDTO;

import java.util.List;

public interface DepartamentoService {
    List<DepartamentoResponseDTO> obtenerTodos();
    DepartamentoResponseDTO obtenerPorId(Long id);
    DepartamentoResponseDTO crear(DepartamentoRequestDTO requestDto);
    DepartamentoResponseDTO actualizar(Long id, DepartamentoRequestDTO requestDto);
    void eliminar(Long id);
}
