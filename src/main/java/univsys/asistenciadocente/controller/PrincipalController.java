package univsys.asistenciadocente.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import univsys.asistenciadocente.controller.Request.CreateUserDTO;
import univsys.asistenciadocente.models.RoleEntity;
import univsys.asistenciadocente.models.UserEntity;
import univsys.asistenciadocente.repositories.RoleRepository;
import univsys.asistenciadocente.repositories.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/hello")
    public String hello(){
        return "Hello World not secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hello World secured";
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        //creamos el set donde van los roles
        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(roleName -> {
                    RoleEntity role = roleRepository.findByName(roleName);
                    if (role == null) {
                        throw new RuntimeException("El rol '" + roleName + "' no existe.");
                    }
                    return role;
                })
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .active(true)
                .roles(roles)
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));
        return "User deleted".concat(id);
    }
}
