package univsys.asistenciadocente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.models.CarreraMateriaEntity;
import univsys.asistenciadocente.repositories.CarreraMateriaRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carrera_materia")
public class CarreraMateriaController {

    private final CarreraMateriaRepository carreraMateriaRepository;

    public CarreraMateriaController(CarreraMateriaRepository carreraMateriaRepository) {
        this.carreraMateriaRepository = carreraMateriaRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<CarreraMateriaEntity> carreraMaterias = (List<CarreraMateriaEntity>) carreraMateriaRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "Lista de relaciones carrera-materia");
        response.put("fecha", LocalDate.now());
        response.put("data", carreraMaterias);
        return ResponseEntity.ok(response);
    }
}
