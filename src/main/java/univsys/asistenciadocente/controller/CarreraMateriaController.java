package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.AsignaRequest;
import univsys.asistenciadocente.models.CarreraEntity;
import univsys.asistenciadocente.models.CarreraMateriaEntity;
import univsys.asistenciadocente.models.MateriaEntity;
import univsys.asistenciadocente.repositories.CarreraMateriaRepository;
import univsys.asistenciadocente.repositories.CarreraRepository;
import univsys.asistenciadocente.repositories.MateriaRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/carrera_materia")
public class CarreraMateriaController {

    private final CarreraMateriaRepository carreraMateriaRepository;
    private final CarreraRepository carreraRepository;
    private final MateriaRepository materiaRepository;

    public CarreraMateriaController(CarreraMateriaRepository carreraMateriaRepository, CarreraRepository carreraRepository, MateriaRepository materiaRepository) {
        this.carreraMateriaRepository = carreraMateriaRepository;
        this.carreraRepository = carreraRepository;
        this.materiaRepository = materiaRepository;
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

    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody AsignaRequest req) {
        CarreraEntity carrera = carreraRepository.findById(req.getCarrera())
                .orElseThrow(() -> new EntityNotFoundException("carrera not found"));
        MateriaEntity materia = materiaRepository.findById(req.getMateria())
                .orElseThrow(() -> new EntityNotFoundException("materia not found"));
        CarreraMateriaEntity carreraMateria = new CarreraMateriaEntity();
        carreraMateria.setCarrera(carrera);
        carreraMateria.setMateria(materia);
        carreraMateria.setNivel(req.getNivel());
        carreraMateriaRepository.save(carreraMateria);
        return ResponseEntity.status(HttpStatus.CREATED).body(carreraMateria);
    }
}

