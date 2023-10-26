package com.ludaku.appbicis.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludaku.appbicis.modelo.Usuario;
import com.ludaku.appbicis.repositorios.RepositorioUserRegistrado;
import com.ludaku.appbicis.repositorios.RepositorioUsuario;

@Service
public class CasosDeUsoUsuarios {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioUserRegistrado repositorioUserRegistrado;

    public void crearUsuario(String login, String password, String nombre) throws ExcepcionUsuarios {

        // Validar que no haya otros usuarios con ese login pb
        List<Usuario> usuariosExistentes = repositorioUsuario.findByNickname(login);
        if (usuariosExistentes.size() > 0) {
            throw new ExcepcionUsuarios("Existe otro usuario con este login");

        }

        if (password.trim().length() < 5) {
            throw new ExcepcionUsuarios("password con numero muy bajo de caracteres");
        }

        Usuario u = new Usuario();
        u.setNickname(login);
        u.setContrasena(password);
        u.setNombreU(nombre);
        
        u = repositorioUsuario.save(u);
        return;
    }


    public void iniciarSesionUsuario(String login, String password) throws ExcepcionUsuarios {
        
        List<Usuario> usuariosExistentes = repositorioUsuario.findByNickname(login);
        if (usuariosExistentes.size() == 0) {
            throw new ExcepcionUsuarios("Nombre de Usuario no existe");
        }

        Usuario u = usuariosExistentes.get(0);
        if (! u.getContrasena().equals(password)) {
            throw new ExcepcionUsuarios("Contraseña no coincide");
        }

        return;
    }

    public void registrarUsuario(String userName) throws ExcepcionUsuarios {

        //#1 encuentra el usuario a partir de su Id
        List<Usuario> usuario = repositorioUsuario.findByNickname(userName);
        if (usuario.isEmpty()) {
            throw new ExcepcionUsuarios("No existe un usuario con ese id");
        }

        //#2 Guarda el usuario encontrado
        Usuario user = usuario.get(0);

        //#3 Registrarlo en el repositorio de usuarios registrados
        repositorioUserRegistrado.save(user);
    }

}
