package univsys.asistenciadocente.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.models.MateriaEntity;
import univsys.asistenciadocente.repositories.CarreraRepository;
import univsys.asistenciadocente.repositories.MateriaRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<MateriaEntity> materias = (List<MateriaEntity>) materiaRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de materias");
        response.put("fecha", LocalDate.now());
        response.put("data", materias);
        return ResponseEntity.ok(response);
    }
    @Autowired
    public MateriaController(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Transactional
    @PostMapping("/materia/store")
    public ResponseEntity<?> store(@RequestBody MateriaEntity materia) {
        materiaRepository.save(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(materia);
    }

}
