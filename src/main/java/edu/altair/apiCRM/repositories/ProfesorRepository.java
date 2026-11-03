package edu.altair.apiCRM.repositories;

import edu.altair.apiCRM.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    boolean existsByDni(String dni);
    Optional<Profesor> findByDni(String dni);
}
