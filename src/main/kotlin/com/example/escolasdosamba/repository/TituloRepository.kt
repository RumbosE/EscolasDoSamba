package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Titulo
import com.example.escolasdosamba.dao.TituloId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.sql.Date
import java.util.*

interface TituloRepository: JpaRepository<Titulo, TituloId> {


}