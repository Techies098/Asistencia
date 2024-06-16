package univsys.asistenciadocente.controller.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignaRequest {
    private int nivel;
    private Long carrera;
    private Long materia;
}
