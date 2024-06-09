package univsys.asistenciadocente.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.models.CarreraEntity;
import univsys.asistenciadocente.models.FacultadEntity;
import univsys.asistenciadocente.repositories.CarreraRepository;
import univsys.asistenciadocente.repositories.FacultadRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrera")
public class CarreraController {
    @Autowired
    private FacultadRepository facultadRepository;
    @Autowired
    private CarreraRepository carreraRepository;

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
    public ResponseEntity<?> crearCarrera(@RequestBody CarreraEntity carrera) {
        Long facultadId = carrera.getFacultad().getId();
        Optional<FacultadEntity> facultad = facultadRepository.findById(facultadId);
        if (!facultad.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La facultad con ID " + facultadId + " no existe.");
        }
        carrera.setFacultad(facultad.get());
        CarreraEntity nuevaCarrera = carreraRepository.save(carrera);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCarrera);
    }
    /*
        {
            "name": "Ingeniería en Sistemas",
            "facultad": {
                "id": 1
                }
        }
    */
}

