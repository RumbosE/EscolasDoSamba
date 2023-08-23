package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Ganador
import com.example.escolasdosamba.dao.GanadorId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.sql.Date

@Repository
interface GanadorRepository: JpaRepository<Ganador, GanadorId > {

    @Query(value = "insert into erl_ganador( id_premio,id_escuela, año) values (?1, ?2, ?3)",
        nativeQuery = true)
    fun insertGanador(  idPremio:Long, idEscuela: Long, year: Date): Ganador

}