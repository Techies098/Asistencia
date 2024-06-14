package univsys.asistenciadocente.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.models.CarreraEntity;
import univsys.asistenciadocente.models.CarreraMateriaEntity;
import univsys.asistenciadocente.models.FacultadEntity;
import univsys.asistenciadocente.models.MateriaEntity;
import univsys.asistenciadocente.repositories.CarreraMateriaRepository;
import univsys.asistenciadocente.repositories.CarreraRepository;
import univsys.asistenciadocente.repositories.FacultadRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/carrera")
public class CarreraController {
    @Autowired
    private FacultadRepository facultadRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private CarreraMateriaRepository carreraMateriaRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<CarreraEntity> carreras = (List<CarreraEntity>) carreraRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de carreras");
        response.put("fecha", LocalDate.now());
        response.put("data", carreras);
        return ResponseEntity.ok(response);
    }

    @Autowired
    public CarreraController(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody CarreraEntity carrera) {
        carreraRepository.save(carrera);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrera);
    }
    /*
        {
            "name": "Ingeniería en Sistemas",
            "facultad": {
                "id": 1
                }
        }
    */
    @GetMapping("/{sigla}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable String sigla) {
        CarreraEntity carrera = carreraRepository.findBySigla(sigla);
        if (carrera != null) {
            List<CarreraMateriaEntity> carreraMaterias = carreraMateriaRepository.findByCarrera(carrera);
            List<Map<String, Object>> materiasConNivel = carreraMaterias.stream()
                    .map(cm -> {
                        Map<String, Object> materiaInfo = new HashMap<>();
                        materiaInfo.put("materia", cm.getMateria());
                        materiaInfo.put("nivel", cm.getNivel());
                        return materiaInfo;
                    })
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("status code", "200");
            response.put("mensaje", "Detalles de la carrera y sus materias");
            response.put("fecha", LocalDate.now());
            response.put("carrera", carrera);
            response.put("materias", materiasConNivel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

