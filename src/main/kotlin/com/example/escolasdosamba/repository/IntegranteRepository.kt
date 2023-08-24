package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Integrante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IntegranteRepository: JpaRepository<Integrante, Long> {

    fun existsIntegranteByIdentityDocumentEquals(identityDocument: String): Boolean

}