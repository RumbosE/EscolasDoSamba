package com.example.escolasdosamba.controller

import com.example.escolasdosamba.dto.premio.PremioEspecialCreateRequestDto
import com.example.escolasdosamba.service.PremioEspecialService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/premios")
class PremioController (
    private val premioService: PremioEspecialService
){
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = premioService.findById(id)

    @PostMapping("/escuelas/{idEscuela}")
    fun createPremio(@PathVariable idEscuela: Long,@RequestBody premioCreateRequestDto: PremioEspecialCreateRequestDto) = premioService.savePremio(idEscuela,premioCreateRequestDto)



}