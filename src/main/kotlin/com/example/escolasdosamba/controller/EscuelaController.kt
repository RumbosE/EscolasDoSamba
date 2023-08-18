package com.example.escolasdosamba.controller

import com.example.escolasdosamba.dto.escuela.EscuelaCreateRequestDto
import com.example.escolasdosamba.dto.escuela.EscuelaUpdateRequestDto
import com.example.escolasdosamba.dto.telefono.TelefonoCreateRequestDto
import com.example.escolasdosamba.service.EscuelaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/escuelas")
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

    @PostMapping("/{id}/colors/{colorId}")
    fun addColor(@PathVariable id: Long, @PathVariable colorId: Long) = escuelaService.addColorToEscuela(id, colorId)

    @DeleteMapping("/{id}/colors/{colorId}")
    fun deleteColor(@PathVariable id: Long, @PathVariable colorId: Long) = escuelaService.deleteColorToEscuela(id, colorId)

    @PostMapping("/{id}/telefonos")
    fun addTelefono(@PathVariable id: Long, @RequestBody telefonoCreateRequestDto: TelefonoCreateRequestDto) = escuelaService.addTelefonoToEscuela(id, telefonoCreateRequestDto)

    @DeleteMapping("/{id}/telefonos")
    fun deleteTelefono(@PathVariable id: Long, @RequestParam(name = "numero", required = true) numero: Long, @RequestParam(name = "codigo", required = true) codigo: Long ) = escuelaService.deleteTelefonoToEscuela(id, codigo, numero)

}