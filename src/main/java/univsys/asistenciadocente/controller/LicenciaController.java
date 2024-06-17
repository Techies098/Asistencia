package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.LicenciaRequest;
import univsys.asistenciadocente.models.*;
import univsys.asistenciadocente.repositories.AsistenciaRepository;
import univsys.asistenciadocente.repositories.HorarioRepository;
import univsys.asistenciadocente.repositories.LicenciaRepository;
import univsys.asistenciadocente.repositories.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/licencia")
public class LicenciaController {

    private final LicenciaRepository licenciaRepository;
    private final UserRepository userRepository;
    private final HorarioRepository horarioRepository;
    private final AsistenciaRepository asistenciaRepository;

    public LicenciaController(LicenciaRepository licenciaRepository, UserRepository userRepository, HorarioRepository horarioRepository, AsistenciaRepository asistenciaRepository) {
        this.licenciaRepository = licenciaRepository;
        this.userRepository = userRepository;
        this.horarioRepository = horarioRepository;
        this.asistenciaRepository = asistenciaRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<LicenciaEntity> licencias = (List<LicenciaEntity>) licenciaRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de licencias");
        response.put("fecha", LocalDate.now());
        response.put("data", licencias);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody LicenciaRequest req) {
        LicenciaEntity licencia = new LicenciaEntity();
        UserEntity user = userRepository.findById(req.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        licencia.setRazon(req.getRazon());
        licencia.setInicio(LocalDate.parse(req.getInicio()));
        licencia.setFin(LocalDate.parse(req.getFin()));
        licencia.setUser(user);
        licenciaRepository.save(licencia);

        LocalDate start = licencia.getInicio();
        LocalDate end = licencia.getFin();
        Long userId = req.getUserId();
        while (!start.isAfter(end)) {
            List<HorarioEntity> horariosDelusuario = horarioRepository.findByGrupoUserId(userId);
            for (HorarioEntity horario : horariosDelusuario) {
                AsistenciaEntity asistencia = new AsistenciaEntity();
                asistencia.setEstado("licencia");
                asistencia.setHorario(horario);
                asistencia.setFecha(start.atStartOfDay());
                asistenciaRepository.save(asistencia);
            }
            start = start.plusDays(1); // Incrementamos el día
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(licencia);
    }
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<Map<String, Object>> docente(@PathVariable Long docenteId) {
        Map<String, Object> response = new HashMap<>();
        List<LicenciaEntity> lic = licenciaRepository.findByUserId(docenteId);
        response.put("status code", "200");
        response.put("mensaje", "lista de licencias del docente "+docenteId);
        response.put("fecha", LocalDate.now());
        response.put("data", lic);
        return ResponseEntity.ok(response);
    }
}
