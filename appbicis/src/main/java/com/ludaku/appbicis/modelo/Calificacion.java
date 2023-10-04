package com.ludaku.appbicis.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@Entity
public class Calificacion {

    @Id
    @GeneratedValue
    private long calificacionId;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Ruta calificacionRuta;

    private int puntuacion;
    private String comentarios;

}
