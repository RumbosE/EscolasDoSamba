package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Color
import com.example.escolasdosamba.dao.EC
import com.example.escolasdosamba.dto.color.ColorDto

fun EC.toDto() = ColorDto (
    id = colorEC?.id,
    name = colorEC?.name
)