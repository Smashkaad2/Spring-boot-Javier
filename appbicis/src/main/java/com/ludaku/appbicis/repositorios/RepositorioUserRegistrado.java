package com.ludaku.appbicis.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludaku.appbicis.modelo.Usuario;
import java.util.List;


@Repository
public interface RepositorioUserRegistrado 
    extends JpaRepository<Usuario, Long>{

    List<Usuario> findByNickname(String nickname);
    
}