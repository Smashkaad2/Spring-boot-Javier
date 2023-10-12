package com.ludaku.appbicis.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@Service
public class CasosDeUsoUsuarios {

    @Autowired
    private RepositorioUsuario repositorioUsuario;


    public void crearUsuario(String login, String password, String nombre) throws ExcepcionUsuarios{
       //Validar que no haya otros usuarios con ese login
       java.util.List <Usuario> usuariosExistentes = repositorioUsuario.findByNickname(login);
        if (usuariosExistentes.size() > 0){
            throw new ExcepcionUsuarios("Existe otro usuario con este login");

        }
        
        if(password.trim().length() < 5){
            throw new ExcepcionUsuarios("password con numero muy bajo de caracteres");
        }
        Usuario u = new Usuario(nombre);
        
        repositorioUsuario.save(u); 
        return;
    }

    public void iniciarSesionUsuario(String login, String password) throws ExcepcionUsuarios{
        java.util.List <Usuario> usuariosExistentes = repositorioUsuario.findByNickname(login);
        if(usuariosExistentes.size() == 0){
            throw new ExcepcionUsuarios("Nombre de Usuario no existe");
        }else{
            usuariosExistentes = repositorioUsuario.findByContrasena(password);
            if(usuariosExistentes.size() == 0){
                throw new ExcepcionUsuarios("No existe un usuario con esa contrasena");
            }
        }
        
        return;
    }

    public void opcionesDeRuta(String destino){
        //java.util.List <Ruta> rutasExistentes = repositorioUsuario.find
    }
}
