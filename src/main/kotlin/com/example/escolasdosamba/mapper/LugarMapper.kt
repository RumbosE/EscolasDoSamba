package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Lugar
import com.example.escolasdosamba.dto.lugar.LugarDto

fun Lugar.toDto()= LugarDto(
        id = this.id,
        name = this.name,
        fatherId = this.fatherId,
        fatherName = this.father?.name,
)
