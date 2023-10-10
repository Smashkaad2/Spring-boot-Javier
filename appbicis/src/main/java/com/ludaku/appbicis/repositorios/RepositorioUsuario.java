package com.ludaku.appbicis.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludaku.appbicis.modelo.Usuario;
import java.util.List;


@Repository
public interface RepositorioUsuario 
    extends JpaRepository<Usuario, Long>{
    //El crea automaticamente 
    //save finAll
    List<Usuario> findByNickname(String nickname);
    List<Usuario> findByContrasena(String contrasena);
    
}
