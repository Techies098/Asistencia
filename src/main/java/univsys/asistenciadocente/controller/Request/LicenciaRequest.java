package univsys.asistenciadocente.controller.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LicenciaRequest {
    String razon;
    String inicio;
    String fin;
    Long userId;
}
