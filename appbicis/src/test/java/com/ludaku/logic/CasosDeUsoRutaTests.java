package com.ludaku.logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ludaku.appbicis.AppbicisApplication;
import com.ludaku.appbicis.logic.CasosDeUsoCrearRutas;
import com.ludaku.appbicis.modelo.Ruta;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioRuta;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@SpringBootTest(classes = AppbicisApplication.class)
public class CasosDeUsoRutaTests {

    @Autowired
    CasosDeUsoCrearRutas pruebaRutaCrear;

    @Autowired
    RepositorioUsuario usuarios;

    @Autowired
    RepositorioRuta rutas;

    @BeforeEach
    public void borraBD() {

        rutas.deleteAll();
        usuarios.deleteAll();

    }

    @Test
    public void pruebaUnirseARutaExitosamente() {
        try {
            // Arrange
            Usuario usuario = new Usuario("Javier");
            usuario = usuarios.save(usuario);

            Ruta ruta = new Ruta("Ruta A casa");
            ruta = rutas.save(ruta);

            // Act
            pruebaRutaCrear.unirseARuta(ruta.getId(), usuario.getUsuarioID());

            // Assert
            Ruta rutaEnBD = rutas.findById(ruta.getId()).orElse(null);
            assertNotNull(rutaEnBD, "La ruta es NULL");
            assertTrue(rutaEnBD.getParticipantes().contains(usuario), "El usuario no se unió a la ruta");

        } catch (Exception e) {
            fail("El programa falló y no debería");
        }
    }

    @Test
    public void pruebaUnirseARutaRutaInexistente() {
        try {
            // Arrange
            Usuario usuario = new Usuario("Javier");
            usuario = usuarios.save(usuario);

            // Act & Assert
            assertThrows(ExcepcionRutas.class, () -> {
                pruebaRutaCrear.unirseARuta(10L, usuario.getUsuarioID());
            });

            // Validar que el usuario no se unió a ninguna ruta
            Usuario usuarioEnBD = usuarios.findById(usuario.getUsuarioID()).orElse(null);
            assertNotNull(usuarioEnBD, "El usuario es NULL");
            assertTrue(usuarioEnBD.getRutas().isEmpty(), "El usuario se unió a una ruta, pero no debería");

        } catch (Exception e) {
            fail("El programa falló y no debería");
        }
    }

    @Test
    public void pruebaUnirseARutaUsuarioInexistente() {
        try {
            // Arrange
            Ruta ruta = new Ruta("Ruta A casa");
            ruta = rutas.save(ruta);

            // Act & Assert
            assertThrows(ExcepcionRutas.class, () -> {
                pruebaRutaCrear.unirseARuta(ruta.getId(), 10L);
            });

            // Validar que la ruta no tiene participantes
            Ruta rutaEnBD = rutas.findById(ruta.getId()).orElse(null);
            assertNotNull(rutaEnBD, "La ruta es NULL");
            assertTrue(rutaEnBD.getParticipantes().isEmpty(), "La ruta tiene participantes, pero no debería");

        } catch (Exception e) {
            fail("El programa falló y no debería");
        }
    }

    @Test
    public void pruebaFiltrarRutas() {
        try {
            // Arrange
            Ruta ruta1 = new Ruta("Ruta 1");
            ruta1.setDistanciaRecorrido(15);
            rutas.save(ruta1);

            Ruta ruta2 = new Ruta("Ruta 2");
            ruta2.setDistanciaRecorrido(25);
            rutas.save(ruta2);

            // Act
            List<Ruta> rutasFiltradas = pruebaRutaCrear.filtrarRutas(20);

            // Assert
            assertEquals(1, rutasFiltradas.size(), "Se esperaba una sola ruta en los resultados");

        } catch (Exception e) {
            fail("El programa falló y no debería");
        }
    }

    @Test
    public void pruebaFiltrarRutasSinResultados() {
        try {
            // Arrange
            Ruta ruta1 = new Ruta("Ruta 1");
            ruta1.setDistanciaRecorrido(25);
            rutas.save(ruta1);

            Ruta ruta2 = new Ruta("Ruta 2");
            ruta2.setDistanciaRecorrido(30);
            rutas.save(ruta2);

            // Act
            List<Ruta> rutasFiltradas = pruebaRutaCrear.filtrarRutas(20);

            // Assert
            assertTrue(rutasFiltradas.isEmpty(), "No se esperaban rutas en los resultados");

        } catch (Exception e) {
            fail("El programa falló y no debería");
        }
    }
}

