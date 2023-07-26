package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Lugar
import com.example.escolasdosamba.dto.lugar.LugarDto

fun Lugar.toDto() = LugarDto(
        id = id,
        name = name,
        type = type,
        fatherType = father?.type,
        fatherName = father?.name,
)