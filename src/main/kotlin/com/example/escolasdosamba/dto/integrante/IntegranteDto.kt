package com.example.escolasdosamba.dto.integrante

import com.example.escolasdosamba.dto.habilidad.HabilidadDto
import java.sql.Date

data class IntegranteDto(
    val id: Long? = null,
    val firstName: String,
    val secondName: String?,
    val firstSurname: String?,
    val secondSurname: String?,
    val birthDate: Date,
    val nationality: String,
    val identityDocument: String?,
    val nickname: String? = null,
    val periodoEscuelas: MutableList<EscuelasInfoIntegranteDto> = mutableListOf(),
    val ability: MutableList<HabilidadDto> = mutableListOf(),
    val relationship: MutableList<ParentescoIntegranteDto> = mutableListOf()

    )

data class SumaryInfoIntegranteDto(
    val id: Long,
    val firstName: String,
    val firstSurname: String,
    val registrationDate: Date,
    val endDate: Date? = null,
    val authority: Char
)

data class ParentescoIntegranteDto(
    val id: Long,
    val firstName: String,
    val firstSurname: String,
    val relationship: String,
    val school: String

)

data class EscuelasInfoIntegranteDto(
    val id: Long,
    val school: String,
    val registrationDate: Date,
    val endDate: Date? = null,
    val authority: Char
)
