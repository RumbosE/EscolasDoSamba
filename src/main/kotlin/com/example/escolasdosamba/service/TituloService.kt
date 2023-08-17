package com.example.escolasdosamba.service

import com.example.escolasdosamba.dao.TituloId
import com.example.escolasdosamba.dto.escuela.EscuelaDto
import com.example.escolasdosamba.dto.titulo.TituloCreateRequestDto
import com.example.escolasdosamba.dto.titulo.TituloDto
import com.example.escolasdosamba.dto.titulo.TituloUpdateRequestDto
import com.example.escolasdosamba.dto.titulo.TitulosDto
import com.example.escolasdosamba.mapper.toDao
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.repository.EscuelaRepository
import com.example.escolasdosamba.repository.TituloRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.util.NoSuchElementException
import kotlin.jvm.optionals.getOrNull

interface ITituloService {

    fun getTitulos(idEscuela: Long): TitulosDto
    fun getTituloById(idEscuela: Long, year: Date): TituloDto

    fun createTitulo(idEscuela: Long, tituloCreateRequestDto: TituloCreateRequestDto): TituloDto

    fun deleteTitulo(idEscuela: Long, year: Date): TituloDto

    fun updateTitulo(idEscuela: Long, yearId: Date, tituloUpdateRequestDto: TituloUpdateRequestDto ): TituloDto
}

@Service
class TituloService (
    private val tituloRepository: TituloRepository,
    private val escuelaRepository: EscuelaRepository
): ITituloService{

    override fun getTitulos(idEscuela: Long): TitulosDto  {
        if (escuelaRepository.findById(idEscuela).isEmpty) {
            throw NoSuchElementException("No se encontró la escuela con id: $idEscuela")
        }
        else {
            return TitulosDto(tituloRepository.findAll().filter { it.id.idEscuela == idEscuela }.map { it.toDto() })
        }
    }
    override fun getTituloById(idEscuela: Long, year: Date): TituloDto = getById(idEscuela, year).toDto()

    override fun createTitulo(idEscuela: Long, tituloCreateRequestDto: TituloCreateRequestDto): TituloDto {
        if (escuelaRepository.findById(idEscuela).isEmpty) {
            throw NoSuchElementException("No se encontró la escuela con id: $idEscuela")

        } else{
            val escuela= escuelaRepository.findById(idEscuela).get()
            return tituloRepository.save(tituloCreateRequestDto.toDao(escuela)).toDto()
        }
    }

    override fun deleteTitulo(idEscuela: Long, year: Date): TituloDto = getById(idEscuela, year).toDto().
        also { tituloRepository.deleteById(TituloId(idEscuela,year)) }

    @Transactional
    override fun updateTitulo(idEscuela: Long, year: Date ,tituloRequest: TituloUpdateRequestDto): TituloDto = with(tituloRequest){
        val titulo = getById(idEscuela,year )

        monto?.let { titulo.monto = it }
        grupo?.let { titulo.grupo = it }

        titulo.toDto()
    }




    private fun getById(idEscuela: Long, year: Date) =
        tituloRepository.findById(TituloId(idEscuela,year)).getOrNull()
            ?: throw NoSuchElementException("No se encontró el titulo con idEscuela: $idEscuela y year: $year")

}
