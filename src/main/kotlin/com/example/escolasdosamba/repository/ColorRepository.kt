package com.example.escolasdosamba.repository

import com.example.escolasdosamba.dao.Color
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ColorRepository :JpaRepository<Color, Long> {

}