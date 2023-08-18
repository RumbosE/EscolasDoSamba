package com.example.escolasdosamba.service

import com.example.escolasdosamba.dao.Escuela
import com.example.escolasdosamba.dao.TelefonoId
import com.example.escolasdosamba.dto.escuela.EscuelaCreateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelaDto
import com.example.escolasdosamba.dto.escuela.EscuelaUpdateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelasDto
import com.example.escolasdosamba.dto.telefono.TelefonoCreateRequestDto
import com.example.escolasdosamba.mapper.toDao
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.mapper.toSummaryDto
import com.example.escolasdosamba.repository.ColorRepository
import com.example.escolasdosamba.repository.EscuelaRepository
import com.example.escolasdosamba.repository.LugarRepository
import com.example.escolasdosamba.repository.TelefonoRepository
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

    fun addColorToEscuela(id: Long, colorId: Long): EscuelaDto

    fun deleteColorToEscuela(id: Long, colorId: Long): EscuelaDto

    fun addTelefonoToEscuela(id: Long, telefonoCreateRequestDto: TelefonoCreateRequestDto): EscuelaDto

    fun deleteTelefonoToEscuela(id: Long, codigo: Long, numero:Long): EscuelaDto

}

@Service
class EscuelaService(
    private val escuelaRepository: EscuelaRepository,
    private val lugarRepository: LugarRepository,
    private val colorRepository: ColorRepository,
    private val telefonoRepository: TelefonoRepository
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

    @Transactional
    override fun addColorToEscuela(id: Long, colorId: Long): EscuelaDto {

        val escuela = getById(id)
        val color = colorRepository.findById(colorId).getOrNull()
            ?: throw IllegalArgumentException("El color no puede ser nulo")

        escuela.colors.add(color)
        return escuela.toDto()
    }

    @Transactional
    override fun deleteColorToEscuela(id: Long, colorId: Long): EscuelaDto {

        val escuela = getById(id)

        val color = escuela.colors.find { it.id == colorId }
            ?: throw IllegalArgumentException("La escuela no tiene el color con id $colorId")

        escuela.colors.remove(color)
        return escuela.toDto()

    }

    override fun addTelefonoToEscuela(id: Long, telefonoCreateRequestDto: TelefonoCreateRequestDto): EscuelaDto {

        val escuela = getById(id)

         telefonoRepository.save(telefonoCreateRequestDto.toDao(escuela))

        return escuela.toDto()

    }

    override fun deleteTelefonoToEscuela(id: Long, codigo: Long, numero: Long): EscuelaDto {

        val escuela = getById(id)

        if(telefonoRepository.findById(TelefonoId(codigo, numero)).isEmpty)
            throw IllegalArgumentException("La escuela no tiene el telefono con codigo $codigo y numero $numero")

        telefonoRepository.deleteById(TelefonoId(codigo, numero)).also { escuela.telefonos.removeIf { it.id.codigo == codigo && it.id.numero == numero } }

        return escuela.toDto()
    }

    private fun getById(id:Long) =
        escuelaRepository.findById(id)
            .getOrNull()
            ?:throw NoSuchElementException("No se encontró el lugar con id $id")

    fun <T : Any> Optional<out T>.toList(): List<T> =
        if (this.isPresent) listOf(this.get()) else emptyList()
}