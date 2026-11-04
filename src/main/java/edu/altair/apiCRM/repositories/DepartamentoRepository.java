package edu.altair.apiCRM.repositories;

import edu.altair.apiCRM.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    @Transactional(readOnly = true)
    // Devuelve true si ya existe un departamento con ese código (para rechazar duplicados)
    boolean existsByCodigo(String codigo);

    //SIEMPRE TRANSACTIONAL!!!!!!!!

    @Transactional(readOnly = true)
    // Busca un departamento por su código; Optional porque puede no existir
    Optional<Departamento> findByCodigo(String codigo);
}