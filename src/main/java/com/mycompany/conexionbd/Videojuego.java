//utlizamos esta clase para introducir los datos de un videojuego en la
//base de datos con mayor comodidad
package com.mycompany.conexionbd;

import java.util.Date;
import java.time.LocalDate;




public class Videojuego {
    
    String nombre;
    
    String categoria;
    
    LocalDate fecha;
    
    String compania;
    
    float precio;

    public Videojuego(String nombre, String categoria, LocalDate fecha, String compania, float precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = fecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
