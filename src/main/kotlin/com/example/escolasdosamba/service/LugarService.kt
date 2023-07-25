package com.example.escolasdosamba.service

import com.example.escolasdosamba.dao.Lugar
import com.example.escolasdosamba.dto.lugar.LugaresDto
import com.example.escolasdosamba.mapper.toDto
import com.example.escolasdosamba.repository.LugarRepository
import org.springframework.stereotype.Service

interface ILugarService {
    fun getAllCiudades(): LugaresDto
}

@Service
class LugarService (
        private val lugarRepository: LugarRepository
) : ILugarService {
    override fun getAllCiudades(): LugaresDto = lugarRepository.getAllCiudades()
            .toList().
            let { LugaresDto(it) }
}