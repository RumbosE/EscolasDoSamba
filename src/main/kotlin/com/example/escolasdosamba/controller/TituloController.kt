package com.example.escolasdosamba.controller

import com.example.escolasdosamba.dto.titulo.TituloCreateRequestDto
import com.example.escolasdosamba.dto.titulo.TituloUpdateRequestDto
import com.example.escolasdosamba.service.TituloService
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
@RequestMapping("/api")
class TituloController(
    private val tituloService: TituloService
) {
    @GetMapping("/escuelas/{escuelaId}/titulos")
    fun getTitulos(@PathVariable escuelaId: Long) = tituloService.getTitulos(escuelaId)
    @GetMapping("/escuelas/{escuelaId}/titulos/year")
    fun getTitulo(@PathVariable escuelaId: Long, @RequestParam(name = "year", required = true) year: Date ) = tituloService.getTituloById(escuelaId, year)

    @PostMapping("/escuelas/{escuelaId}/titulos")
    fun createTitulo(@PathVariable escuelaId: Long, @RequestBody tituloCreateRequestDto: TituloCreateRequestDto) =
        tituloService.createTitulo(escuelaId, tituloCreateRequestDto)

    @PatchMapping("/escuelas/{escuelaId}/titulos")
    fun updateTitulo(@PathVariable escuelaId: Long, @RequestParam(name = "year", required = true) year: Date, @RequestBody tituloUpdateRequestDto: TituloUpdateRequestDto) =
        tituloService.updateTitulo(escuelaId, year, tituloUpdateRequestDto)

    @DeleteMapping("/escuelas/{escuelaId}/titulos")
    fun deleteTitulo(@PathVariable escuelaId: Long,@RequestParam(name = "year", required = true) year: Date ) = tituloService.deleteTitulo(escuelaId, year)
}