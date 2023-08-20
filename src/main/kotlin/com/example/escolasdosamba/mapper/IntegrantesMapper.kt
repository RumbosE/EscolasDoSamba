package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.PeriodoActivo
import com.example.escolasdosamba.dto.integrante.SumaryInfoIntegranteDto

fun PeriodoActivo.toDto() = SumaryInfoIntegranteDto(
    id = integrantePeriodoActivo?.id!!,
    firstName = integrantePeriodoActivo.firstName,
    firstSurname = integrantePeriodoActivo.firstSurname,
    registrationDate = id.registrationDate,
    endDate = endDate,
    authority = authority

)

