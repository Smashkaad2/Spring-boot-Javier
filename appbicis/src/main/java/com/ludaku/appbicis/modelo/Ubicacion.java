package com.ludaku.appbicis.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@Entity
public class Ubicacion {
    @Id
    @GeneratedValue
    private long ubicacionID;

    private String nombreUb;
    private String direccion;
    private String sector;
    @ManyToMany(mappedBy = "ubicaciones")
    private List<Ruta> rutasPerteneciente;
}
