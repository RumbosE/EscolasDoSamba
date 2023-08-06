package com.example.escolasdosamba.dao

import jakarta.persistence.*

@SequenceGenerator(
    name = "color_seq", sequenceName = "erl_color_principal_id_seq", allocationSize = 1
)

@Entity
@Table(name = "erl_color_principal")
data class Color(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="color_seq")
    var id: Long? = null,

    @Column(name="nombre")
    var name: String,

    @OneToMany(mappedBy="colorEC")
    var EC: List<EC>
)

