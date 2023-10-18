package com.ludaku.appbicis.logic;

import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

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
    ) throws ExcepcionRutas {

        // 1. Valida que exista un usuario con ese id
        Optional<Usuario> usuario = usuarios.findById(idUsuario);
        if (usuario.isEmpty()) {
            throw new ExcepcionRutas("No existe un usuario con ese id");
        }

        // 2. Valida que no exista una ruta con este nombre
        java.util.List<Ruta> rutasExistentes = rutas.findByNombreRuta(nombreRuta);
        if (rutasExistentes.size() > 0){
            throw new ExcepcionRutas("Existe una ruta con este Nombre");
        }

        //Guarda la nueva ruta
        Ruta nuevaRuta = new Ruta();

        nuevaRuta.setNombreRuta(nombreRuta);
        nuevaRuta.setDescripcion(descripcion);
        nuevaRuta.setFecha(new Date()); // fecha y hora actual

        // asigno como autor al usuario que buscamos al inicio del caso de uso
        nuevaRuta.setAutor(usuario.get());

        rutas.save(nuevaRuta);

    }

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


    public void mostrarRutasAlternativas(double distancia) throws ExcepcionRutas{
        //Valida que haya rutas con la distancia solicitada
        List<Ruta> ruta = rutas.findByDistanciaRutas(distancia);
        if(ruta.isEmpty()){
            throw new ExcepcionRutas("No existen rutas con esa distancia");
        }
        //Muestra las rutas solicitadas
        for(int i = 0; i<ruta.size(); i++){
            System.out.println(ruta.get(i).getNombreRuta());
            System.out.println(ruta.get(i).getDescripcion());
            System.out.println(ruta.get(i).getDistancia());
            System.out.println(ruta.get(i).getAutor().getNickname());
        }

        return;
    }


    // CU02: Unirse a una ruta específica
    public void unirseARuta(Long idRuta, Long idUsuario) throws ExcepcionRutas {
        // 1. Valida que exista la ruta
        Optional<Ruta> rutaOptional = rutas.findById(idRuta);
        if (rutaOptional.isEmpty()) {
            throw new ExcepcionRutas("No existe una ruta con ese id");
        }

        Ruta rutaSeleccionada = rutaOptional.get();

        // 2. Valida que exista el usuario
        Optional<Usuario> usuarioOptional = usuarios.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            throw new ExcepcionRutas("No existe un usuario con ese id");
        }

        Usuario usuario = usuarioOptional.get();

        // 3. Verifica que exista cupo para un nuevo participante
        if (rutaSeleccionada.getCupoDisponible() <= 0) {
            throw new ExcepcionRutas("No hay disponibilidad de cupos en esta ruta");
        }

        // 4. Añade al usuario a la lista de participantes de la ruta
        rutaSeleccionada.agregarParticipante(usuario);

        // 5. Disminuye el cupo disponible
        rutaSeleccionada.disminuirCupo();

        // 6. Actualiza la ruta en la base de datos
        rutas.save(rutaSeleccionada);

        // 7. Muestra alerta "se ha unido con éxito"
        System.out.println("Se ha unido con éxito a la ruta " + rutaSeleccionada.getNombreRuta());
    }

    // CU03: Filtrar rutas existentes
    public List<Ruta> filtrarRutas(double distanciaMaxima) {
        // 1. Muestra todas las rutas disponibles
        List<Ruta> todasLasRutas = rutas.findAll();

        // 2. Filtra las rutas por distancia máxima
        List<Ruta> rutasFiltradas = todasLasRutas.stream()
                .filter(ruta -> ruta.getDistanciaRecorrido() <= distanciaMaxima)
                .collect(Collectors.toList());

        // 3. Si no existen rutas que cumplan con los filtros especificados, muestra un mensaje
        if (rutasFiltradas.isEmpty()) {
            System.out.println("No hay rutas existentes con los requisitos especificados");
        }

        return rutasFiltradas;
    }
}
