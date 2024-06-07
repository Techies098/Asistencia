package univsys.asistenciadocente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/v1/index2","/createUser").permitAll() //acceso público
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Requiere rol "ADMIN" para /admin/**
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .successHandler(successHandler())
                )
                .sessionManagement((sessionManagement)-> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                //.invalidSessionUrl("/login")
                                //.maximumSessions(1)
                                //.expiredUrl("/login")
                )
                //.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults()) // Configuración para autenticación básica
                .build();
    }
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> response.sendRedirect("/v1/index"));
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*
   UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User
                .withUsername("test2")
                .password("0000")
                .roles()
                .build());
        return manager;
    }
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder)
            .build();
    }
     */
}