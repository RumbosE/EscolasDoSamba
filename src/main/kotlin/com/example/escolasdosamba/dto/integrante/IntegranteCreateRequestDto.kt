package com.example.escolasdosamba.dto.integrante

import java.sql.Date

data class IntegranteCreateRequestDto  (
    val firstName : String?,
    val secondName : String?,
    val firstSurname: String?,
    val secondSurname : String?,
    val birthDate : Date?,
    val nationality : String?,
    val identityDocument : String?,
    val nickname : String?,
    val registrationDate: Date?,
    val endDate: Date?,
    val authority: Char?,
)