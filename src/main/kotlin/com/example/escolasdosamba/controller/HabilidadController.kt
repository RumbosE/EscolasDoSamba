package com.example.escolasdosamba.controller

import com.example.escolasdosamba.service.HabilidadService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/habilidades")
class HabilidadController(
    private val habilidadService: HabilidadService
) {

    @GetMapping("/")
    fun getHabilidades() = habilidadService.getHabilidades()

    @GetMapping("/{id}")
    fun getHabilidadById(@PathVariable id: Long) = habilidadService.getHabilidadById(id)

}