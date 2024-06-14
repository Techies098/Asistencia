package univsys.asistenciadocente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.models.AulaEntity;
import univsys.asistenciadocente.models.HorarioEntity;
import univsys.asistenciadocente.repositories.HorarioRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/horario")
public class HorarioController {
    private final HorarioRepository horarioRepository;

    public HorarioController(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<HorarioEntity> Horarios = (List<HorarioEntity>) horarioRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de Horarios");
        response.put("fecha", LocalDate.now());
        response.put("data", Horarios);
        return ResponseEntity.ok(response);
    }
}
