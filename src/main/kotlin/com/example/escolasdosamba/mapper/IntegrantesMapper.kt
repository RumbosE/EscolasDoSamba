package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Integrante
import com.example.escolasdosamba.dao.PeriodoActivo
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

    relationship = parentescos.map(Integrante::toParentescoDto).toMutableList().apply {
        addAll(parentescos2.map(Integrante::toParentescoDto))
    },

)

fun Integrante.toParentescoDto() = ParentescoIntegranteDto(
    id = id!!,
    firstName = firstName,
    firstSurname = firstSurname,
    school = periodosActivos.first().escuelaPeriodoActivo!!.name
)


