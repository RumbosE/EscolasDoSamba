package com.example.escolasdosamba.dto.titulo

import java.sql.Date


data class TituloCreateRequestDto (
    var idEscuela: Long?,
    val year: Date?,
    val monto: Float?,
    val grupo: String?
)