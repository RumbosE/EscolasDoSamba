package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Ganador
import com.example.escolasdosamba.dao.GanadorId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GanadorRepository: JpaRepository<Ganador, GanadorId > {


}