package org.example.basiccrudkotlin.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "USERS")
open class User {
    @Id
    @Column(name = "ID", nullable = false)
    open var id: Long? = null

    @Column(name = "USERNAME", nullable = false, length = 50)
    open var username: String? = null

    @Column(name = "EMAIL", nullable = false)
    open var email: String? = null

    @Column(name = "PASSWORD_HASH", nullable = false)
    open var passwordHash: String? = null
}