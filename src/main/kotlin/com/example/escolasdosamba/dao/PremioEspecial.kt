package com.example.escolasdosamba.dao

import jakarta.persistence.*

@SequenceGenerator(name = "premio_generator", sequenceName = "erl_premio_especial_id_seq", allocationSize = 1)

@Entity
@Table(name = "erl_premio_especial")
data class PremioEspecial(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "premio_generator")
    var id: Long?= null,

    @Column(name = "nombre", length = 20)
    var name: String,

    @Column(name= "tipo")
    var type: Char,

    @Column(name= "descripcion", length = 150)
    var description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_lugar", nullable = false)
    var place: Lugar,

)
