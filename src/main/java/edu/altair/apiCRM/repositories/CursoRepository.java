package edu.altair.apiCRM.repositories;

import edu.altair.apiCRM.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Transactional(readOnly = true)
    // Devuelve true si ya existe un curso con ese diminutivo (para rechazar duplicados)
    boolean existsByDiminutivo(String diminutivo);

    //SIEMPRE TRANSACTIONAL!!!!!!!!

    @Transactional(readOnly = true)
    // Busca un curso por su diminutivo; Optional porque puede no existir
    Optional<Curso> findByDiminutivo(String diminutivo);
}
