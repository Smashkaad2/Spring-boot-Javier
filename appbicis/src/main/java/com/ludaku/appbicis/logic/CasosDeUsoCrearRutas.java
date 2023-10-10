package com.ludaku.appbicis.logic;

import java.security.PublicKey;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ludaku.appbicis.modelo.Ruta;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioRuta;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

public class CasosDeUsoCrearRutas {

    @Autowired
    RepositorioRuta rutas;

    @Autowired
    RepositorioUsuario usuarios;


    // CU01: Crear ruta
    public void crearRutas(
        String nombreRuta,
        String descripcion, 
        Long idUsuario 
        // ...
    )
        throws ExcepcionRutas {

        // 1. Valida que exista un usuario con ese id
        Optional<Usuario> usuario = usuarios.findById(idUsuario);
        if (usuario.isEmpty()) {
            throw new ExcepcionRutas("No existe un usuario con ese id");
        }

        // 2. Valida que no ...
        java.util.List <Ruta> rutasExistentes = rutas.findByNickname(nombreRuta);
        if (rutasExistentes.size() > 0){
            throw new ExcepcionRutas("Existe una ruta con este Nombre");
        }

        // :

        // 10. Guarda la nueva ruta 
        Ruta nuevaRuta = new Ruta();

        nuevaRuta.setFecha(new Date()); // fecha y hora actual

        // asigno como autor al usuario que buscamos al inicio del caso de uso
        nuevaRuta.setAutor(usuario.get());

        rutas.save(nuevaRuta);

    }
    
    // otro método?
    public void agregarUbicacionARuta(Long idRuta, Long idUbicacion) {

        // 1. valida que exista la ruta

        // 2. valida que exista la ubicación (**)

        // 3. agrega la ubicación a la ruta

        // 4- guarda la ruta -- con la ubicación agregada

    }

}
