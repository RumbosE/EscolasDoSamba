package com.example.escolasdosamba.dao

import jakarta.persistence.*

@Entity
@Table(name = "erl_lugar")
data class Lugar(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator_lugar")
        @SequenceGenerator(name = "sequence_generator_lugar", sequenceName = "erl_lugar_id_seq", allocationSize = 1)
        val id:Long? = null,

        @Column(name = "nombre", length = 15)
        val name:String,

        @Column(name = "tipo", length = 3)
        val type:String,

        @Column(name = "padre_id")
        val fatherId:Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "padre_id", referencedColumnName = "id")
        val father:Lugar? = null,

        @OneToMany(mappedBy = "fatherId", fetch = FetchType.LAZY)
        val children:List<Lugar>? = null,

)
