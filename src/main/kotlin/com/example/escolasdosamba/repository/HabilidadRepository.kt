package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Habilidad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HabilidadRepository: JpaRepository<Habilidad, Long> {
}