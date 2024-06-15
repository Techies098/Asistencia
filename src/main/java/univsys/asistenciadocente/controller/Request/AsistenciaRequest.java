package univsys.asistenciadocente.controller.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AsistenciaRequest {
    String estado;
    Long horario;
}
