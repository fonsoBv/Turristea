package com.example.alfonso.turristea.Domain;

/**
 * Created by Alfonso on 05/06/2018.
 */

public class Sitio {
    private String precio;
    private String ubicacion;
    private String tipo_de_viaje;
    private String descripcion;
    private String titulo;
    private String imagen;
    private String video;
    private double latitud;
    private double longitud;



    public Sitio(String precio, String ubicacion, String tipo_de_viaje, String descripcion, String titulo,
                 double latitud, double longitud,String imagen,String video) {
        this.precio = precio;
        this.video = video;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.tipo_de_viaje = tipo_de_viaje;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getPrecio() {
        return precio;
    }


    public String getImagen() {
        return imagen;
    }


    public void setPrecio(String precio) {
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
