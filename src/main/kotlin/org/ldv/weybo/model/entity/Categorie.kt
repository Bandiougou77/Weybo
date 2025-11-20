package org.ldv.weybo.model.entity

import jakarta.persistence.*

@Entity
data class Categorie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var nom: String = "",

    @Column(length = 1000)
    var description: String = ""
)



