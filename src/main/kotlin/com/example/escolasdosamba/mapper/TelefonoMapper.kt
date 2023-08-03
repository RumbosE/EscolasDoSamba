package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Telefono
import com.example.escolasdosamba.dto.telefono.TelefonoDto

fun Telefono.toDto() = TelefonoDto(
        codigo= id.codigo,
        numero = id.numero,
        tipo = tipo
)