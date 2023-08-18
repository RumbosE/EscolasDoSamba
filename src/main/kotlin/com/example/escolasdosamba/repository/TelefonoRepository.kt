package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Telefono
import com.example.escolasdosamba.dao.TelefonoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TelefonoRepository: JpaRepository<Telefono, TelefonoId>{

}