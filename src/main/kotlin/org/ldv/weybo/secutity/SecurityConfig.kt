package org.ldv.weybo.secutity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class SecurityConfig(
    private val myUserDetailsService: UserDetailsService
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()


    @Bean
    fun authenticationManager(config: AuthenticationConfiguration):
            AuthenticationManager {
        return config.authenticationManager
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/", "/home", "/shop", "/cart", "/register", "/login", "/css/**", "/images/**")
                    .permitAll()

                it.requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                it.anyRequest().authenticated()
            }
            .formLogin {
                it.loginPage("/login")
                it.defaultSuccessUrl("/profil", true)
                it.failureUrl("/login?error=true")
                it.permitAll()
            }
            .logout {
                it.logoutUrl("/logout")
                it.logoutSuccessUrl("/login")
            }
            .csrf { it.disable() }

        return http.build()
    }
}


