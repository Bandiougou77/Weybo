package org.ldv.weybo.model.entity


import jakarta.persistence.*

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    var nom: String = ""
)

