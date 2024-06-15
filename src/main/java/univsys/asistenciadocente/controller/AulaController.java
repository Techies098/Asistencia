package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.AulaRequest;
import univsys.asistenciadocente.models.AulaEntity;
import univsys.asistenciadocente.models.FacultadEntity;
import univsys.asistenciadocente.models.HorarioEntity;
import univsys.asistenciadocente.models.ModuloEntity;
import univsys.asistenciadocente.repositories.AulaRepository;
import univsys.asistenciadocente.repositories.HorarioRepository;
import univsys.asistenciadocente.repositories.ModuloRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/aula")
public class AulaController {
    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private AulaRepository aulaRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<AulaEntity> aulas = (List<AulaEntity>) aulaRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de aulas");
        response.put("fecha", LocalDate.now());
        response.put("data", aulas);
        return ResponseEntity.ok(response);
    }

    @Autowired
    public AulaController(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody AulaRequest req) {
        AulaEntity aula = new AulaEntity();
        ModuloEntity modulo = moduloRepository.findById(req.getModulo())
                .orElseThrow(() -> new EntityNotFoundException("modulo not found"));
        aula.setNumero(req.getNumero());
        aula.setModulo(modulo);
        aula.setCapacidad(req.getCapacidad());
        aulaRepository.save(aula);
        creaHorarios(aula.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(aula);
    }/*
    {
        "numero": 1,
        "modulo": 1
    }
    */

    @GetMapping("/{aulaId}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Long aulaId) {
        Map<String, Object> response = new HashMap<>();
        Optional<AulaEntity> aulas = aulaRepository.findById(aulaId);
        if (aulas.isPresent()) {
            AulaEntity aula = aulas.get();
            List<HorarioEntity> Horarios = horarioRepository.findByAulaId(aula.getId());
            response.put("status code", "200");
            response.put("mensaje", "Detalles del aula");
            response.put("fecha", LocalDate.now());
            response.put("aula", aula);
            response.put("Horarios", Horarios);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void creaHorarios(Long aulaId) {
        horarioSeed(aulaId, "07:00", "07:45");
        horarioSeed(aulaId, "07:45", "08:30");
        horarioSeed(aulaId, "08:30", "09:15");
        horarioSeed(aulaId, "09:15", "10:00");
        horarioSeed(aulaId, "10:00", "10:45");
        horarioSeed(aulaId, "10:45", "11:30");
        horarioSeed(aulaId, "11:30", "12:15");
        horarioSeed(aulaId, "12:15", "13:00");
        horarioSeed(aulaId, "13:45", "14:30");
        horarioSeed(aulaId, "14:30", "15:15");
        horarioSeed(aulaId, "15:15", "16:00");
    }

    public void horarioSeed(Long aulaID, String Sinicio, String Sfin) {
        Optional<AulaEntity> aula = aulaRepository.findById(aulaID);
        if (aula.isPresent()) {
            LocalTime fin = LocalTime.parse(Sfin);
            LocalTime inicio = LocalTime.parse(Sinicio);
            HorarioEntity horario = new HorarioEntity();
            horario.setAula(aula.get());
            horario.setInicio(inicio);
            horario.setFin(fin);
            horarioRepository.save(horario);
        }
    }
}
