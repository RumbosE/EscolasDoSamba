package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Escuela
import com.example.escolasdosamba.dao.Titulo
import com.example.escolasdosamba.dao.TituloId
import com.example.escolasdosamba.dto.titulo.TituloCreateRequestDto
import com.example.escolasdosamba.dto.titulo.TituloDto

fun Titulo.toDto() = TituloDto(
    year = id.year,
    monto = monto,
    grupo = grupo
)

fun TituloCreateRequestDto.toDao(escuela: Escuela) = Titulo (
    id = TituloId(
        idEscuela = escuela.id ?: throw IllegalArgumentException("El id de la escuela no puede ser nulo"),
        year = year ?: throw IllegalArgumentException("El año no puede ser nulo")
        ),
    monto = monto,
    grupo = grupo

)