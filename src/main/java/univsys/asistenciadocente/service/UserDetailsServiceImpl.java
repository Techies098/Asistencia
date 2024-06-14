package univsys.asistenciadocente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import univsys.asistenciadocente.models.UserEntity;
import univsys.asistenciadocente.repositories.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;
/*
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = (UserEntity) userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("El usuario"+ username + "no existe"));
        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE".concat(role.getName().name())))
                .collect(Collectors.toSet());
        return new User(userEntity.getUsername(),userEntity.getPassword()
                ,true,true,true,true
                ,authorities);
    }
}*/
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));
//        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                .collect(Collectors.toSet());
//        return new User(userEntity.getUsername(), userEntity.getPassword(),
//                userEntity.isActive(), true, true, true, authorities);
 //   }
//}
//

