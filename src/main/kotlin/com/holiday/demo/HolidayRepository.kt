package com.holiday.demo

// Repository file: HolidayRepository.kt
import org.springframework.data.jpa.repository.JpaRepository

interface HolidayRepository : JpaRepository<Holiday, Long>


