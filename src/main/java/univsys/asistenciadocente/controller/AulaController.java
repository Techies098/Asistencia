package univsys.asistenciadocente.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.models.AulaEntity;
import univsys.asistenciadocente.models.ModuloEntity;
import univsys.asistenciadocente.repositories.AulaRepository;
import univsys.asistenciadocente.repositories.ModuloRepository;

import java.time.LocalDate;
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
    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<AulaEntity> carreras = (List<AulaEntity>) aulaRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de aulas");
        response.put("fecha", LocalDate.now());
        response.put("data", carreras);
        return ResponseEntity.ok(response);
    }
    @Autowired
    public AulaController(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }
    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody AulaEntity aula) {
        aulaRepository.save(aula);
        return ResponseEntity.status(HttpStatus.CREATED).body(aula);
    }/*
    {
        "numero": 1,
        "modulo":   {
                    "id": 1
                    }
    }
    */
}
