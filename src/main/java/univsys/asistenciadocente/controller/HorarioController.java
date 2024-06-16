package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.models.AulaEntity;
import univsys.asistenciadocente.models.HorarioEntity;
import univsys.asistenciadocente.models.UserEntity;
import univsys.asistenciadocente.repositories.AulaRepository;
import univsys.asistenciadocente.repositories.HorarioRepository;
import univsys.asistenciadocente.repositories.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/horario")
public class HorarioController {
    private final HorarioRepository horarioRepository;
    private final AulaRepository aulaRepository;
    private final UserRepository userRepository;

    public HorarioController(HorarioRepository horarioRepository, AulaRepository aulaRepository, UserRepository userRepository) {
        this.horarioRepository = horarioRepository;
        this.aulaRepository = aulaRepository;
        this.userRepository = userRepository;
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
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        if ("ADMIN".equals(user.getRol())){
            response.put("error", "Solo docentes");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        List<HorarioEntity> Horarios =horarioRepository.findByGrupoUserId(userId);
        response.put("status code", "200");
        response.put("mensaje", "horario show");
        response.put("fecha", LocalDate.now());
        response.put("user", user);
        response.put("horarios", Horarios);
        return ResponseEntity.ok(response);
    }
}
