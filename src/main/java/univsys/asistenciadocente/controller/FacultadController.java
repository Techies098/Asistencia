package univsys.asistenciadocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.models.FacultadEntity;
import univsys.asistenciadocente.repositories.FacultadRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/facultad")
public class FacultadController {
    @Autowired
    private FacultadRepository facultadRepository;
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
