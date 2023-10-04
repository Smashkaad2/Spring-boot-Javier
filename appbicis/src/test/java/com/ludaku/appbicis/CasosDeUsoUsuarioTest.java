package com.ludaku.appbicis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.support.Repositories;

import com.ludaku.appbicis.logic.*;
import com.ludaku.appbicis.repositorios.*;

@SpringBootTest
public class CasosDeUsoUsuarioTest {

    @Autowired
    private CasosDeUsoUsuarios casosDeUsoUsuarios;

    @Autowired
    private RepositorioUsuario usuarios;

    @Test
    public void pruebaLogin(){

        casosDeUsoUsuarios.crearUsuario("jaime");

    }
}
