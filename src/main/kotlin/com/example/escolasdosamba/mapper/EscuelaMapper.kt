package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.*
import com.example.escolasdosamba.dto.color.ColorDto
import com.example.escolasdosamba.dto.escuela.EscuelaCreateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelaDto
import com.example.escolasdosamba.dto.escuela.EscuelaSummaryInfoDto

fun Escuela.toDto() = EscuelaDto(
    id = id,
    name = name,
    history = history,
    foundationDate = foundationDate,
    address = address,
    state = place?.name,
    region = place?.father?.name,
    phones = telefonos.map(Telefono::toString),
    titles = titulos?.map(Titulo::toDto),
    colors = colors?.map(EC::toDto),
    premios = premios?.map(Ganador::toDto)

)

fun Escuela.toSummaryDto() = EscuelaSummaryInfoDto(
    id = id!!,
    name = name,
    placeName = place.name,
    placeFatherName = place.father?.name
)

fun EscuelaCreateRequestDto.toDao(lugar: Lugar) = Escuela(
    name = name?:throw IllegalArgumentException("El nombre no puede ser nulo"),
    history = history ?: throw IllegalArgumentException("La historia no puede ser nula"),
    foundationDate = foundationDate ?: throw IllegalArgumentException("La fecha de fundación no puede ser nula"),
    address = address ?: throw IllegalArgumentException("La dirección no puede ser nula"),
    place = lugar
)