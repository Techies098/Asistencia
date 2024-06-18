package univsys.asistenciadocente.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.AsistenciaRequest;
import univsys.asistenciadocente.models.AsistenciaEntity;
import univsys.asistenciadocente.models.HorarioEntity;
import univsys.asistenciadocente.repositories.AsistenciaRepository;
import univsys.asistenciadocente.repositories.HorarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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
        LocalTime now = LocalTime.now();
        Optional<HorarioEntity> maybeHorario = horarioRepository.findByGrupoUserId(req.getUserId())
                .stream()
                .filter(h -> now.isAfter(h.getInicio()) && now.isBefore(h.getFin()))
                .findFirst();
        if (!maybeHorario.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No schedule found for the current time.");
        }
        HorarioEntity horario = maybeHorario.get();
        AsistenciaEntity asistencia = new AsistenciaEntity();
        asistencia.setEstado(req.getEstado());
        asistencia.setHorario(horario);
        asistencia.setFecha(LocalDateTime.now());
        asistenciaRepository.save(asistencia);
        Optional<HorarioEntity> Horarios2 = horarioRepository.findByGrupoUserId(req.getUserId())
                .stream()
                .filter(h -> horario.getFin().equals(h.getInicio()) && h.getAula().getId().equals(horario.getAula().getId()))
                .findFirst();
        if (Horarios2.isPresent()) {
            HorarioEntity horario2 = Horarios2.get();
            AsistenciaEntity asistencia2 = new AsistenciaEntity();
            asistencia2.setEstado(req.getEstado());
            asistencia2.setHorario(horario2);
            asistencia2.setFecha(LocalDateTime.now());
            asistenciaRepository.save(asistencia2);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(asistencia);
    }


    //    @Transactional
//    @PostMapping("/store")
//    public ResponseEntity<?> store(@RequestBody AsistenciaRequest req) {
//        List<HorarioEntity> horarios = horarioRepository.findByGrupoUserId(req.getUserId());
//
//        AsistenciaEntity asistencia = new AsistenciaEntity();
//        asistencia.setEstado(req.getEstado());
//        asistencia.setHorario(horario);
//        asistencia.setFecha(LocalDateTime.now());
//        asistenciaRepository.save(asistencia);
//        return ResponseEntity.status(HttpStatus.CREATED).body(asistencia);
//    }
/*
    {
    "estado": "Presente",
    "horario": 1
    }
    */
    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Long grupoId) {
        Map<String, Object> response = new HashMap<>();
        List<AsistenciaEntity> asist =asistenciaRepository.findByHorarioGrupoId(grupoId);
        response.put("status code", "200");
        response.put("mensaje", "lista de asistencias del grupo"+grupoId);
        response.put("fecha", LocalDate.now());
        response.put("data", asist);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<Map<String, Object>> docente(@PathVariable Long docenteId) {
        Map<String, Object> response = new HashMap<>();
        List<AsistenciaEntity> asist = asistenciaRepository.findByHorarioGrupoUserIdOrderByFechaDesc(docenteId);
        response.put("status code", "200");
        response.put("mensaje", "lista de asistencias del docente "+docenteId);
        response.put("fecha", LocalDate.now());
        response.put("data", asist);
        return ResponseEntity.ok(response);
    }
}