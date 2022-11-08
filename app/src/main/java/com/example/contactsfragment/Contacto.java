package com.example.contactsfragment;

import java.io.Serializable;

public class Contacto implements Serializable {
    private int id;
    private final String nombre;
    private final String apellidos;
    private final String fechaNacimiento;
    private final String companyia;
    private final String email;
    private final String movil1;
    private final String movil2;
    private final String direccion;

    public Contacto(String nombre, String apellidos, String fechaNacimiento, String companyia, String email, String movil1, String movil2, String direccion) {
        this.id = id++;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.companyia = companyia;
        this.email = email;
        this.movil1 = movil1;
        this.movil2 = movil2;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCompanyia() {
        return companyia;
    }

    public String getEmail() {
        return email;
    }

    public String getMovil1() {
        return movil1;
    }

    public String getMovil2() {
        return movil2;
    }

    public String getDireccion() {
        return direccion;
    }
}
