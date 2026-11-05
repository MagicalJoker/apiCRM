package edu.altair.apiCRM.repositories;

import edu.altair.apiCRM.entities.Titulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TitulacionRepository extends JpaRepository<Titulacion, Long> {
    @Transactional(readOnly = true)
    // Devuelve true si ya existe una titulación con ese código (para rechazar duplicados)
    boolean existsByCodigo(String codigo);

    //SIEMPRE TRANSACTIONAL!!!!!!!!

    @Transactional(readOnly = true)
    // Busca una titulación por su código; Optional porque puede no existir
    Optional<Titulacion> findByCodigo(String codigo);

    @Transactional(readOnly = true)
    // Devuelve true si ya existe una titulación con ese diminutivo (para rechazar duplicados)
    boolean existsByDiminutivo(String diminutivo);

    @Transactional(readOnly = true)
    // Busca una titulación por su diminutivo; Optional porque puede no existir
    Optional<Titulacion> findByDiminutivo(String diminutivo);
}
