package com.example.escolasdosamba.service

import org.springframework.stereotype.Service
import com.example.escolasdosamba.dto.premio.PremioEspecialCreateRequestDto
import com.example.escolasdosamba.dto.premio.PremioEspecialDto
import com.example.escolasdosamba.mapper.toDao
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.repository.GanadorRepository
import com.example.escolasdosamba.repository.LugarRepository
import com.example.escolasdosamba.repository.PremioEspecialRepository
import java.util.NoSuchElementException
import kotlin.jvm.optionals.getOrNull

interface IPremioEspecialService {
    fun findById(id: Long): PremioEspecialDto

    fun savePremio(idEscuela: Long,premioEspecialCreateRequestDto: PremioEspecialCreateRequestDto): PremioEspecialDto

    fun updatePremio(id: Long, premioEspecialDto: PremioEspecialDto): PremioEspecialDto

    fun deletePremio(id: Long): PremioEspecialDto
}

@Service
class PremioEspecialService(
    private val premioRepository: PremioEspecialRepository,
    private val lugarRepository: LugarRepository

): IPremioEspecialService {
    override fun findById(id: Long): PremioEspecialDto = getById(id).toDto()

    override fun savePremio(idEscuela: Long, premioEspecialCreateRequestDto: PremioEspecialCreateRequestDto
    ): PremioEspecialDto = with(premioEspecialCreateRequestDto) {

        val lugar = lugarId.let { lugarRepository.findById(it) }
            .getOrNull()
            ?: throw NoSuchElementException("No existe el lugar con id: $idEscuela")

        return premioRepository.save(toDao(lugar)).toDto()

    }

    override fun updatePremio(id: Long, premioEspecialDto: PremioEspecialDto): PremioEspecialDto {
        TODO()
    }

    override fun deletePremio(id: Long): PremioEspecialDto {
        TODO()
    }

    private fun getById(id:Long) =
        premioRepository.findById(id)
            .getOrNull()
            ?:throw NoSuchElementException("No existe el premio especial con id: $id")

}