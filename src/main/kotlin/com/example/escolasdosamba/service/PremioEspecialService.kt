package com.example.escolasdosamba.service

import org.springframework.stereotype.Service
import com.example.escolasdosamba.dto.premio.PremioEspecialCreateRequestDto
import com.example.escolasdosamba.dto.premio.PremioEspecialDto
import com.example.escolasdosamba.dto.premio.PremioEspecialUpdateRequestDto
import com.example.escolasdosamba.mapper.toDao
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.repository.GanadorRepository
import com.example.escolasdosamba.repository.LugarRepository
import com.example.escolasdosamba.repository.PremioEspecialRepository
import org.springframework.transaction.annotation.Transactional
import java.util.NoSuchElementException
import kotlin.jvm.optionals.getOrNull

interface IPremioEspecialService {

    fun findAll(): List<PremioEspecialDto>
    fun findById(id: Long): PremioEspecialDto

    fun savePremio(premioEspecialCreateRequestDto: PremioEspecialCreateRequestDto): PremioEspecialDto

    fun updatePremio(id: Long, premioEspecialUpdateRequestDto: PremioEspecialUpdateRequestDto): PremioEspecialDto

    fun deletePremio(id: Long): PremioEspecialDto
}

@Service
class PremioEspecialService(
    private val premioRepository: PremioEspecialRepository,
    private val lugarRepository: LugarRepository

): IPremioEspecialService {

    override fun findAll(): List<PremioEspecialDto> = premioRepository.findAll().map { it.toDto() }
    override fun findById(id: Long): PremioEspecialDto = getById(id).toDto()

    override fun savePremio( premioEspecialCreateRequestDto: PremioEspecialCreateRequestDto
    ): PremioEspecialDto = with(premioEspecialCreateRequestDto) {

        val lugar = lugarId?.let { lugarRepository.findById(it) }
            ?.getOrNull()
            ?: throw NoSuchElementException("No existe el lugar con id: $lugarId")

        return premioRepository.save(toDao(lugar)).toDto()

    }

    @Transactional
    override fun updatePremio(id: Long, premioEspecialUpdateRequestDto: PremioEspecialUpdateRequestDto): PremioEspecialDto = with(premioEspecialUpdateRequestDto) {
        val premio = getById(id)

            name?. let { premio.name = it }
            type?. let { premio.type = it }
            description?. let { premio.description = it }
            lugarId?. let {premio.place = lugarRepository.findById(it).get() }

        return premio.toDto()
    }


    override fun deletePremio(id: Long): PremioEspecialDto = getById(id).toDto()
        .also { premioRepository.deleteById(id) }

    private fun getById(id:Long) =
        premioRepository.findById(id)
            .getOrNull()
            ?:throw NoSuchElementException("No existe el premio especial con id: $id")

}