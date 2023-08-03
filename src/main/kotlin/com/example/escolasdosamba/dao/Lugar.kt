package com.example.escolasdosamba.dao

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "erl_lugar")
data class Lugar(

        @Id
        @Column(name="id")
        var id: Long? = null,

        @Column(name="nombre", length = 15)
        var name: String? = null,

        @Column(name="tipo", length = 3)
        var type: String? = null,

        @ManyToOne
        @JoinColumn(name="padre_id")
        var father: Lugar? = null,


)
