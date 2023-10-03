package logic;

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
        
        try {
            Usuario u = repositorioUsuario.findAllById(login);
            if(!u.getContrasena().equals(password)){
                throw new Exception();
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception();
        }
    }

    public void crearUsuario(String nombre){
        Usuario u = new Usuario();
        repositorioUsuario.save(u);
    }
}
