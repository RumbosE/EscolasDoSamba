package com.example.escolasdosamba.dto.titulo

import java.sql.Date
import java.time.Year

data class TituloDto(
    val year: Date?,
    val monto: Float?,
    val grupo: String?,
    val fecha: Int?
)
