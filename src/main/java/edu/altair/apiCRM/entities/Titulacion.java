package edu.altair.apiCRM.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "titulacion")

public class Titulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titulacion", nullable = false)
    private Long idTitulacion;

    /**
     * titulacion:
     *  PK    id_titulacion
     *  NN, UK codigo
     *  NN, UK diminutivo
     *  .     horas_totales
     */

    @Column(name ="codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name= "diminutivo", nullable = false, unique = true)
    private String diminutivo;

    @Column(name ="horas_totales")
    private Integer horasTotales;


    //Tras haber mapeado las columnas, se crea un constructor vacío de forma OBLIGATORIA
    public Titulacion () {}

    //Y ahora se hace el constructor real
    public Titulacion(Long idTitulacion, String codigo, String diminutivo, Integer horasTotales) {
        this.idTitulacion = idTitulacion;
        this.codigo = codigo;
        this.diminutivo = diminutivo;
        this.horasTotales = horasTotales;
    }

    //Ahora van los getters y los setters

    //El de id
    public Long getIdTitulacion() {return idTitulacion;}
    public void setIdTitulacion (Long idTitulacion) {this.idTitulacion = idTitulacion;}

    //El de codigo
    public String getCodigo() {return codigo;}
    public void setCodigo (String codigo) {this.codigo = codigo;}

    //El de diminutivo
    public String getDiminutivo() {return diminutivo;}
    public void setDiminutivo (String diminutivo) {this.diminutivo = diminutivo;}

    //El de horasTotales
    public Integer getHorasTotales() {return horasTotales;}
    public void setHorasTotales(Integer horasTotales) {this.horasTotales = horasTotales;}



}
