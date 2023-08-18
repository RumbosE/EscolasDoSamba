package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Color
import com.example.escolasdosamba.dto.color.ColorDto

fun Color.toDto() = ColorDto(
    id = id,
    name = name
)
