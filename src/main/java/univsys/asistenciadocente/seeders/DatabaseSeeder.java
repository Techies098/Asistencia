package univsys.asistenciadocente.seeders;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import univsys.asistenciadocente.models.FacultadEntity;
import univsys.asistenciadocente.models.RoleEntity;
import univsys.asistenciadocente.models.UserEntity;
import univsys.asistenciadocente.repositories.FacultadRepository;
import univsys.asistenciadocente.repositories.RoleRepository;
import univsys.asistenciadocente.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseSeeder {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FacultadRepository facultadRepository;

    @EventListener
    @Transactional
    public void seed(ContextRefreshedEvent event) {
        seedFacultades();
        seedRoleTable();
        seedUsersTable();
    }
    public void seedFacultades() {
        List<String> nombresFacultades = new ArrayList<>();
        nombresFacultades.add("Contaduria");
        nombresFacultades.add("Ciencias Exactas");
        nombresFacultades.add("Medicina");
        nombresFacultades.add("Humanidades");
        nombresFacultades.add("Ciencias de la Computacion");
        nombresFacultades.add("Derecho");
        nombresFacultades.add("Veterinaria");
        for (String nombre : nombresFacultades) {
            FacultadEntity facultad = new FacultadEntity();
            facultad.setName(nombre);
            facultadRepository.save(facultad);
        }
    }

    private void seedRoleTable() {
        if (roleRepository.findByName("ADMIN") == null) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }
        if (roleRepository.findByName("USER") == null) {
            RoleEntity userRole = new RoleEntity();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }
        if (roleRepository.findByName("INVITED") == null) {
            RoleEntity invRole = new RoleEntity();
            invRole.setName("INVITED");
            roleRepository.save(invRole);
        }
    }

    private void seedUsersTable() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("0000"));
            user.setEmail("admin@gmail.com");
            RoleEntity role = roleRepository.findByName("ADMIN");
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user.setActive(true);
            userRepository.save(user);
        }
        if (userRepository.findByUsername("docente").isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername("docente");
            user.setPassword(passwordEncoder.encode("0000"));
            user.setEmail("docente@gmail.com");
            RoleEntity role = roleRepository.findByName("USER");
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user.setActive(true);
            userRepository.save(user);
        }
        if (userRepository.findByUsername("Angy").isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername("Angy");
            user.setPassword(passwordEncoder.encode("0000"));
            user.setEmail("Angy@gmail.com");
            RoleEntity role = roleRepository.findByName("USER");
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user.setActive(true);
            userRepository.save(user);
        }

    }

}

