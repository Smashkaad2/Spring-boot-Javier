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
@Getter
@Setter
@EqualsAndHashCode

public class Calificacion {

    Stirng calificacionId;
    String autor;
    Ruta calificacionRuta;
    int puntuacion;
    String comentarios;

    public Calificacion(Stirng calificacionId, String autor, Ruta calificacionRuta, int puntuacion,
            String comentarios) {
        this.calificacionId = calificacionId;
        this.autor = autor;
        this.calificacionRuta = calificacionRuta;
        this.puntuacion = puntuacion;
        this.comentarios = comentarios;
    }

    public Stirng getCalificacionId() {
        return this.calificacionId;
    }

    public void setCalificacionId(Stirng calificacionId) {
        this.calificacionId = calificacionId;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Ruta getCalificacionRuta() {
        return this.calificacionRuta;
    }

    public void setCalificacionRuta(Ruta calificacionRuta) {
        this.calificacionRuta = calificacionRuta;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}