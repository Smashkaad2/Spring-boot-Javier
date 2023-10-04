package com.ludaku.appbicis.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludaku.appbicis.modelo.Ruta;

@Repository
public interface RepositorioRuta 
extends JpaRepository<Ruta,Long>{
    
}
