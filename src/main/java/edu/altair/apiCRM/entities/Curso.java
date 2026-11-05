package edu.altair.apiCRM.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    /**
     * curso:
     *  PK    id_curso
     *  NN, UK diminutivo
     *  .     descripcion
     *  FK    id_titulacion --> titulacion
     */

    @Column(name ="diminutivo", nullable = false, unique = true)
    private String diminutivo;

    @Column(name= "descripcion")
    private String descripcion;

    // Relación N:1 con Titulacion (Un curso pertenece a 0 ó 1 Titulación)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulacion", nullable = true)
    private Titulacion titulacion;


    //Tras haber mapeado las columnas, se crea un constructor vacío de forma OBLIGATORIA
    public Curso () {}

    //Y ahora se hace el constructor real
    public Curso(Long idCurso, String diminutivo, String descripcion, Titulacion titulacion) {
        this.idCurso = idCurso;
        this.diminutivo = diminutivo;
        this.descripcion = descripcion;
        this.titulacion = titulacion;
    }

    //Ahora van los getters y los setters

    //El de id
    public Long getIdCurso() {return idCurso;}
    public void setIdCurso (Long idCurso) {this.idCurso = idCurso;}

    //El de diminutivo
    public String getDiminutivo() {return diminutivo;}
    public void setDiminutivo (String diminutivo) {this.diminutivo = diminutivo;}

    //El de descripcion
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    //El de titulacion
    public Titulacion getTitulacion() {return titulacion;}
    public void setTitulacion(Titulacion titulacion) {this.titulacion = titulacion;}



}
