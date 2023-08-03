package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Escuela
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EscuelaRepository: JpaRepository<Escuela, Long>{

}