package univsys.asistenciadocente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.models.ModuloEntity;
import univsys.asistenciadocente.models.UserEntity;
import univsys.asistenciadocente.repositories.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        List<UserEntity> usuarios = (List<UserEntity>) userRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status code", "200");
        response.put("mensaje", "lista de Usuarios");
        response.put("fecha", LocalDate.now());
        response.put("data", usuarios);
        return ResponseEntity.ok(response);
    }

}
