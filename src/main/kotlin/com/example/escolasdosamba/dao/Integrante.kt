package com.example.escolasdosamba.dao

import jakarta.persistence.*
import java.sql.Date

@SequenceGenerator(name = "seq_integrante", sequenceName = "erl_integrante_id_seq", allocationSize = 1)

@Entity
@Table(name = "erl_integrante")
data class Integrante(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_integrante")
    var id: Long? = null,

    @Column(name="primer_nombre", length = 15)
    var firstName: String,

    @Column(name="segundo_nombre", length = 15)
    var secondName: String?,

    @Column(name="primer_apellido", length = 15)
    var firstSurname: String,

    @Column(name="segundo_apellido", length = 15)
    var secondSurname: String?,

    @Column(name="fecha_nacimiento")
    var birthDate: Date,

    @Column(name="nacionalidad", length = 15)
    var nationality: String,

    @Column(name="doc_identidad", length = 20)
    var identityDocument: String?,

    @Column(name="apodo", length = 25)
    var nickname: String? = null,

    @OneToMany(mappedBy = "integrantePeriodoActivo")
    var periodosActivos: MutableList<PeriodoActivo> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name= "erl_i_h",
        joinColumns = [JoinColumn(name="id_integrante")],
        inverseJoinColumns = [JoinColumn(name="id_habilidad")]
    )
    var ability : MutableList<Habilidad> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name= "erl_parentesco",
        joinColumns = [JoinColumn(name="id_integrante1")],
        inverseJoinColumns = [JoinColumn(name="id_integrante2")]
    )
    var parentescos: MutableList<Integrante> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name= "erl_parentesco",
        joinColumns = [JoinColumn(name="id_integrante2")],
        inverseJoinColumns = [JoinColumn(name="id_integrante1")]
    )
    var parentescos2: MutableList<Integrante> = mutableListOf(),


)
