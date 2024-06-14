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
@RequestMapping("/api/modulo")
public class ModuloController {
    @Autowired
    ModuloRepository moduloRepository;
    @Autowired
    AulaRepository aulaRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<ModuloEntity> modulos = (List<ModuloEntity>) moduloRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de modulos");
        response.put("fecha", LocalDate.now());
        response.put("data", modulos);
        return ResponseEntity.ok(response);
    }
    @Transactional
    @PostMapping("/store")
    public ResponseEntity<ModuloEntity> store(@RequestBody ModuloEntity modulo) {
        moduloRepository.save(modulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(modulo);
    }/*{
    "numero": 555
    }*/
    @GetMapping("/{moduloId}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Long moduloId) {
        Map<String, Object> response = new HashMap<>();
            // buscar el modulo con el moduloId
        Optional<ModuloEntity> modulos = moduloRepository.findById(moduloId);
        if (modulos.isPresent()) {
            ModuloEntity modulo = modulos.get();
            // Buscar aulas del módulo por su moduloID
            List<AulaEntity> aulas = aulaRepository.findByModuloId(moduloId);
            response.put("status code", "200");
            response.put("mensaje", "Detalles del modulo y sus aulas");
            response.put("fecha", LocalDate.now());
            response.put("aulas", aulas);
            response.put("modulo", modulo);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
