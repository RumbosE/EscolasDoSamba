package com.example.escolasdosamba.service

import com.example.escolasdosamba.dao.Parentesco
import com.example.escolasdosamba.dao.ParentescoId
import com.example.escolasdosamba.dao.PeriodoActivo
import com.example.escolasdosamba.dao.PeriodoActivoId
import com.example.escolasdosamba.dto.integrante.*
import com.example.escolasdosamba.mapper.toDao
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.mapper.toSumaryDto
import com.example.escolasdosamba.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

interface IIntegranteService {
    fun getIntegrantesByEscuela(idEscuela: Long): IntegrantesResponse

    fun getIntegranteById(id: Long): IntegranteDto

    fun createIntegrante(idEscuela: Long,  integranteCreateRequestDto: IntegranteCreateRequestDto): IntegranteDto

    fun updateIntegrante(id: Long, integranteUpdateRequestDto: IntegranteUpdateRequestDto): IntegranteDto

    fun getIntegrantesInactivos() : IntegrantesDto

    fun addHabilidadToIntegrante(idIntegrante: Long, idHabilidad: Long): IntegranteDto

    fun removeHabilidadFromIntegrante(idIntegrante: Long, idHabilidad: Long): IntegranteDto

    fun addParienteToIntegrante(idIntegrante: Long, idPariente: Long, relationship: String ): IntegranteDto

    fun removeParienteFromIntegrante(idIntegrante: Long, idPariente: Long): IntegranteDto

}

