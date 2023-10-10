package com.ludaku.appbicis.logic;

public class ExcepcionRutas extends Exception {

    public ExcepcionRutas(){
        super();

    }
    public ExcepcionRutas (String mensaje){
        super(mensaje);
    }
    public ExcepcionRutas(String mensaje, Throwable cause){
        super(mensaje, cause);
    }

}
