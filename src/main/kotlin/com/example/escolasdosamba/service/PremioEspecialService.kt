package com.example.escolasdosamba.service

import com.example.escolasdosamba.dto.premio.GanadorEscuelaDto
import com.example.escolasdosamba.dto.premio.PremioEspecialCreateRequestDto
import com.example.escolasdosamba.dto.premio.PremioEspecialDto
import com.example.escolasdosamba.mapper.toDao
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.repository.EscuelaRepository
import com.example.escolasdosamba.repository.GanadorRepository
import com.example.escolasdosamba.repository.LugarRepository
import com.example.escolasdosamba.repository.PremioEspecialRepository
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import kotlin.jvm.optionals.getOrNull

interface IPremioEspecialService {
    fun findById(id: Long): PremioEspecialDto
    fun savePremio(idEscuela: Long,premioEspecialCreateRequestDto: PremioEspecialCreateRequestDto): PremioEspecialDto
    fun update(id: Long, premioEspecialDto: PremioEspecialDto): PremioEspecialDto
    fun delete(id: Long): PremioEspecialDto
}

@Service
class PremioEspecialService(
    private val premioRepository: PremioEspecialRepository,
    private val lugarRepository: LugarRepository,
    private val escuelaRepository: EscuelaRepository,
    private val ganadorRepository: GanadorRepository

): IPremioEspecialService {
    override fun findById(id: Long): PremioEspecialDto = getById(id).toDto()

    override fun savePremio(idEscuela: Long, premioEspecialCreateRequestDto: PremioEspecialCreateRequestDto
    ): PremioEspecialDto = with(premioEspecialCreateRequestDto) {

        val lugar = lugarId.let { lugarRepository.findById(it) }
            .getOrNull()
            ?: throw NoSuchElementException("No existe el lugar con id: $idEscuela")

        val escuela = escuelaRepository.findById(idEscuela)
            .getOrNull()
            ?: throw NoSuchElementException("No existe la escuela con id: $idEscuela")

        val premio = toDao(lugar)
        val premioSaved = premioRepository.save(premio)


        val ganador = premioSaved.id?.let { ganadorRepository.insertGanador(it, idEscuela, year) }

        return ganador?.toDto() ?: throw Exception("No se pudo guardar el premio")

    }

    override fun update(id: Long, premioEspecialDto: PremioEspecialDto): PremioEspecialDto {
        TODO()
    }

    override fun delete(id: Long): PremioEspecialDto {
        TODO()
    }

    private fun getById(id:Long) =
        premioRepository.findById(id)
            .getOrNull()
            ?:throw NoSuchElementException("No existe el premio especial con id: $id")

}