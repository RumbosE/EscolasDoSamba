package com.example.escolasdosamba.dto.integrante

import java.sql.Date

data class IntegranteUpdateRequestDto(

    val firstName : String? = null,
    val secondName : String? = null,
    val firstSurname: String? = null,
    val secondSurname : String? = null,
    val birthDate : Date? = null,
    val nationality : String? = null,
    val identityDocument : String? = null,
    val nickname : String? = null,
    val endDate : Date? = null

    )
