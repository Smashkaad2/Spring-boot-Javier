package com.ludaku.appbicis.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private long UsuarioID;

    private String nombreU;
    private String apellido;
    private String correo;
    private String nickname;
    private String contrasena;

    @OneToMany(mappedBy = "autor")
    private List<Ruta> rutasPropias;
    
    @OneToMany(mappedBy = "autor")
    private List<Calificacion> calificaciones;
}

