package com.example.escolasdosamba.controller

import com.example.escolasdosamba.dto.escuela.EscuelaCreateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelaUpdateRequestDto
import com.example.escolasdosamba.service.EscuelaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/escuela")
class EscuelaController(
    private val escuelaService: EscuelaService
) {
    @GetMapping("/")
    fun getEscuelas() = escuelaService.getEscuelas()

    @GetMapping("/{id}")
    fun getEscuela(@PathVariable id:Long) = escuelaService.getEscuelaById(id)

    @PostMapping("/")
    fun saveEscuela(@RequestBody escuelaCreateRequestDto: EscuelaCreateRequestDto) = escuelaService.saveEscuela(escuelaCreateRequestDto)

    @PatchMapping("/{id}")
    fun updateEscuela(@PathVariable id: Long, @RequestBody escuelaUpdateRequestDto: EscuelaUpdateRequestDto) = escuelaService.updateEscuela(id, escuelaUpdateRequestDto)

}