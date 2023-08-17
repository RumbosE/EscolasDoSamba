package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.EC
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface EcRepository: JpaRepository<EC, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO erl_e_c (id_color,id_escuela) VALUES (:color, :escuela)",
        nativeQuery = true)
    fun insertColor( @Param("escuela") escuela: Long, @Param("color") color: Long )

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM erl_e_c WHERE id_color = :color AND id_escuela = :escuela",
        nativeQuery = true)
    fun deleteColor( @Param("escuela") escuela: Long, @Param("color") color: Long )
}