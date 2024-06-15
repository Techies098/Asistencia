package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.AsistenciaRequest;
import univsys.asistenciadocente.models.AsistenciaEntity;
import univsys.asistenciadocente.models.AulaEntity;
import univsys.asistenciadocente.models.HorarioEntity;
import univsys.asistenciadocente.repositories.AsistenciaRepository;
import univsys.asistenciadocente.repositories.HorarioRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    private final AsistenciaRepository asistenciaRepository;
    private final HorarioRepository horarioRepository;

    public AsistenciaController(AsistenciaRepository asistenciaRepository, HorarioRepository horarioRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.horarioRepository = horarioRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<AsistenciaEntity> asist = (List<AsistenciaEntity>) asistenciaRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de asistencias");
        response.put("fecha", LocalDate.now());
        response.put("data", asist);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody AsistenciaRequest req) {
        HorarioEntity horario = horarioRepository.findById(req.getHorario())
                .orElseThrow(() -> new EntityNotFoundException("horario not found"));
        AsistenciaEntity asistencia = new AsistenciaEntity();
        asistencia.setEstado(req.getEstado());
        asistencia.setHorario(horario);
        asistencia.setFecha(new Date());
        asistenciaRepository.save(asistencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(asistencia);
    }/*{
    }
    */
}