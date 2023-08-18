package com.example.escolasdosamba.dto.telefono

data class TelefonoCreateRequestDto(
        val codigo: Long?,
        val numero: Long?,
        val tipo: Char?,
        var idEscuela: Long?
)
