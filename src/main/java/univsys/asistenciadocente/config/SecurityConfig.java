package univsys.asistenciadocente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/v1/index2").permitAll() //acceso público
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Requiere rol "ADMIN" para /admin/**
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .successHandler(successHandler())
                )
                .sessionManagement((sessionManagement)-> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                .invalidSessionUrl("/login")
                                .maximumSessions(1)
                                .expiredUrl("/login")
                )

                .httpBasic(Customizer.withDefaults()) // Configuración para autenticación básica
                .build();
    }
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> response.sendRedirect("/v1/index"));
    }
}
