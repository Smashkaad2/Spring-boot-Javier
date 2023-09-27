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
    long UsuarioID;
    String nombreU;
    String apellido;
    String correo;
    String nickname;
    list<Ruta> rutasPropias;
}
