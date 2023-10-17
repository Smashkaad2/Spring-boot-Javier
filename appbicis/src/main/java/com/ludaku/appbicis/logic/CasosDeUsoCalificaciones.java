package com.ludaku.appbicis.logic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludaku.appbicis.modelo.Ruta;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.modelo.Calificacion;
import com.ludaku.appbicis.repositorios.RepositorioCalificacion;
import com.ludaku.appbicis.repositorios.RepositorioRuta;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@Service
public class CasosDeUsoCalificaciones {

    @Autowired
    RepositorioRuta rutas;

    @Autowired
    RepositorioUsuario usuarios;

    @Autowired
    RepositorioCalificacion calificaciones;

    //CU07: Calificar rutas completadas
    public void calificarRuta(
        int puntuacion,
        String comentarios,
        Long idUsuario,
        Long idRuta

        //...
    )

    throws ExcepcionCalificaciones
    {
        //1. Valida que exista un usuario con ese id
        Optional<Usuario> usuario = usuarios.findById(idUsuario);
        if(usuario.isEmpty())
        {
            throw new ExcepcionCalificaciones("No existe un usuario con ese Id");
        }

        //2. Valida que la ruta que desea puntuar exista con el idRuta
        Optional<Ruta> ruta = rutas.findById(idRuta);
        if(ruta.isEmpty())
        {
            throw new ExcepcionCalificaciones("No existe una ruta con el Id indicado");
        }

        //3. Crear la nueva calificación
        Calificacion nuevaCalificacion = new Calificacion();

        // se asigna como autor al usuario buscado al principio
        nuevaCalificacion.setAutor(usuario.get());

        nuevaCalificacion.setPuntuacion(puntuacion);
        nuevaCalificacion.setComentarios(comentarios);

        // se asigna la ruta buscada como aquella que recibe la puntuación
        nuevaCalificacion.setCalificacionRuta(ruta.get());

        //4. Guardar la nueva calificación creada
        calificaciones.save(nuevaCalificacion);
    }
    
}
