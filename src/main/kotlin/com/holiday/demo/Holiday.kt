package com.holiday.demo

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "holidays")
data class Holiday(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val holidayDate: LocalDate,
    val addedDate: LocalDate
)
