package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.*
import com.example.escolasdosamba.dto.premio.GanadorEscuelaDto
import com.example.escolasdosamba.dto.premio.PremioEspecialCreateRequestDto
import com.example.escolasdosamba.dto.premio.PremioEspecialDto

fun Ganador.toDto() = PremioEspecialDto(
    id = id.idPremio,
    name = premioGanador.name,
    type = premioGanador.type,
    description = premioGanador.description,
    place = premioGanador.place.name,
    year = id.year
)

fun PremioEspecial.toDto() = PremioEspecialDto(
    id = id,
    name = name,
    type = type,
    description = description,
    place = place.name,
    year = null
)

fun PremioEspecialCreateRequestDto.toDao(lugar: Lugar) = PremioEspecial(
    name = name?: throw Exception("Name is required"),
    type = type?: throw Exception("Type is required"),
    description = description,
    place = lugar
)

fun GanadorEscuelaDto.toDao( premio: PremioEspecial, escuela: Escuela) = Ganador(
    id = GanadorId(
        idPremio = idPremio?: throw Exception("IdPremio is required"),
        year = year?: throw Exception("Year is required")
    ),
    escuelaPremio = escuela,
    premioGanador = premio
)

