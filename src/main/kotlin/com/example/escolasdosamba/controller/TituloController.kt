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
import org.springframework.web.bind.annotation.RestController
import java.sql.Date

@RestController
@RequestMapping("/api/titulo")
class TituloController(
    private val tituloService: TituloService
) {
    @GetMapping("/{id}")
    fun getTitulos(@PathVariable id: Long) = tituloService.getTitulos(id)
    @GetMapping("/{id}/{year}")
    fun getTitulo(@PathVariable id:Long, @PathVariable year:Date) = tituloService.getTituloById(id, year)

    @PostMapping("/{id}")
    fun createTitulo(@PathVariable id: Long, @RequestBody tituloCreateRequestDto: TituloCreateRequestDto) =
        tituloService.createTitulo(id, tituloCreateRequestDto)

    @PatchMapping("/{id}/{year}")
    fun updateTitulo(@PathVariable id: Long, @PathVariable year: Date, @RequestBody tituloUpdateRequestDto: TituloUpdateRequestDto) =
        tituloService.updateTitulo(id, year, tituloUpdateRequestDto)

    @DeleteMapping("/{id}/{year}")
    fun deleteTitulo(@PathVariable id: Long, @PathVariable year: Date) = tituloService.deleteTitulo(id, year)
}