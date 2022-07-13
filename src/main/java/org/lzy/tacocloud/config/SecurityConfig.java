package org.lzy.tacocloud.config;

import org.lzy.tacocloud.data.UserRepository;
import org.lzy.tacocloud.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> {
            Optional<User> user = repo.findByUsername(username);
            return user.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .mvcMatchers("/design", "/order").hasRole("USER")
                        .mvcMatchers("/db/**").access(((authentication, request) ->
                                Optional.of(hasRole("ADMIN").check(authentication, request))
                                        .filter(decision -> !decision.isGranted())
                                        .orElseGet(() -> hasRole("DBA").check(authentication, request))))
                        .antMatchers("/**").permitAll())
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design", false).and()
                .logout()
                    .logoutSuccessUrl("/").and()
                .build();
    }
}
