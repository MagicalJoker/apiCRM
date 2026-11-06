package edu.altair.apiCRM.repositories;

import edu.altair.apiCRM.entities.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    /**
     * modulo:
     * 	PK		id_modulo
     * 	UK, NN	codigo
     * 	UK, NN	diminutivo
     * 	NN		nombre
     * 			horas_semanales
     * 			creditos
     */

    @Transactional(readOnly = true)
    // Devuelve true si ya existe un modulo con ese código (para rechazar duplicados)
    boolean existsByCodigo(String codigo);

    @Transactional(readOnly = true)
    boolean existByDiminutivo(String diminutivo);


    //SIEMPRE TRANSACTIONAL!!!!!!!!

    @Transactional(readOnly = true)
        // Busca un modulo por su código; Optional porque puede no existir
    Optional<Modulo> findByCodigo(String codigo);

    @Transactional(readOnly = true)
    //Busca un modulo por su diminutivo
    Optional<Modulo> findByDiminutivo(String diminutivo);
}
