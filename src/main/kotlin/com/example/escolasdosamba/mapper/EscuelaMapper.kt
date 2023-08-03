package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Escuela
import com.example.escolasdosamba.dao.Lugar
import com.example.escolasdosamba.dao.Telefono
import com.example.escolasdosamba.dao.Titulo
import com.example.escolasdosamba.dto.escuela.EscuelaCreateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelaDto
import com.example.escolasdosamba.dto.escuela.EscuelaSummaryInfoDto

fun Escuela.toDto() = EscuelaDto(
    id = id,
    name = name,
    history = history,
    foundationDate = foundationDate,
    address = address,
    placeName = place?.name,
    placeFatherName = place?.father?.name,
    phones = telefonos.map(Telefono::toString),
    titles = titulos.map(Titulo::toDto)
    //titles = titulos.map { it.toDto() }

)

fun Escuela.toSummaryDto() = EscuelaSummaryInfoDto(
    id = id!!,
    name = name,
    phones = telefonos.map(Telefono::toString)
)

fun EscuelaCreateRequestDto.toDao(lugar: Lugar) = Escuela(
    name = name?:throw IllegalArgumentException("El nombre no puede ser nulo"),
    history = history ?: throw IllegalArgumentException("La historia no puede ser nula"),
    foundationDate = foundationDate ?: throw IllegalArgumentException("La fecha de fundación no puede ser nula"),
    address = address ?: throw IllegalArgumentException("La dirección no puede ser nula"),
    place = lugar
)