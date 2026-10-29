package edu.altair.apiCRM.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "departamento")

public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private long idDepartamento;

    //Seguir mapeando columnas

}
