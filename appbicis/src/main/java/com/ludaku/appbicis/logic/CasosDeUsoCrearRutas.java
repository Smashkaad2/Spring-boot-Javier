package com.ludaku.appbicis.logic;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludaku.appbicis.modelo.Ruta;
import com.ludaku.appbicis.modelo.Ubicacion;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioRuta;
import com.ludaku.appbicis.repositorios.RepositorioUbicacion;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@Service
public class CasosDeUsoCrearRutas {

    @Autowired
    RepositorioRuta rutas;

    @Autowired
    RepositorioUsuario usuarios;

    @Autowired
    RepositorioUbicacion ubicaciones;


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
        java.util.List <Ruta> rutasExistentes = rutas.findByNombreRuta(nombreRuta);
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
    public void agregarUbicacionARuta(Long idRuta, Long idUbicacion) 
        throws ExcepcionRutas {

        // 1. valida que exista la ruta
        Optional<Ruta> ruta = rutas.findById(idRuta);
        if (ruta.isEmpty()) {
            throw new ExcepcionRutas("No existe una ruta con ese id");
        }

        // 2. valida que exista la ubicación (**)
        Optional<Ubicacion> ubicacion = ubicaciones.findById(idUbicacion);
        if (ubicacion.isEmpty()) {
            throw new ExcepcionRutas("No existe una ubicación con este id");
        }

        // 3. agrega la ubicación a la ruta
        Ruta rutaR = ruta.get();
        rutaR.getUbicaciones().add(ubicacion.get());

        // 4- guarda la ruta -- con la ubicación agregada
        rutas.save(rutaR);
    }

}
