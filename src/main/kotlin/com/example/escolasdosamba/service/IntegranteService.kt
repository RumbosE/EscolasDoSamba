package com.example.escolasdosamba.service

import com.example.escolasdosamba.dto.integrante.IntegranteDto
import com.example.escolasdosamba.dto.integrante.IntegrantesDto
import com.example.escolasdosamba.repository.PeriodoActivoRepository
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.mapper.toSumaryDto
import com.example.escolasdosamba.repository.IntegranteRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

interface IIntegranteService {
    fun getIntegrantesByEscuela(idEscuela: Long): IntegrantesDto

    fun getIntegrantesActivosByEscuela(idEscuela: Long): IntegrantesDto

    fun getIntegranteById(id: Long): IntegranteDto

    fun getIntegrantesInactivosByEscuela(idEscuela: Long): IntegrantesDto

    fun getIntegrantesAutoridadesByEscuela(idEscuela: Long): IntegrantesDto

}

@Service
class IntegranteService (
    private val periodoActivoRepository: PeriodoActivoRepository,
    private val integranteRepository: IntegranteRepository
): IIntegranteService {

    override fun getIntegrantesByEscuela(idEscuela: Long): IntegrantesDto = periodoActivoRepository.findByIdIdEscuela(idEscuela)
        .map { it.toSumaryDto() }
        .let { IntegrantesDto(it) }

    override fun getIntegrantesActivosByEscuela(idEscuela: Long): IntegrantesDto = periodoActivoRepository.findByIdIdEscuelaAndEndDateIsNull(idEscuela)
        .map { it.toSumaryDto() }
        .let { IntegrantesDto(it) }

    override fun getIntegrantesInactivosByEscuela(idEscuela: Long): IntegrantesDto = periodoActivoRepository.findByIdIdEscuelaAndEndDateIsNotNull(idEscuela)
        .map { it.toSumaryDto() }
        .let { IntegrantesDto(it) }

    override fun getIntegrantesAutoridadesByEscuela(idEscuela: Long): IntegrantesDto = periodoActivoRepository.findByIdIdEscuelaAndAuthority(idEscuela, 'S')
        .map { it.toSumaryDto() }
        .let { IntegrantesDto(it) }

    override fun getIntegranteById(id: Long): IntegranteDto = getById(id).toDto()

    private fun getById(id: Long) = integranteRepository.findById(id).getOrNull()
        ?: throw Exception("Integrante no encontrado con id: $id")


}