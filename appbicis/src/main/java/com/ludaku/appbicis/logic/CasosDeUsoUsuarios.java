package com.ludaku.appbicis.logic;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@Service
public class CasosDeUsoUsuarios {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public void iniciarSesion(String login, String password) throws Exception{
        //Valida que exista el usuario
        
        /*
         Optional<RepositorioUsuario> u = repositorioUsuario.findById(login);
         if(u.isEmpty()){
            throw new Exception("Usuario no existe");
         }
         if(!((Usuario) u.get()).getContrasena().equals(password)){
            throw new Exception("Contraseña no coincide");
        }
        */
        /*Caso de uso registrar usuario
          
        */

    }

    public void crearUsuario(String login, String password, String nombre){
       //Validar que no haya otros usuarios con ese login
       List <Usuario> usuariosExistentes = repositorioUsuario.findByNickname(login);
        if (usuariosExistentes.size() > 0){
            throw new ExcepcionUsuarios("Existe otro usuario con este login");

        }
        
        if(password.trim().length() < 5){
            throw new ExcepcionUsuarios("password con numero muy bajo de caracteres")
        }
        Usuario u = new Usuario(nombre);
        
        repositorioUsuario.save(u); 
    }
}
