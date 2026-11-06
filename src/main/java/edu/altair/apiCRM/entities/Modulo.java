package edu.altair.apiCRM.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "modulo")

public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo", nullable = false)
    private Long idModulo;

    /**
     * modulo:
     * 	PK		id_modulo
     * 	UK, NN	codigo
     * 	UK, NN	diminutivo
     * 	NN		nombre
     * 			horas_semanales
     * 			creditos
     */

    @Column(name ="codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name ="diminutivo", nullable = false, unique = true)
    private String diminutivo;

    @Column(name= "nombre", nullable = false)
    private String nombre;

    @Column(name ="horas_semanales")
    private Integer horasSemanales;

    @Column (name="creditos")
    private Integer creditos;


    //Tras haber mapeado las columnas, se crea un constructor vacío de forma OBLIGATORIA
    public Modulo () {}

    //Y ahora se hace el constructor real
    public Modulo(Long idModulo, String codigo, String diminutivo, String nombre, Integer horasSemanales, Integer creditos) {
        this.idModulo = idModulo;
        this.codigo = codigo;
        this.diminutivo = diminutivo;
        this.nombre = nombre;
        this.horasSemanales = horasSemanales;
        this.creditos = creditos;
    }

    //Ahora van los getters y los setters

    //El de id
    public Long getIdModulo() {return idModulo;}
    public void setIdModulo (Long idModulo) {this.idModulo = idModulo;}

    //El de codigo
    public String getCodigo() {return codigo;}
    public void setCodigo (String codigo) {this.codigo = codigo;}

    //El de diminutivo
    public String getDiminutivo() {return diminutivo;}
    public void setDiminutivo(String diminutivo) {this.diminutivo = diminutivo;}

    //El de nombre
    public String getNombre() {return nombre;}
    public void setNombre (String nombre) {this.nombre = nombre;}

    //El de HorasSemanales
    public Integer getHorasSemanales() {return horasSemanales;}
    public void setHorasSemanales(Integer horasSemanales) {this.horasSemanales = horasSemanales;}

    //El de creditos
    public Integer getCreditos() {return creditos;}
    public void setCreditos(Integer creditos) {this.creditos = creditos;}

}