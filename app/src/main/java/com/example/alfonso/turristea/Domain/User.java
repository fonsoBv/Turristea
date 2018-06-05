package com.example.alfonso.turristea.Domain;

/**
 * Created by Alfonso on 02/06/2018.
 */

public class User {

    private String nombre;
    private String contrasenia;

    public User(){
        nombre = "";
        contrasenia="";
    }

    public User(String nombre,String contrasenia){
        this.nombre = nombre;
        this.contrasenia=contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
