package univsys.asistenciadocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.models.CarreraEntity;
import univsys.asistenciadocente.models.ModuloEntity;
import univsys.asistenciadocente.repositories.ModuloRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/modulo")
public class ModuloController {
    @Autowired
    ModuloRepository moduloRepository;

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
}
