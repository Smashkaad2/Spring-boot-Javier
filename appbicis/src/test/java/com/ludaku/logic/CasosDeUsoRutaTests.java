package com.ludaku.logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ludaku.appbicis.logic.CasosDeUsoCrearRutas;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@SpringBootTest
public class CasosDeUsoRutaTests {

    @Autowired
    CasosDeUsoCrearRutas pruebaRutaCrear;

    @Autowired
    RepositorioUsuario usuarios;

    @Test
    public void pruebaCrearRuta() {

        usuarios.deleteAll();
        Usuario u = new Usuario("Javier");
        u = usuarios.save(u);
        try {
            pruebaRutaCrear.crearRutas("Casa", "Ruta A casa", u.getUsuarioID());
        } catch (Exception e) {
            fail("El programa fallo y no deberia");
        }

    }

    @Test
    public void pruebaCrearRutaConUsuarioInexistente() {

        usuarios.deleteAll();
        try {
            pruebaRutaCrear.crearRutas("Casa", "Ruta A casa", 10L);
            fail("El programa creo la ruta y no deberia porque el usuario no existe");
        } catch (Exception e) {
            //Ok
        }

    }

}
