package com.example.escolasdosamba.dto.premio

data class PremioEspecialUpdateRequestDto(

        val name: String?,
        val type: Char?,
        val description: String?,
        val lugarId: Long?,
)
