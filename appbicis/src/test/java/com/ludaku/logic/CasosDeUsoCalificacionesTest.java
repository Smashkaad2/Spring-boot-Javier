package com.ludaku.logic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ludaku.appbicis.logic.CasosDeUsoCalificaciones;
import com.ludaku.appbicis.logic.ExcepcionCalificaciones;
import com.ludaku.appbicis.modelo.Ruta;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.modelo.Calificacion;
import com.ludaku.appbicis.repositorios.RepositorioCalificacion;
import com.ludaku.appbicis.repositorios.RepositorioRuta;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@SpringBootTest
public class CasosDeUsoCalificacionesTest {

    //SUT - Subject Under Test
    @Autowired
    CasosDeUsoCalificaciones casosDeUsoCalificaciones;

    // Fixing --- Clases adicionales
    @Autowired
    RepositorioCalificacion calificaciones;

    @Autowired
    RepositorioRuta rutas;

    @Autowired
    RepositorioUsuario usuarios;

    @BeforeEach
    public void prepararArrange()
    {
        calificaciones.deleteAll();
    }

    //CU07: Calificar ruta
    // -------------------

    //Curso normal de eventos
    @Test
    public void calificarRutaCorrectamente()
    {
        try {

            // Arrange
            Usuario u = new Usuario("JaimePrueba");
            usuarios.save(u);

            Ruta r = new Ruta();
            rutas.save(r);

            //Act
            casosDeUsoCalificaciones.calificarRuta(4, "Me gustó la ruta, muy divertida", u.getUsuarioID(), r.getRutaId());
            
            //Assert
            List<Calificacion> calificacionesDeCuatroPuntos = calificaciones.findByPuntuacion(4);
            if(calificacionesDeCuatroPuntos.size() == 0)
            {
                fail("No se grabó la puntuación");
            }
            

        } catch(ExcepcionCalificaciones e) {
            fail("Ocurrió un error al grabar la puntuación", e);
        }
    }

    //Curso alternativo -- (2) Cuando el usuario no ha completado rutas
    @Test
    public void calificarRutaSinRutasExistentes() {
        try {

            //Arrange
            Usuario u = new Usuario("JaimePrueba2");
            usuarios.save(u);

            Ruta r = new Ruta();
            rutas.save(r);

            //Act
            casosDeUsoCalificaciones.calificarRuta(4, "Me gustó la ruta, que se repita", u.getUsuarioID(), r.getRutaId());

            //Assert
            List<Ruta> rutasDelUsuario = u.getRutasPropias();
            if(rutasDelUsuario.size() == 0)
            {
                fail("El usuario no ha completado rutas");
            }

        } catch(ExcepcionCalificaciones e) {
            // ok (?
        }
    }

    //Curso alternativo -- (6) Cuando la puntuación indicada no se encuentra en el rango 1-5
    @Test
    public void calificarRutaConPuntuacionInvalida() {
        try {

            //Arrange
            Usuario u = new Usuario("JaimePrueba3");
            usuarios.save(u);

            Ruta r = new Ruta();
            rutas.save(r);

            //Act
            casosDeUsoCalificaciones.calificarRuta(6, "Me gustó la ruta, muy top", u.getUsuarioID(), r.getRutaId());

            //Assert
            List<Ruta> rutasDelUsuario = u.getRutasPropias();
            if(rutasDelUsuario.size() == 0)
            {
                fail("El usuario no ha completado rutas");
            }

        } catch(ExcepcionCalificaciones e) {
            // ok (?
        }
    }

}

