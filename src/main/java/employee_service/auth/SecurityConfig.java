package employee_service.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                	    .requestMatchers(
                	        "/auth/register",
                	        "/auth/login",
                	        "/auth/forgot-password",
                	        "/auth/reset-password",
                	        "/swagger-ui/**",
                	        "/v3/api-docs/**"
                	    ).permitAll()

                	    .requestMatchers(HttpMethod.GET, "/employees", "/employees/**")
                	    .hasAnyRole("ADMIN", "USER")
                	    

                	    .requestMatchers(HttpMethod.POST, "/employees/**")
                	    .hasRole("ADMIN")

                	    .requestMatchers(HttpMethod.PUT, "/employees/**")
                	    .hasRole("ADMIN")

                	    .requestMatchers(HttpMethod.DELETE, "/employees/**")
                	    .hasRole("ADMIN")
                	    .requestMatchers("/dashboard/**").hasRole("ADMIN")

                	    .anyRequest().authenticated()
                	)
                
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}