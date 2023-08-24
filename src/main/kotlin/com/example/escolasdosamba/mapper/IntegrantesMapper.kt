package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Integrante
import com.example.escolasdosamba.dao.Parentesco
import com.example.escolasdosamba.dao.PeriodoActivo
import com.example.escolasdosamba.dao.PeriodoActivoId
import com.example.escolasdosamba.dto.habilidad.HabilidadDto
import com.example.escolasdosamba.dto.integrante.*

fun PeriodoActivo.toSumaryDto() = SumaryInfoIntegranteDto(
    id = integrantePeriodoActivo?.id!!,
    firstName = integrantePeriodoActivo.firstName,
    firstSurname = integrantePeriodoActivo.firstSurname,
    registrationDate = id.registrationDate,
    endDate = endDate,
    authority = authority
)

fun PeriodoActivo.toEscuelaInfoDto() = EscuelasInfoIntegranteDto(
    id = escuelaPeriodoActivo?.id!!,
    school = escuelaPeriodoActivo.name,
    registrationDate = id.registrationDate,
    endDate = endDate,
    authority = authority
)


fun Integrante.toDto() = IntegranteDto(
    id = id!!,
    firstName = firstName,
    secondName = secondName,
    firstSurname = firstSurname,
    secondSurname = secondSurname,
    birthDate = birthDate,
    nationality = nationality,
    identityDocument = identityDocument,
    nickname = nickname,
    periodoEscuelas = periodosActivos.map(PeriodoActivo::toEscuelaInfoDto).toMutableList(),
    ability = ability.map {
        HabilidadDto(
            id = it.id!!,
            name = it.name

        )
    }.toMutableList(),

    relationship = parentescos1.map(Parentesco::toParentescoDto).toMutableList().apply {
        addAll(parentescos2.map(Parentesco::toParentescoDto))
    },

)

fun Parentesco.toParentescoDto() = ParentescoIntegranteDto(
    id = integrante2.id!!,
    firstName = integrante2.firstName,
    firstSurname = integrante2.firstSurname,
    school = integrante2.periodosActivos.first().escuelaPeriodoActivo?.name ?: "No pertenece a ninguna escuela",
    relationship = relation
)

fun IntegranteCreateRequestDto.toDao() = Integrante(

    firstName = firstName ?: throw IllegalArgumentException("El Primernombre es requerido"),
    secondName = secondName,
    firstSurname = firstSurname ?: throw IllegalArgumentException("El primer apellido es requerido"),
    secondSurname = secondSurname,
    birthDate = birthDate ?: throw IllegalArgumentException("La fecha de nacimiento no puede ser nula"),
    nationality = nationality ?: throw IllegalArgumentException("La nacionalidad no puede ser nula"),
    identityDocument = identityDocument,
    nickname = nickname,

)


