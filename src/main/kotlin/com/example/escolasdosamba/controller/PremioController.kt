package com.example.escolasdosamba.controller

import com.example.escolasdosamba.dto.premio.PremioEspecialCreateRequestDto
import com.example.escolasdosamba.dto.premio.PremioEspecialUpdateRequestDto
import com.example.escolasdosamba.service.EscuelaService
import com.example.escolasdosamba.service.PremioEspecialService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Date

@RestController
@RequestMapping("/api/premios")
class PremioController (
    private val premioService: PremioEspecialService,
    private val escuelaService: EscuelaService
){
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = premioService.findById(id)

    @PostMapping("/escuelas/{idEscuela}")
    fun createPremio(@PathVariable idEscuela: Long, @RequestParam year: Date ,@RequestBody premioCreateRequestDto: PremioEspecialCreateRequestDto) =
        escuelaService.addPremioEspecial(idEscuela, year, premioCreateRequestDto)

    @PatchMapping("/{id}")
    fun updatePremio(@PathVariable id: Long, @RequestBody premioEspecialUpdateRequestDto: PremioEspecialUpdateRequestDto) =
        premioService.updatePremio(id, premioEspecialUpdateRequestDto)

    @DeleteMapping("/{id}/escuelas/{idEscuela}")
    fun deletePremio(@PathVariable id: Long, @PathVariable idEscuela: Long, @RequestParam year: Date ) =
        escuelaService.deletePremioEspecial(idEscuela, year, id)


}