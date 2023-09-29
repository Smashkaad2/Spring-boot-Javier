import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.list;

@Entity
@Getter @Setter
public class Ubicacion {
    @Id
    @GeneratedValue
    private long ubicacionID;
    private String nombreUb;
    private String direccion;
    private String sector;
    private list<Ruta> rutasPerteneciente;

    
}
