package com.example.escolasdosamba.controller

import com.example.escolasdosamba.service.LugarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lugar")
class LugarController (
        private val lugarService: LugarService
    ){
    @GetMapping("/")
    fun getLugares() = lugarService.getLugares()

    @GetMapping("/{id}")
    fun getLugar(@PathVariable("id") id: Long) = lugarService.getLugarById(id)

    @GetMapping("/estados")
    fun getEstados() = lugarService.getEstados()

    @GetMapping("/regiones")
    fun getRegiones() = lugarService.getRegiones()

}