package org.ldv.weybo.model.entity

import jakarta.persistence.*

@Entity
data class Utilisateur(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var nom: String = "",

    @Column(nullable = false)
    var prenom: String = "",

    @Column(nullable = false, unique = true)
    var email: String = "",

    @Column(nullable = false)
    var motDePasse: String = "",

    @ManyToOne
    @JoinColumn(name = "role_id")
    var role: Role? = null
)

