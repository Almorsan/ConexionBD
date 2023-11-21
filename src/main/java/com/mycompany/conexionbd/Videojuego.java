
package com.mycompany.conexionbd;

import java.sql.Date;


public class Videojuego {
    
    String nombre;
    
    String categoria;
    
    Date fecha_Lanzamiento;
    
    String compania;
    
    float precio;

    public Videojuego(String nombre, String categoria, Date fecha_Lanzamiento, String compania, float precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha_Lanzamiento = fecha_Lanzamiento;
        this.compania = compania;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha_Lanzamiento() {
        return fecha_Lanzamiento;
    }

    public void setFecha_Lanzamiento(Date fecha_Lanzamiento) {
        this.fecha_Lanzamiento = fecha_Lanzamiento;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
    
    
    
}
