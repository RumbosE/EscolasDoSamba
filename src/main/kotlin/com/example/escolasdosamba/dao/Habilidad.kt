package com.example.escolasdosamba.dao

import jakarta.persistence.*

@SequenceGenerator(name = "seq_habilidad", sequenceName = "erl_habilidad_id_seq", allocationSize = 1)

@Entity
@Table(name = "erl_habilidad")
data class Habilidad(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_habilidad")
    var id: Long? = null,

    @Column(name="nombre", length = 15)
    var name: String,

    @ManyToMany(mappedBy = "ability", fetch = FetchType.LAZY)
    var integrantes: MutableList<Integrante> = mutableListOf()

)
