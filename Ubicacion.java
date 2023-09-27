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
    long ubicacionID;
    String nombreUb;
    String direccion;
    String sector;
    list <Ruta> rutasPerteneciente;
}
