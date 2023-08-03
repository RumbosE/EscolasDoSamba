package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Titulo
import com.example.escolasdosamba.dto.titulo.TituloDto

fun Titulo.toDto() = TituloDto(
    year = getYear(),
    monto = monto,
    grupo = grupo
)