package com.ludaku.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static org.assertj.core.api.Assert.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.support.Repositories;

import com.ludaku.appbicis.logic.*;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.*;

@SpringBootTest
public class CasosDeUsoUsuarioTest {

    @Autowired
    private CasosDeUsoUsuarios casosDeUsoUsuarios;

    @Autowired
    private RepositorioUsuario usuarios;



    //CU: PERMITIR INICIAR SESION
    public void iniciarSesionUsuarioSinErrores() throws ExcepcionUsuarios{
        
        try {
           //Arrange
            usuarios.deleteAll();
            Usuario u = new Usuario();
            u.setNombreU("Jose Manuel");
            u.setApellido("Rodriguez Torres");
            u.setCorreo("josemanu456@gmail.com");
            u.setNickname("Jose");
            u.setContrasena("12345");
            
            //Act
            casosDeUsoUsuarios.iniciarSesionUsuario("Jose", "12345");
            //Assert 
            java.util.List <Usuario> usuariosExistentes = usuarios.findByNickname("Jose");
            if(usuariosExistentes.size() == 0){
                throw new ExcepcionUsuarios("Nombre de Usuario no existe");
            }else{
                usuariosExistentes = usuarios.findByContrasena("12345");
                if(usuariosExistentes.size() == 0){
                    throw new ExcepcionUsuarios("No existe un usuario con esa contrasena");
                }
            }

        } catch (Exception e) {
            fail("Fallo por un error que no deberia", e);
        }
    }

    public void iniciarSesionUsuarioInexistente(){
        try {
            //Arrange
             usuarios.deleteAll();
             Usuario u = new Usuario();
             u.setNombreU("Jose Manuel");
             u.setApellido("Rodriguez Torres");
             u.setCorreo("josemanu456@gmail.com");
             u.setNickname("Jose");
             u.setContrasena("12345");
             
             //Act
             casosDeUsoUsuarios.iniciarSesionUsuario("Eduardo", "12345");
             //Assert 
             fail("Inicio sesion con un nickname erroneo");
 
         } catch (Exception e) {
             //OK!!
         }
    }

    public void iniciarSesionContrasenaIncorrecta(){
        try {
            //Arrange
             usuarios.deleteAll();
             Usuario u = new Usuario();
             u.setNombreU("Jose Manuel");
             u.setApellido("Rodriguez Torres");
             u.setCorreo("josemanu456@gmail.com");
             u.setNickname("Jose");
             u.setContrasena("12345");
             
             //Act
             casosDeUsoUsuarios.iniciarSesionUsuario("Jose", "12");
             //Assert 
             fail("Inicio sesion con una contrasena erronea");
 
         } catch (Exception e) {
             //OK!! 
         }
    }

}
