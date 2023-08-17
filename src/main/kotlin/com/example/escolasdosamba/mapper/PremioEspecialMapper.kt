package com.example.escolasdosamba.mapper

import com.example.escolasdosamba.dao.Ganador
import com.example.escolasdosamba.dto.premio.PremioEspecialDto

fun Ganador.toDto() = PremioEspecialDto(
    id = id.idPremio,
    name = premioGanador.name,
    type = premioGanador.type,
    description = premioGanador.description,
    place = premioGanador.place?.name,
    year = id.year
)