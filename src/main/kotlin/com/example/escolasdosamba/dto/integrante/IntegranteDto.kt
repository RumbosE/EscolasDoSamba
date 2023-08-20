package com.example.escolasdosamba.dto.integrante

import java.sql.Date

data class IntegranteDto(
    val id: Long? = null,
    val firstName: String,
    val secondName: String,
    val firstSurname: String,
    val secondSurname: String,
    val birthDate: Date,
    val nationality: String,
    val identityDocument: String,
    val nickname: String? = null,
    val registrationDate: Date,
    val authority: Char,
    val endDate: Date? = null
)

data class SumaryInfoIntegranteDto(
    val id: Long,
    val firstName: String,
    val firstSurname: String,
    val registrationDate: Date,
    val endDate: Date? = null,
    val authority: Char
)
