package com.ludaku.appbicis.logic;

import com.ludaku.appbicis.modelo.Ruta;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioRuta;

public class CasosDeUsoCrearRutas {

    public void crearRutas(Long idRuta, String nombreRuta, Usuario nombreUsuario){

        java.util.List <Rutas> rutasExistentes = RepositorioRuta.findByNickname(nombreRuta);
        if (rutasExistentes.size() > 0){
            throw new ExcepcionUsuarios("Existe una ruta con este Nombre");
        }

        Ruta nuevaRuta = new Ruta();
        RepositorioRuta.save(nuevaRuta);

    }
    
}
