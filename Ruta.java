package com.ingesoft.puntos.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode

public class Ruta{

String rutaId;
String nombreRuta;
String descripcion;
String autor;
double distancia;
String fecha;
double tiempo;
private List<Ubicacion> ubicaciones;
private List<Calificacion> calificaciones;



    public Ruta(String rutaId, String nombreRuta, String descripcion, String autor, double distancia, String fecha, double tiempo, List<Ubicacion> ubicaciones, List<Calificacion> calificaciones) {
        this.rutaId = rutaId;
        this.nombreRuta = nombreRuta;
        this.descripcion = descripcion;
        this.autor = autor;
        this.distancia = distancia;
        this.fecha = fecha;
        this.tiempo = tiempo;
        this.ubicaciones = ubicaciones;
        this.calificaciones = calificaciones;
    }

    public String getRutaId() {
        return this.rutaId;
    }

    public void setRutaId(String rutaId) {
        this.rutaId = rutaId;
    }

    public String getNombreRuta() {
        return this.nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public List<Ubicacion> getUbicaciones() {
        return this.ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public List<Calificacion> getCalificaciones() {
        return this.calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }



}