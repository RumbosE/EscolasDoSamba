package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Lugar
import com.example.escolasdosamba.dto.lugar.LugarDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LugarRepository : JpaRepository<Lugar, Long> {

    @Query("SELECT l2.id ,l2.nombre, l1.id,l1.nombre  FROM erl_lugar l1, erl_lugar l2 WHERE l1.id= l2.padre_id",
            nativeQuery = true
    )
    fun getAllCiudades(): List<LugarDto>
}