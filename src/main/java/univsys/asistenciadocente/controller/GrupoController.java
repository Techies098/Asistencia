package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.GrupoRequest;
import univsys.asistenciadocente.models.GrupoEntity;
import univsys.asistenciadocente.models.HorarioEntity;
import univsys.asistenciadocente.models.MateriaEntity;
import univsys.asistenciadocente.models.UserEntity;
import univsys.asistenciadocente.repositories.GrupoRepository;
import univsys.asistenciadocente.repositories.HorarioRepository;
import univsys.asistenciadocente.repositories.MateriaRepository;
import univsys.asistenciadocente.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/grupo")
public class GrupoController {
    private final GrupoRepository grupoRepository;
    private final MateriaRepository materiaRepository;
    private final UserRepository userRepository;
    private final HorarioRepository horarioRepository;

    public GrupoController(GrupoRepository grupoRepository, MateriaRepository materiaRepository, UserRepository userRepository, HorarioRepository horarioRepository) {
        this.grupoRepository = grupoRepository;
        this.materiaRepository = materiaRepository;
        this.userRepository = userRepository;
        this.horarioRepository = horarioRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        Iterable<GrupoEntity> grupos = grupoRepository.findAll();
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (GrupoEntity grupo : grupos) {//cada pate de data tendra grupo y horarios
            List<HorarioEntity> horarios = horarioRepository.findByGrupoId(grupo.getId());
            Map<String, Object> groupData = new HashMap<>();
            groupData.put("grupo", grupo);
            groupData.put("horarios", horarios);
            dataList.add(groupData);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de Grupos");
        response.put("fecha", LocalDate.now());
        response.put("data", dataList);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody GrupoRequest grupoRequest) {
        MateriaEntity materia = materiaRepository.findById(grupoRequest.getMateria())
                .orElseThrow(() -> new EntityNotFoundException("Materia not found"));
        UserEntity user = userRepository.findById(grupoRequest.getUser())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        GrupoEntity grup = new GrupoEntity();
        grup.setNombre(grupoRequest.getNombre());
        grup.setPeriodo(grupoRequest.getPeriodo());
        grup.setMateria(materia);
        grup.setUser(user);
        grupoRepository.save(grup);
        return ResponseEntity.status(HttpStatus.CREATED).body(grup);
    }

    public void asignargrupo(Long idgrupo, Long idhorario) {
        HorarioEntity horario = horarioRepository.findById(idhorario).orElseThrow(() -> new RuntimeException("Horario not found"));
        GrupoEntity grupo = grupoRepository.findById(idgrupo).orElseThrow(() -> new RuntimeException("Grupo not found"));
        horario.setGrupo(grupo);
        horarioRepository.save(horario);
    }

}
