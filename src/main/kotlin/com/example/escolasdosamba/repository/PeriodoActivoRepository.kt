package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.PeriodoActivo
import com.example.escolasdosamba.dao.PeriodoActivoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface PeriodoActivoRepository: JpaRepository<PeriodoActivo, PeriodoActivoId> {

    fun findByIdIdEscuela(idEscuela: Long): List<PeriodoActivo>

    fun findByIdIdEscuelaAndAuthority (idEscuela: Long, authority: Char): List<PeriodoActivo>

    fun findByIdIdEscuelaAndEndDateIsNull (idEscuela: Long): List<PeriodoActivo>

    fun findByIdIdEscuelaAndEndDateIsNotNull (idEscuela: Long): List<PeriodoActivo>

    fun findByIdIdIntegranteAndEndDateIsNull (idIntegrante: Long): PeriodoActivo

    fun existsByIdIdIntegranteAndEndDateIsNull (idIntegrante: Long): Boolean

    fun findAllByEndDateIsNotNull () : List<PeriodoActivo>
}