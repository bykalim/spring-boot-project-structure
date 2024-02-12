package org.example.basiccrudkotlin.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "GENRES")
open class Genre {
    @Id
    @Column(name = "ID", nullable = false)
    open var id: Int? = null

    @Column(name = "GENRE_NAME", nullable = false, length = 100)
    open var genreName: String? = null
}