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

    @Column (name = "nombre", nullable = false)
    private String nombre;

    @Column (name = "apellidos", nullable = false)
    private String apellidos;

    @Column (name = "dni")
    private String dni;

    @Column (name = "email_centro")
    private String emailCentro;

    @Column (name = "email_personal")
    private String emailPersonal;

    @Column (name = "telefono_personal")
    private Integer telefonoPersonal;

    // Relación N:1 con Departamento (Un profesor pertenece a 0 ó 1 Departamento)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", nullable = true)
    private Departamento departamento;

    //Constructor vacío
    public Profesor () {}




    //Constructor real
    public Profesor (Long idProfesor, String nombre, String apellidos, String dni, String emailCentro, String emailPersonal,
                     Integer telefonoPersonal, Departamento departamento) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.emailCentro = emailCentro;
        this.emailPersonal = emailPersonal;
        this.telefonoPersonal = telefonoPersonal;
        this.departamento = departamento;
    }

    //Getters y Setters

    public long getIdProfesor() {return idProfesor;}
    public void setIdProfesor(long idProfesor) {this.idProfesor = idProfesor;}



    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getEmailCentro() {return emailCentro;}
    public void setEmailCentro(String emailCentro) {this.emailCentro = emailCentro;}

    public String getEmailPersonal() {return emailPersonal;}
    public void setEmailPersonal(String emailPersonal) {this.emailPersonal = emailPersonal;}

    public Integer getTelefonoPersonal() {return telefonoPersonal;}
    public void setTelefonoPersonal(Integer telefonoPersonal) {this.telefonoPersonal = telefonoPersonal;}

    public Departamento getDepartamento() {return departamento;}
    public void setDepartamento(Departamento departamento) {this.departamento = departamento;}













}
