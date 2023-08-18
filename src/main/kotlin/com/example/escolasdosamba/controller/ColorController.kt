package com.example.escolasdosamba.controller

import com.example.escolasdosamba.service.ColorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/color")
class ColorController(
    private val colorService: ColorService
) {
    @GetMapping("/")
    fun getColors() = colorService.getAllColors()

    @GetMapping("/{id}")
    fun getColor(@PathVariable("id") id: Long) = colorService.getColorById(id)
}