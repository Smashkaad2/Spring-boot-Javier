package logic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludaku.appbicis.modelo.Usuario;

import repositorios.RepositorioUsuario;

@Service
public class CasosDeUsoUsuarios {
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public void iniciarSesion(String login, String password) throws Exception{
        //Valida que exista el usuario
        
        
         Optional<RepositorioUsuario> u = repositorioUsuario.findById(login);
         if(u.isEmpty()){
            throw new Exception("Usuario no existe");
         }
         if(!((Usuario) u.get()).getContrasena().equals(password)){
            throw new Exception("Contraseña no coincide");
        }
    }

    public void crearUsuario(String nombre){
        Usuario u = new Usuario(u);
        //repositorioUsuario.save(u); 
    }
}
