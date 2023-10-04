package com.ludaku.appbicis.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludaku.appbicis.modelo.Ubicacion;

@Repository
public interface RepositorioUbicacion 
extends JpaRepository<Ubicacion, Long>{
    
}
