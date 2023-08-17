package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Titulo
import com.example.escolasdosamba.dao.TituloId
import org.springframework.data.jpa.repository.JpaRepository
import java.sql.Date
import java.util.*

interface TituloRepository: JpaRepository<Titulo, TituloId> {

    fun findByIdIdEscuela(idEscuela: Long): List<Titulo>

}