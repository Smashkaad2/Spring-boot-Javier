package com.ludaku.appbicis.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long UsuarioID;

    private String nombreU;
    private String apellido;
    private String correo;
    private String nickname;
    private String contrasena;

    @OneToMany(mappedBy = "autor")
    private List<Ruta> rutasPropias;
    
    @OneToMany(mappedBy = "autor")
    private List<Calificacion> calificaciones;

    // un constructor
    public Usuario(String nombreU)
    {
        this.nombreU = nombreU;
    }
}

