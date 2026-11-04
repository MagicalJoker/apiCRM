package edu.altair.apiCRM.repositories;

import edu.altair.apiCRM.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    @Transactional(readOnly = true)
    boolean existsByDni(String dni);

    @Transactional(readOnly = true)
    Optional<Profesor> findByDni(String dni);
}

