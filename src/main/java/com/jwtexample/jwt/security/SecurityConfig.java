package com.jwtexample.jwt.security;

import com.jwtexample.jwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {



    private final JwtRequestFilter jwtRequestFilter;
    @Autowired
    private UserDetailsService userService;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF devre dışı bırakılıyor çünkü JWT zaten güvenli bir kimlik doğrulama yöntemi sağlar.
        // Session kullanımı da JWT ile gereksiz olduğu için STATELESS olarak ayarlandı.
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // "/authenticate" gibi endpoint'ler herkesin erişimine açık.
                       // .requestMatchers("/api/authenticate").permitAll()
                        .requestMatchers("/api/authenticate/**","/user/save/**").permitAll()
                        // Diğer tüm istekler kimlik doğrulaması gerektiriyor.
                        .anyRequest().authenticated()
                );

                // HTTP oturumunun (session) durumsuz (stateless) olduğunu belirtir. JWT'de oturum yönetimi yoktur.
                http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // JWT doğrulama filtresini ekleyin, böylece her istekte token kontrol edilir.
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    // AuthenticationManager için bir bean oluşturuyoruz. Bu, JWT doğrulama işlemlerinde gereklidir.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Şifreleri encode etmek için PasswordEncoder bean'i kullanılır (örneğin, BCrypt).
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
