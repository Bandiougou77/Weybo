package org.ldv.weybo.model.service

import org.ldv.weybo.model.dao.UtilisateurDAO
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService(
    private val utilisateurDAO: UtilisateurDAO
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = utilisateurDAO.findByEmail(username)
            ?: throw UsernameNotFoundException("Utilisateur non trouvé")

        val role = user.role?.nom ?: "CLIENT"

        return User
            .withUsername(user.email)
            .password(user.motDePasse)
            .roles(role)
            .build()
    }
}



