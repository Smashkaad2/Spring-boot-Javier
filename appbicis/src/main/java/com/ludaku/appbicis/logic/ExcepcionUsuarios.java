package com.ludaku.appbicis.logic;

public class ExcepcionUsuarios extends Exception {

    public ExcepcionUsuarios(){
        super();

    }
    public ExcepcionUsuarios (String mensaje){
        super(mensaje);
    }
    public ExcepcionUsuarios(String mensaje, Throwable cause){
        super(mensaje, cause);
    }

}
