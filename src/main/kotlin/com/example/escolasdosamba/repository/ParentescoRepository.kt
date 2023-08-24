package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Parentesco
import com.example.escolasdosamba.dao.ParentescoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParentescoRepository: JpaRepository<Parentesco, ParentescoId>{

    fun existsParentescoByIdIdIntegrante1AndIdIdIntegrante2(idIntegrante1: Long, idIntegrante2: Long): Boolean
}