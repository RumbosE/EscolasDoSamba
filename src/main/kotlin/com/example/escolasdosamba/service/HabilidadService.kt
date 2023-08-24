package com.example.escolasdosamba.service

import com.example.escolasdosamba.dto.habilidad.HabilidadDto
import com.example.escolasdosamba.dto.habilidad.HabilidadesDto
import com.example.escolasdosamba.repository.HabilidadRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

interface IHabilidadService {
    fun getHabilidades(): HabilidadesDto

    fun getHabilidadById(id: Long): HabilidadDto
}

@Service
class HabilidadService(
    private val habilidadRepository: HabilidadRepository
): IHabilidadService {
    override fun getHabilidades(): HabilidadesDto = habilidadRepository.findAll()
        .map { HabilidadDto(
            id = it.id,
            name = it.name,
            )
        }
        .let { HabilidadesDto(it) }

    override fun getHabilidadById(id: Long): HabilidadDto = getById(id)
        .let { HabilidadDto(
            id = it.id,
            name = it.name,
            )
        }

    private fun  getById(id: Long) =
        habilidadRepository.findById(id).getOrNull()
            ?: throw IllegalArgumentException("Habilidad with id $id not found")
}