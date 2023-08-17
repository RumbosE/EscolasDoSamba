package com.example.escolasdosamba.dto.escuela

import com.example.escolasdosamba.dto.color.ColorDto
import com.example.escolasdosamba.dto.premio.PremioEspecialDto
import com.example.escolasdosamba.dto.titulo.TituloDto
import java.util.Date

data class EscuelaDto(
        val id: Long? = null,
        val name: String,
        val history: String,
        val foundationDate: Date,
        val address: String,
        val state: String?,
        val region: String?,
        val phones: List<String>?,
        val titles: List<TituloDto>?,
        val colors: List<ColorDto>?,
        val premios: List<PremioEspecialDto>?

)

data class EscuelaSummaryInfoDto(
        val id: Long,
        val name: String,
        val placeName: String?,
        val placeFatherName: String?
)
