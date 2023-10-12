package com.ludaku.logic;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ludaku.appbicis.AppbicisApplication;
import com.ludaku.appbicis.logic.*;
import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.*;

@SpringBootTest(classes = AppbicisApplication.class)
public class CasosDeUsoUsuarioTests {

    @Autowired
    private CasosDeUsoUsuarios casosDeUsoUsuarios;

    @Autowired
    private RepositorioUsuario usuarios;

    //CU: PERMITIR INICIAR SESION
    @Test
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
            
            u = usuarios.save(u);

            // Act
            casosDeUsoUsuarios.iniciarSesionUsuario("Jose", "12345");
            
            // Assert 
            // OK 

        } catch (Exception e) {
            fail("Fallo por un error que no deberia", e);
        }
    }

    @Test
    public void iniciarSesionUsuarioInexistente(){
        try {

            //Arrange
            usuarios.deleteAll();
               
            //Act
            casosDeUsoUsuarios.iniciarSesionUsuario("Eduardo", "12345");
            
            //Assert 
            fail("Inicio sesion con un nickname erroneo");
 
         } catch (Exception e) {
             //OK!!
         }
    }

    @Test
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
