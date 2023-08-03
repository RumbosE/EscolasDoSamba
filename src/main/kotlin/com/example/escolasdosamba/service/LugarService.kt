package com.example.escolasdosamba.service

import com.example.escolasdosamba.dao.Lugar
import com.example.escolasdosamba.dto.lugar.LugarDto
import com.example.escolasdosamba.dto.lugar.LugaresDto
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.repository.LugarRepository
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

interface ILugarService {
    fun getLugares(): LugaresDto
    fun getEstados(): LugaresDto

    fun getRegiones(): LugaresDto
    fun getLugarById(id: Long): LugarDto

}
@Service
class LugarService(
        private val lugarRepository: LugarRepository
) : ILugarService {
    override fun getLugares(): LugaresDto = lugarRepository.findAll().toList().map (Lugar::toDto).let { LugaresDto(it) }

    override fun getEstados(): LugaresDto = lugarRepository.getEstados().toList().map (Lugar::toDto).let { LugaresDto(it) }

    override fun getRegiones(): LugaresDto = lugarRepository.getRegiones().toList().map (Lugar::toDto).let { LugaresDto(it) }

    override fun getLugarById(id: Long): LugarDto = getById(id).toDto()

    private fun getById(id:Long) =
            lugarRepository.findById(id)
                    .getOrNull()
                    ?:throw NoSuchElementException("No se encontró el lugar con id $id")

    fun <T : Any> Optional<out T>.toList(): List<T> =
            if (this.isPresent) listOf(this.get()) else emptyList()
}