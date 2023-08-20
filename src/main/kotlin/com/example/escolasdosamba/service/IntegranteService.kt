package com.example.escolasdosamba.service

import com.example.escolasdosamba.dto.integrante.IntegrantesDto
import com.example.escolasdosamba.repository.PeriodoActivoRepository
import com.example.escolasdosamba.mapper.toDto
import org.springframework.stereotype.Service

interface IIntegranteService {
    fun getIntegrantesByEscuelaId(idEscuela: Long): IntegrantesDto
}

@Service
class IntegranteService (
    private val periodoActivoRepository: PeriodoActivoRepository
): IIntegranteService {

    override fun getIntegrantesByEscuelaId(idEscuela: Long): IntegrantesDto = periodoActivoRepository.findByIdIdEscuela(idEscuela)
        .toList()
        .map { it.toDto() }
        .let { IntegrantesDto(it) }
}