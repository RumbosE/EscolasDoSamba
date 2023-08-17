package com.example.escolasdosamba.dto.premio

import java.sql.Date

data class PremioEspecialDto (

    val id: Long?,

    val name: String,

    val type: Char,

    val description: String?,

    val place: String?,

    val year: Date
)