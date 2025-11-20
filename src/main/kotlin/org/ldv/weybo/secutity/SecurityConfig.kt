package org.ldv.weybo.secutity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.csrf { it.disable() }

            .authorizeHttpRequests {
                it.requestMatchers("/", "/accueil", "/css/**", "/images/**", "/js/**").permitAll()
                it.requestMatchers("/admin/**").hasRole("ADMIN")
                it.requestMatchers("/client/**").hasRole("CLIENT")
                it.anyRequest().authenticated()
            }

            .formLogin {
                it.loginPage("/login")
                    .defaultSuccessUrl("/profil")
                    .failureUrl("/login?error=true")
                    .permitAll()
            }

            .logout {
                it.logoutUrl("/logout")
                    .permitAll()
            }

        return http.build()
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}

