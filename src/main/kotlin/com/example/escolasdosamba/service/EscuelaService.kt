package com.example.escolasdosamba.service

import com.example.escolasdosamba.dao.Escuela
import com.example.escolasdosamba.dto.escuela.EscuelaCreateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelaDto
import com.example.escolasdosamba.dto.escuela.EscuelaUpdateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelasDto
import com.example.escolasdosamba.mapper.toDao
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.mapper.toSummaryDto
import com.example.escolasdosamba.repository.EscuelaRepository
import com.example.escolasdosamba.repository.LugarRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull

interface IEscuelaService{

    fun getEscuelaById(id: Long): EscuelaDto

    fun getEscuelas(): EscuelasDto

    fun saveEscuela(escuela: EscuelaCreateRequestDto): EscuelaDto

    fun updateEscuela(id: Long, escuelaRequest: EscuelaUpdateRequestDto): EscuelaDto

    fun deleteEscuela(id: Long): EscuelaDto
}

@Service
class EscuelaService(
    private val escuelaRepository: EscuelaRepository,
    private val lugarRepository: LugarRepository
): IEscuelaService {

    override fun getEscuelas() = escuelaRepository.findAll()
        .map(Escuela::toSummaryDto)
        .let { EscuelasDto(it) }

    override fun getEscuelaById(id: Long): EscuelaDto = getById(id).toDto()

    override fun saveEscuela(escuelaCreateRequestDto: EscuelaCreateRequestDto): EscuelaDto = with(escuelaCreateRequestDto) {
        val lugar = lugarId?.let { lugarRepository.findById(it).get() }
            ?: throw IllegalArgumentException("El lugar no puede ser nulo")

        return escuelaRepository.save(escuelaCreateRequestDto.toDao(lugar)).toDto()
    }

    @Transactional
    override fun updateEscuela(id: Long, escuelaRequest: EscuelaUpdateRequestDto): EscuelaDto = with(escuelaRequest){
        val escuela = getById(id)

            name?.let { escuela.name = it }
            history?.let { escuela.history = it }
            foundationDate?.let { escuela.foundationDate = it }
            address?.let { escuela.address = it }
            lugar?.let { escuela.place = lugarRepository.findById(it).get() }

        escuela.toDto()
    }

    override fun deleteEscuela(id: Long): EscuelaDto = getById(id).toDto()
        .also { escuelaRepository.deleteById(id) }

    private fun getById(id:Long) =
        escuelaRepository.findById(id)
            .getOrNull()
            ?:throw NoSuchElementException("No se encontró el lugar con id $id")

    fun <T : Any> Optional<out T>.toList(): List<T> =
        if (this.isPresent) listOf(this.get()) else emptyList()
}