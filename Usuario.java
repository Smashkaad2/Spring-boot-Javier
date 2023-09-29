import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.list;

@Entity
@Getter @Setter
public class Usuario {
    @Id
    @GeneratedValue
    private long UsuarioID;
    private String nombreU;
    private String apellido;
    private String correo;
    private String nickname;
    private list<Ruta> rutasPropias;
}
