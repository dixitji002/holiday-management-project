package com.holiday.demo



import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class HolidayRequest(
    val name: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val holidayDate: LocalDate
)
