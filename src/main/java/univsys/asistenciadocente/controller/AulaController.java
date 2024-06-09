package univsys.asistenciadocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.models.AulaEntity;
import univsys.asistenciadocente.models.CarreraEntity;
import univsys.asistenciadocente.repositories.AulaRepositry;
import univsys.asistenciadocente.repositories.ModuloRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/aula")
public class AulaController {
    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private AulaRepositry aulaRepositry;
    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<AulaEntity> carreras = (List<AulaEntity>) aulaRepositry.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de aulas");
        response.put("fecha", LocalDate.now());
        response.put("data", carreras);
        return ResponseEntity.ok(response);
    }
}
