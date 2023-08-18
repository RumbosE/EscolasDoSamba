package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Escuela
import com.example.escolasdosamba.dao.Telefono
import com.example.escolasdosamba.dao.TelefonoId
import com.example.escolasdosamba.dto.telefono.TelefonoCreateRequestDto
import com.example.escolasdosamba.dto.telefono.TelefonoDto

fun Telefono.toDto() = TelefonoDto(
        codigo= id.codigo,
        numero = id.numero,
        tipo = tipo,
        numeroCompleto = toString()
)

fun TelefonoCreateRequestDto.toDao(escuela: Escuela) = Telefono(
        id = TelefonoId(
                codigo = codigo ?: throw IllegalArgumentException("El codigo no puede ser nulo"),
                numero = numero ?: throw IllegalArgumentException("El numero no puede ser nulo")
        ),
        tipo = tipo ?: throw IllegalArgumentException("El tipo no puede ser nulo"),
        escuelaTelefono = escuela
)