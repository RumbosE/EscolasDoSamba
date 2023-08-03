package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Lugar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LugarRepository : JpaRepository<Lugar, Long> {

    @Query("SELECT * FROM erl_lugar WHERE tipo = 'EST'",
            nativeQuery = true)
    fun getEstados(): List<Lugar>

    @Query("SELECT * FROM erl_lugar WHERE tipo = 'REG'",
        nativeQuery = true)
    fun getRegiones(): List<Lugar>



}