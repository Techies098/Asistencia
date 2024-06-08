package univsys.asistenciadocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.controller.DTO.FacultadDTO;
import univsys.asistenciadocente.models.FacultadEntity;
import univsys.asistenciadocente.repositories.FacultadRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/facultad")
public class FacultadController {
    @Autowired
    private FacultadRepository facultadRepository;
    /*@GetMapping()// se asume q es "/"
    Iterable<FacultadEntity> list() {
        return facultadRepository.findAll();
    }*/
    /*@GetMapping
    public List<FacultadDTO> list() {
        List<FacultadDTO> facultadesDTO = new ArrayList<>();
        List<FacultadEntity> facultades = (List<FacultadEntity>) facultadRepository.findAll();
        for (FacultadEntity facultad : facultades) {
            FacultadDTO dto = new FacultadDTO();
            dto.setId(facultad.getId());
            dto.setNombre(facultad.getName());
            facultadesDTO.add(dto);
        }

        return facultadesDTO;
    }
    @GetMapping
    public String list() {// Crear manualmente la cadena JSON con los datos de las facultades
        StringBuilder json = new StringBuilder("data[");
        for (FacultadEntity facultad : facultadRepository.findAll()) {
            json.append(facultad.toString());
        }
        json.append("]");
        return json.toString();
    }
    @GetMapping
    public ResponseEntity<List<FacultadEntity>> list() {
        List<FacultadEntity> facultades = (List<FacultadEntity>) facultadRepository.findAll();
        return ResponseEntity.ok(facultades);
    }*/

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<FacultadEntity> facultades = (List<FacultadEntity>) facultadRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de facultades");
        response.put("fecha", LocalDate.now());
        response.put("data", facultades);
        return ResponseEntity.ok(response);
    }





}
