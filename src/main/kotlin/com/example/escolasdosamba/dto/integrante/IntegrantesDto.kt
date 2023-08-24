package com.example.escolasdosamba.dto.integrante

import com.fasterxml.jackson.annotation.JsonInclude

data class IntegrantesDto(
    val Integrantes: List<SumaryInfoIntegranteDto>
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class IntegrantesResponse(
    val Activos: IntegrantesDto?,
    val Inactivos: IntegrantesDto?,
    val Autoridades: IntegrantesDto?
)
