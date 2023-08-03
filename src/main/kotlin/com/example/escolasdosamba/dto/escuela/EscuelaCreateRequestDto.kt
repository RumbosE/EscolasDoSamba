package com.example.escolasdosamba.dto.escuela

import java.util.*

data class EscuelaCreateRequestDto(
    val name: String?,
    val history: String?,
    val foundationDate: Date?,
    val address: String?,
    val lugarId: Long? = null
)
