package edu.altair.apiCRM.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor", uniqueConstraints = {
        @UniqueConstraint(name = "dni", columnNames = "dni")
})

public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor", nullable = false)
    private long idProfesor;

    /**
     * profesor:
     * 	PK		id_profesor                                         HECHO
     * 	NN		nombre
     * 	NN		apellidos
     * 	UK		dni
     * 			email_centro
     * 			email_personal
     * 			telefono_personal
     * 	FK		departamento--> id_departamento
     */




}
