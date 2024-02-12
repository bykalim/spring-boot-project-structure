package org.example.basiccrudkotlin.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ACTORS")
open class Actor {
    @Id
    @Column(name = "ID", nullable = false)
    open var id: Long? = null

    @Column(name = "NAME", nullable = false)
    open var name: String? = null
}