package com.example.escolasdosamba.service

import com.example.escolasdosamba.dao.Color
import com.example.escolasdosamba.dto.color.ColorDto
import com.example.escolasdosamba.dto.color.ColorsDto
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.repository.ColorRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

interface IColorService{
    fun getAllColors(): ColorsDto
    fun getColorById(id: Long): ColorDto

}

@Service
class ColorService(
    private val colorRepository: ColorRepository
): IColorService {
    override fun getAllColors(): ColorsDto = colorRepository.findAll()
        .map (Color::toDto)
        .let { ColorsDto(it) }

    override fun getColorById(id: Long): ColorDto = getById(id).toDto()

    private fun getById(id: Long) =
        colorRepository.findById(id)
            .getOrNull()
            ?: throw IllegalArgumentException("El color con id $id no existe")

    fun <T : Any> Optional<out T>.toList(): List<T> =
        if (this.isPresent) listOf(this.get()) else emptyList()
}