@Service
class IntegranteService (
    private val periodoActivoRepository: PeriodoActivoRepository,
    private val integranteRepository: IntegranteRepository,
    private val habilidadRepository: HabilidadRepository,
    private val parentescoRepository: ParentescoRepository,
    private val escuelaRepository: EscuelaRepository,
    private val escuelaService: EscuelaService

): IIntegranteService {

    override fun getIntegrantesByEscuela(idEscuela: Long): IntegrantesResponse = with(periodoActivoRepository) {

        val escuela = escuelaService.getEscuelaById(idEscuela)

        val integrantesActivos = escuela.id?.let { findByIdIdEscuelaAndEndDateIsNull(it)
                .map { it.toSumaryDto() }
                .let { IntegrantesDto(it) }
            }

        val integrantesInactivos = escuela.id?.let { findByIdIdEscuelaAndEndDateIsNotNull(it)
                .map { it.toSumaryDto() }
                .let { IntegrantesDto(it) }
            }

        val integrantesAutoridades = escuela.id?.let { findByIdIdEscuelaAndAuthority(it, 'S')
                .map { it.toSumaryDto() }
                .let { IntegrantesDto(it) }
            }

        return IntegrantesResponse( integrantesActivos, integrantesInactivos, integrantesAutoridades)
    }

    override fun createIntegrante(
        idEscuela: Long, integranteCreateRequestDto: IntegranteCreateRequestDto
    ): IntegranteDto = with(integranteCreateRequestDto) {

        val escuela = escuelaRepository.findById(idEscuela).getOrNull()
            ?: throw Exception("Escuela no encontrada con id: $idEscuela")

        if (identityDocument?.let { integranteRepository.existsIntegranteByIdentityDocumentEquals(it) } == true)
            throw Exception("Ya existe un integrante registrado con el documento de identidad: $identityDocument")

        if (registrationDate == null || authority == null || (authority != 'S' && authority != 'N'))
            throw IllegalArgumentException("La fecha de registro es requerida")

        if (endDate != null && endDate < registrationDate)
            throw Exception("La fecha de retiro no puede ser menor a la fecha de registro")

        val integrante = integranteRepository.save(toDao())

        val periodo = registrationDate.let{
            PeriodoActivo(
                 id = PeriodoActivoId(
                     idEscuela = escuela.id!!,
                     idIntegrante = integrante.id!!,
                     registrationDate = it
            ),
                authority = authority,
                endDate = endDate,
                escuelaPeriodoActivo = escuela,
                integrantePeriodoActivo = integrante
            )}

        return integrante.periodosActivos.add(periodo.let(periodoActivoRepository::save)).let { integrante.toDto() }
    }

    @Transactional
    override fun updateIntegrante(id: Long, integranteUpdateRequestDto: IntegranteUpdateRequestDto): IntegranteDto =
        with(integranteUpdateRequestDto){
            val integrante = getById(id)

            if (!periodoActivoRepository.existsByIdIdIntegranteAndEndDateIsNull(id))
                throw Exception("El integrante no se encuentra activo")
            else if (endDate != null && endDate < periodoActivoRepository.findByIdIdIntegranteAndEndDateIsNull(id).id.registrationDate)
                throw Exception("La fecha de retiro no puede ser menor a la fecha de registro")

            val periodo = periodoActivoRepository.findByIdIdIntegranteAndEndDateIsNull(id)



            firstName?.let { integrante.firstName = it }
            secondName?.let { integrante.secondName = it }
            firstSurname?.let { integrante.firstSurname = it }
            secondSurname?.let { integrante.secondSurname = it }
            birthDate?.let { integrante.birthDate = it }
            nationality?.let { integrante.nationality = it }
            identityDocument?.let { integrante.identityDocument = it }
            nickname?.let { integrante.nickname = it }

            endDate?.let { periodo.endDate = it }

        return integrante.toDto()
    }

    @Transactional
    override fun addHabilidadToIntegrante(idIntegrante: Long, idHabilidad: Long): IntegranteDto {
        val integrante = getById(idIntegrante)
        val habilidad = habilidadRepository.findById(idHabilidad).getOrNull()
            ?: throw Exception("Habilidad no encontrada con id: $idHabilidad")

        integrante.ability.add(habilidad)
        return integrante.toDto()
    }

    @Transactional
    override fun removeHabilidadFromIntegrante(idIntegrante: Long, idHabilidad: Long): IntegranteDto {
        val integrante = getById(idIntegrante)
        val habilidad = habilidadRepository.findById(idHabilidad).getOrNull()
            ?: throw Exception("Habilidad no encontrada con id: $idHabilidad")
        integrante.ability.remove(habilidad)
        return integrante.toDto()
    }

    @Transactional
    override fun addParienteToIntegrante(idIntegrante: Long, idPariente: Long, relationship: String): IntegranteDto {
        val integrante = getById(idIntegrante)
        val pariente = getById(idPariente)

        if (integrante.id == pariente.id)
            throw IllegalArgumentException("No se puede agregar como pariente a si mismo")
        else if (parentescoRepository.existsParentescoByIdIdIntegrante1AndIdIdIntegrante2(integrante.id!!, pariente.id!!))
            throw IllegalArgumentException("Ya existe un parentesco entre los integrantes")
        else if (parentescoRepository.existsParentescoByIdIdIntegrante1AndIdIdIntegrante2(pariente.id!!, integrante.id!!))
            throw IllegalArgumentException("Ya existe un parentesco entre los integrantes")

        val parentesco = {
            Parentesco(
                id = ParentescoId(
                    idIntegrante1 = integrante.id!!,
                    idIntegrante2 = pariente.id!!
                ),
                relation = relationship,
                integrante1 = integrante,
                integrante2 = pariente
            )}

        return integrante.parentescos1.add(parentesco().let(parentescoRepository::save)).let { integrante.toDto() }
    }

    override fun removeParienteFromIntegrante(idIntegrante: Long, idPariente: Long): IntegranteDto {
        val integrante = getById(idIntegrante)
        val pariente = getById(idPariente)

        if (parentescoRepository.existsParentescoByIdIdIntegrante1AndIdIdIntegrante2(idIntegrante, idPariente))
            parentescoRepository.deleteById(ParentescoId(idIntegrante, idPariente))
        else if (parentescoRepository.existsParentescoByIdIdIntegrante1AndIdIdIntegrante2(idPariente, idIntegrante))
            parentescoRepository.deleteById(ParentescoId(idPariente, idIntegrante))
        else
            throw Exception("No existe parentesco entre los integrantes")

        return integrante.toDto()
    }


    override fun getIntegrantesInactivos(): IntegrantesDto {
        val integrantes = periodoActivoRepository.findAllByEndDateIsNotNull()
            .map { it.toSumaryDto() }
            .let { IntegrantesDto(it) }

        return integrantes
    }


    override fun getIntegranteById(id: Long): IntegranteDto = getById(id).toDto()

    private fun getById(id: Long) = integranteRepository.findById(id).getOrNull()
        ?: throw Exception("Integrante no encontrado con id: $id")


}