package edu.altair.apiCRM.services;

import edu.altair.apiCRM.dtos.TitulacionRequestDTO;
import edu.altair.apiCRM.dtos.TitulacionResponseDTO;

import java.util.List;

public interface TitulacionService {
    List<TitulacionResponseDTO> obtenerTodos();
    TitulacionResponseDTO obtenerPorId(Long id);
    TitulacionResponseDTO crear(TitulacionRequestDTO requestDto);
    TitulacionResponseDTO actualizar(Long id, TitulacionRequestDTO requestDto);
    void eliminar(Long id);
}
