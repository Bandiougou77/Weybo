package org.ldv.weybo.model.entity


import jakarta.persistence.*

@Entity
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var nom: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    var description: String = "",

    @Column(nullable = false)
    var prix: Double = 0.0,

    @Column(nullable = false)
    var stock: Int = 0,

    @Column(nullable = false)
    var image: String = "",

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    var categorie: Categorie? = null
)





