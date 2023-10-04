package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ludaku.appbicis.logic.CasosDeUsoUsuarios;

@RestController
public class ApiUsuario {
    @Autowired
    private CasosDeUsoUsuarios casosDeUsoUsuarios;
    
    @PostMapping("/api/usuarios")
    public void crearUsuario(String nombre){
        casosDeUsoUsuarios.crearUsuario(nombre);
    }
}
