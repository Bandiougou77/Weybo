package org.ldv.weybo.model.dao

import org.ldv.weybo.model.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository

interface UtilisateurDAO : JpaRepository<Utilisateur, Long> {
    fun findByEmail(email: String): Utilisateur?
}

