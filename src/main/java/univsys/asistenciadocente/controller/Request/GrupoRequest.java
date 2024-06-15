package univsys.asistenciadocente.controller.Request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoRequest {
    String nombre;
    String periodo;
    Long materia;
    Long user;
}
