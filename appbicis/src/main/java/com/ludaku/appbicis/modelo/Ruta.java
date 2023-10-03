package com.ludaku.appbicis.modelo;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Ruta {
    @Id
    @GeneratedValue
    private String rutaId;

    private String nombreRuta;
    private String descripcion;

    @ManyToOne
    private String autor;

    private double distancia;

    private Date fecha;

    private double tiempo;

    @ManyToMany(mappedBy = "rutasPerteneciente")
    private List<Ubicacion> ubicaciones;
    
    @OneToMany(mappedBy = "calificacionRuta", fetch = FetchType.LAZY)
    private List<Calificacion> calificaciones;
}
