package com.example.escolasdosamba.controller

import com.example.escolasdosamba.dto.integrante.IntegranteCreateRequestDto
import com.example.escolasdosamba.dto.integrante.IntegranteUpdateRequestDto
import com.example.escolasdosamba.service.IntegranteService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/integrantes")
class IntegranteController(
    private val integranteService: IntegranteService
) {

    @GetMapping("/escuelas/{idEscuela}")
    fun getIntegrantesByEscuela(@PathVariable idEscuela: Long) = integranteService.getIntegrantesByEscuela(idEscuela)

    @GetMapping("/{id}")
    fun getIntegranteById(@PathVariable id: Long) = integranteService.getIntegranteById(id)

    @PostMapping("/escuelas/{idEscuela}")
    fun createIntegranteToEscuela(@PathVariable idEscuela: Long, @RequestBody integranteCreateRequestDto: IntegranteCreateRequestDto) =
        integranteService.createIntegrante(idEscuela, integranteCreateRequestDto)

    @PatchMapping("/{id}")
    fun updateIntegrante(@PathVariable id: Long, @RequestBody integranteUpdateRequestDto: IntegranteUpdateRequestDto) =
        integranteService.updateIntegrante(id, integranteUpdateRequestDto)

    @GetMapping("/inactivos")
    fun getIntegrantesInactivos() = integranteService.getIntegrantesInactivos()

    @PostMapping("/{id}/habilidades/{idHabilidad}")
    fun addHabilidad(@PathVariable id: Long, @PathVariable idHabilidad: Long) =
        integranteService.addHabilidadToIntegrante(id, idHabilidad)

    @DeleteMapping("/{id}/habilidades/{idHabilidad}")
    fun deleteHabilidad(@PathVariable id: Long, @PathVariable idHabilidad: Long) =
        integranteService.removeHabilidadFromIntegrante(id, idHabilidad)

    @PostMapping("/{id}/parientes/{idPariente}")
    fun addPariente(@PathVariable id: Long, @PathVariable idPariente: Long, @RequestParam relationship: String) =
        integranteService.addParienteToIntegrante(id, idPariente, relationship)

    @DeleteMapping("/{id}/parientes/{idPariente}")
    fun deletePariente(@PathVariable id: Long, @PathVariable idPariente: Long) =
        integranteService.removeParienteFromIntegrante(id, idPariente)
}