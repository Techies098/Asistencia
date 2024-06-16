package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.LicenciaRequest;
import univsys.asistenciadocente.models.FacultadEntity;
import univsys.asistenciadocente.models.HorarioEntity;
import univsys.asistenciadocente.models.LicenciaEntity;
import univsys.asistenciadocente.models.UserEntity;
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

    public LicenciaController(LicenciaRepository licenciaRepository, UserRepository userRepository) {
        this.licenciaRepository = licenciaRepository;
        this.userRepository = userRepository;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(licencia);
    }
}
