package univsys.asistenciadocente.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.models.GrupoEntity;
import univsys.asistenciadocente.repositories.GrupoRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/grupo")
public class GrupoController {
    private final GrupoRepository grupoRepository;

    public GrupoController(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<GrupoEntity> grupos = (List<GrupoEntity>) grupoRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de Grupos");
        response.put("fecha", LocalDate.now());
        response.put("data", grupos);
        return ResponseEntity.ok(response);
    }


    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody GrupoEntity grup) {
        grupoRepository.save(grup);
        return ResponseEntity.status(HttpStatus.CREATED).body(grup);
    }
}
