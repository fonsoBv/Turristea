package com.example.alfonso.turristea.Domain;

/**
 * Created by Alfonso on 05/06/2018.
 */

public class Sitio {
    private float precio;
    private String ubicacion;
    private String tipo_de_viaje;
    private String descripcion;
    private String titulo;
    private String imagen;
    private float latitud;
    private float longitud;



    public Sitio(float precio, String ubicacion, String tipo_de_viaje, String descripcion, String titulo,
                 float latitud, float longitud,String imagen) {
        this.precio = precio;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.tipo_de_viaje = tipo_de_viaje;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public float getPrecio() {
        return precio;
    }


    public String getImagen() {
        return imagen;
    }


    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo_de_viaje() {
        return tipo_de_viaje;
    }

    public void setTipo_de_viaje(String tipo_de_viaje) {
        this.tipo_de_viaje = tipo_de_viaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
