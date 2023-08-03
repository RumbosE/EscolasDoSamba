package com.example.escolasdosamba.dto.escuela

import java.util.*

data class EscuelaUpdateRequestDto(
        val name: String? = null,
        val history: String? = null,
        val foundationDate: Date? = null,
        val address: String? = null,
        val lugar: Long? = null
)
