package com.example.escolasdosamba.controller

import com.example.escolasdosamba.service.EscuelaService
import com.example.escolasdosamba.service.IntegranteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/integrantes")
class IntegranteController(
    private val integranteService: IntegranteService,
) {

    @GetMapping("/escuelas/{idEscuela}")
    fun getIntegrantesByEscuela(@PathVariable idEscuela: Long) = integranteService.getIntegrantesByEscuela(idEscuela)

    @GetMapping("/{id}")
    fun getIntegranteById(@PathVariable id: Long) = integranteService.getIntegranteById(id)

    @GetMapping("/escuelas/{idEscuela}/activos")
    fun getIntegrantesActivosByEscuela(@PathVariable idEscuela: Long) = integranteService.getIntegrantesActivosByEscuela(idEscuela)

    @GetMapping("/escuelas/{idEscuela}/inactivos")
    fun getIntegrantesInactivosByEscuela(@PathVariable idEscuela: Long) = integranteService.getIntegrantesInactivosByEscuela(idEscuela)

    @GetMapping("/escuelas/{idEscuela}/autoridades")
    fun getIntegrantesAutoridadesByEscuela(@PathVariable idEscuela: Long) = integranteService.getIntegrantesAutoridadesByEscuela(idEscuela)
}