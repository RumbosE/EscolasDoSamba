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
    fun getIntegrantesByEscuelaId(@PathVariable idEscuela: Long) = integranteService.getIntegrantesByEscuelaId(idEscuela)

}