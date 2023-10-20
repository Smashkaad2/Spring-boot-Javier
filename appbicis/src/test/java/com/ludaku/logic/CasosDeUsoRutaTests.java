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
    public void pruebaCrearRuta() {

        try {

            // Arrange
            Usuario u = new Usuario("Javier");
            u = usuarios.save(u);

            // act
            pruebaRutaCrear.crearRutas("Casa", "Ruta A casa", u.getUsuarioID());

            // assert
            List<Ruta> listaRutas = rutas.findByNombreRuta("Casa");
            assertTrue(listaRutas.size() > 0, "No se grabó la ruta");

            Ruta rutaEnBD = listaRutas.get(0);
            assertNotNull(rutaEnBD, "La ruta es NULL");
            assertEquals(rutaEnBD.getDescripcion(), "Ruta A casa", "La descripción no coincide");
            assertNotNull(rutaEnBD.getAutor(), "La ruta tiene el autor en NULL");
            assertEquals(rutaEnBD.getAutor().getNombreU(), "Javier", "El nombre del autor no coincide");

        } catch (Exception e) {
            fail("El programa fallo y no deberia");
        }

    }

    @Test
    public void pruebaCrearRutaConUsuarioInexistente() {

        try {

            pruebaRutaCrear.crearRutas("Casa", "Ruta A casa", 10L);

            fail("El programa creo la ruta y no deberia porque el usuario no existe");

        } catch (Exception e) {

            // valida que no se halla creado una ruta con ese nombre
            List<Ruta> listaRutas = rutas.findByNombreRuta("Casa");
            assertTrue(listaRutas.size() == 0, "Se grabó una ruta y no debía");

            // Ok
        }

    }

    @Test
    public void mostrarRutasAlternativasSinErrores() {
        try {
            // Arrange
            Usuario u = new Usuario();
            u.setNombreU("Jose Manuel");
            u.setApellido("Rodriguez Torres");
            u.setCorreo("josemanu456@gmail.com");
            u.setNickname("Jose");
            u.setContrasena("12345");

            Usuario u2 = new Usuario();
            u.setNombreU("Jose Jose");
            u.setApellido("Gordo Feliz");
            u.setCorreo("chiste@hotmail.com");
            u.setNickname("Gordis");
            u.setContrasena("123456");

            u = usuarios.save(u);

            Ruta r = new Ruta();
            r.setAutor(u);
            r.setParticipantes(u2);
            r.setDescripcion("Ruta super bonita gozando de belleza");
            r.setDistancia(20);
            r.setNombreRuta("Bello Paraiso");
            // Act
            pruebaRutaCrear.mostrarRutasAlternativas(20);
            // Assert
            // OK
        } catch (Exception e) {
            fail("Fallo por un error que no deberia", e);
        }
    }

    @Test
    public void mostrarRutasAlternativasNoExistenRutasConLaDistanciaEspecificada() {
        try {
            // Arrange
            Usuario u = new Usuario();
            u.setNombreU("Jose Manuel");
            u.setApellido("Rodriguez Torres");
            u.setCorreo("josemanu456@gmail.com");
            u.setNickname("Jose");
            u.setContrasena("12345");

            u = usuarios.save(u);

            Ruta r = new Ruta();
            r.setAutor(u);
            r.setDescripcion("Ruta super bonita gozando de belleza");
            r.setDistancia(10);
            r.setNombreRuta("Bello Paraiso");

            // Act
            pruebaRutaCrear.mostrarRutasAlternativas(20);

            // Assert
            fail("Tomo una ruta que no tenia la distancia especificada");

        } catch (Exception e) {
            // Ok
        }
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
            pruebaRutaCrear.unirseARuta(ruta.getRutaId(), usuario.getUsuarioID());

            // Assert
            Ruta rutaEnBD = rutas.findById(ruta.getRutaId()).orElse(null);
            assertNotNull(rutaEnBD, "La ruta es NULL");
            // assertTrue(rutaEnBD.getParticipantes().contains(usuario), "El usuario no se
            // unió a la ruta");

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

            pruebaRutaCrear.unirseARuta(10L, usuario.getUsuarioID());

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

            pruebaRutaCrear.unirseARuta(ruta.getRutaId(), 10L);

            // Validar que la ruta no tiene participantes
            Ruta rutaEnBD = rutas.findById(ruta.getRutaId()).orElse(null);
            assertNotNull(rutaEnBD, "La ruta es NULL");
            //assertTrue(rutaEnBD.getParticipantes().isEmpty(), "La ruta tiene participantes, pero no debería");

        } catch (Exception e) {
            fail("El programa falló y no debería");
        }
    }

    @Test
    public void pruebaFiltrarRutas() {
        try {
            // Arrange
            Ruta ruta1 = new Ruta("Ruta 1");
            ruta1.setDistancia(15);
            rutas.save(ruta1);

            Ruta ruta2 = new Ruta("Ruta 2");
            ruta2.setDistancia(25);
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
            ruta1.setDistancia(25);
            rutas.save(ruta1);

            Ruta ruta2 = new Ruta("Ruta 2");
            ruta2.setDistancia(30);
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
