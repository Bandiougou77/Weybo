package org.ldv.weybo.model.service

import org.ldv.weybo.model.dao.RoleDAO
import org.ldv.weybo.model.dao.UtilisateurDAO
import org.ldv.weybo.model.entity.Role
import org.ldv.weybo.model.entity.Utilisateur
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val roleDAO: RoleDAO,
    private val utilisateurDAO: UtilisateurDAO,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        if (roleDAO.count() == 0L) {
            val roleAdmin = roleDAO.save(Role(nom = "ADMIN"))
            val roleClient = roleDAO.save(Role(nom = "CLIENT"))

            val admin = Utilisateur(
                nom = "Admin",
                prenom = "Super",
                email = "admin@weybo.com",
                motDePasse = passwordEncoder.encode("admin123"),
                role = roleAdmin
            )
            utilisateurDAO.save(admin)

            val client = Utilisateur(
                nom = "Client",
                prenom = "Normal",
                email = "client@weybo.com",
                motDePasse = passwordEncoder.encode("client123"),
                role = roleClient
            )
            utilisateurDAO.save(client)

            println("✔ Utilisateurs & rôles générés avec succès")
        }
    }
}

