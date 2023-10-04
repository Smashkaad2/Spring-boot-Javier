package com.ludaku.appbicis.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Ubicacion {
    
    @Id
    @GeneratedValue
    private long ubicacionID;

    private String nombreUb;
    private String direccion;
    private String sector;

    @ManyToMany
    @JoinTable(name = "ruta_ubicacion",
        joinColumns = @JoinColumn(name="ruta_id"),
        inverseJoinColumns = @JoinColumn(name="ubicacion_id"))
    private List<Ruta> rutasPerteneciente;
}
