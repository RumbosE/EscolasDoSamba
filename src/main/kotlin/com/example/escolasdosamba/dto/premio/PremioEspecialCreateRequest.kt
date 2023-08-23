package com.example.escolasdosamba.dto.premio

import java.sql.Date


data class PremioEspecialCreateRequestDto(

        val name: String?,

        val type: Char?,

        val description: String?,

        val lugarId: Long,

        val year: Date,

)

data class GanadorEscuelaDto(

        val idEscuela: Long,

        val year: Date,

        val idPremio: Long,
    )
