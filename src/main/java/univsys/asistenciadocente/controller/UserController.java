package univsys.asistenciadocente.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.LoginRequest;
import univsys.asistenciadocente.models.UserEntity;
import univsys.asistenciadocente.repositories.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Transactional
    @PostMapping("/store")
    public ResponseEntity<UserEntity> store(@RequestBody UserEntity user) {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    /* {
            "email": "prueba@gmail.com",
            "username": "prueba",
            "password": "0000",
            "rol": "USER"
        }*/
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            response.put("status code", "200");
            response.put("mensaje", "Detalles del usuario");
            response.put("fecha", LocalDate.now());
            response.put("data", user);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
}
