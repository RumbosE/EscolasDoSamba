package com.example.escolasdosamba.controller

import com.example.escolasdosamba.service.LugarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/lugar")
class LugarController (
        private val lugarService: LugarService
) {
    @GetMapping("/ciudades")
    fun getAllCiudades() = lugarService.getAllCiudades()

}