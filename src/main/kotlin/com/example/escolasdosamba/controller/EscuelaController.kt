package com.example.escolasdosamba.controller

import com.example.escolasdosamba.dto.escuela.EscuelaCreateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelaUpdateRequestDto
import com.example.escolasdosamba.service.EscuelaService
import org.springframework.web.bind.annotation.*

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

    @DeleteMapping("/{id}")
    fun deleteEscuela(@PathVariable id: Long) = escuelaService.deleteEscuela(id)

    @PostMapping("/{id}/color/{colorId}")
    fun addColor(@PathVariable id: Long, @PathVariable colorId: Long) = escuelaService.addColor(id, colorId)

    @DeleteMapping("/{id}/color/{colorId}")
    fun deleteColor(@PathVariable id: Long, @PathVariable colorId: Long) = escuelaService.deleteColor(id, colorId)
}