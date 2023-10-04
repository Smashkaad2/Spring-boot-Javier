package com.ludaku.appbicis.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludaku.appbicis.modelo.Usuario;

@Repository
public interface RepositorioUsuario 
    extends JpaRepository<Usuario, Long>{
    
}
