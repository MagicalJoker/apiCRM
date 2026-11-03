package edu.altair.apiCRM.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "departamento")

public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento", nullable = false)
    private Long idDepartamento;

    /**
     * departamento:
     *  PK    id_departamento
     *  NN, UK codigo
     *  NN    nombre
     *  .     descripcion
     *  .     telefono
     */

    @Column(name ="codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name= "nombre", nullable = false)
    private String nombre;

    @Column(name ="descripcion")
    private String descripcion;

    @Column (name="telefono")
    private String telefono;


    //Tras haber mapeado las columnas, se crea un constructor vacío de forma OBLIGATORIA
    public Departamento () {}

    //Y ahora se hace el constructor real
    public Departamento(Long idDepartamento, String codigo, String nombre, String descripcion, String telefono) {
        this.idDepartamento = idDepartamento;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefono = telefono;
    }

    //Ahora van los getters y los setters

    //El de id
    public Long getIdDepartamento() {return idDepartamento;}
    public void setIdDepartamento (Long idDepartamento) {this.idDepartamento = idDepartamento;}

    //El de codigo
    public String getCodigo() {return codigo;}
    public void setCodigo (String codigo) {this.codigo = codigo;}

    //El de nombre
    public String getNombre() {return nombre;}
    public void setNombre (String nombre) {this.nombre = nombre;}

    //El de descripcion
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    //El de telefono
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}






}