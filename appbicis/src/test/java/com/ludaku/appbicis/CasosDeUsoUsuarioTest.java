package com.ludaku.appbicis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import logic.CasosDeUsoUsuarios;

@SpringBootTest
public class CasosDeUsoUsuarioTest {

    @Autowired
    private CasosDeUsoUsuarios casosDeUsoUsuarios;

    @Test
    public void pruebaLogin(){
        try {
            casosDeUsoUsuarios.iniciarSesion("X", "Y");
            
        } catch (Exception e) {
            // TODO: handle exception
            fail("fallo");
            assertEquals(1, 0, "Fallo2");
        }
    }
}
