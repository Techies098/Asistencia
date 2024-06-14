package univsys.asistenciadocente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
//import univsys.asistenciadocente.service.UserDetailsServiceImpl;

import java.util.Arrays;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/v1/index2","/createUser").permitAll() //acceso público
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Requiere rol "ADMIN" para /admin/**
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                //.formLogin(form -> form.successHandler(successHandler()) )
                .formLogin(Customizer.withDefaults())
                .sessionManagement((sessionManagement)-> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                /*.invalidSessionUrl("/login")
                                .maximumSessions(1)
                                .expiredUrl("/login")*/
                )
                //.httpBasic(Customizer.withDefaults()) // Configuración para autenticación básica
                .build();
    }
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> response.sendRedirect("/v1/index"));
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
    }*/
    /*@Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManager XXX = new AuthenticationManager() {}
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
            .build();
    }*/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return daoAuthenticationProvider;
//    }
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return new ProviderManager(Arrays.asList(daoAuthenticationProvider));
//    }

}