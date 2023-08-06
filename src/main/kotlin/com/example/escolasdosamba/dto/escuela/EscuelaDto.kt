package com.example.escolasdosamba.dto.escuela

import com.example.escolasdosamba.dao.Color
import com.example.escolasdosamba.dto.color.ColorDto
import com.example.escolasdosamba.dto.titulo.TituloDto
import java.util.Date

data class EscuelaDto(
        val id: Long? = null,
        val name: String,
        val history: String,
        val foundationDate: Date,
        val address: String,
        val placeName: String?,
        val placeFatherName: String?,
        val phones: List<String>,
        val titles: List<TituloDto>?,
        val colors: List<ColorDto>?
)

data class EscuelaSummaryInfoDto(
        val id: Long,
        val name: String,
        val placeName: String?,
        val placeFatherName: String?
)
