package com.ludaku.appbicis.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludaku.appbicis.modelo.Calificacion;

@Repository
public interface RepositorioCalificacion 
extends JpaRepository<Calificacion, Long>{

    List<Calificacion> findByPuntuacion(int puntuacion);
    
}
