package com.ludaku.appbicis.logic;

public class ExcepcionCalificaciones extends Exception {
    public ExcepcionCalificaciones(){
        super();

    }
    public ExcepcionCalificaciones (String mensaje){
        super(mensaje);
    }
    public ExcepcionCalificaciones (String mensaje, Throwable cause){
        super(mensaje, cause);
    }
    
}