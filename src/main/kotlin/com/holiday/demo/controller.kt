package com.holiday.demo


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/api/holidays")
class HolidayController @Autowired constructor(private val holidayService: HolidayService) {

    @PostMapping
    fun addHoliday(@RequestBody request: HolidayRequest) {
        holidayService.addHoliday(request)
    }

    @GetMapping
    fun getHolidays(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) fromHolidayDate: LocalDate?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) toHolidayDate: LocalDate?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) fromAddDate: LocalDate?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) toAddDate: LocalDate?,
        @RequestParam(required = false) sortBy: String?
    ): List<Holiday> {
        return holidayService.getHolidays(fromHolidayDate, toHolidayDate, fromAddDate, toAddDate, sortBy)
    }

    @GetMapping("/working-days")
    fun getWorkingDays(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) fromDate: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) toDate: LocalDate
    ): List<LocalDate> {
        return holidayService.getWorkingDays(fromDate, toDate)
    }

    @GetMapping("/next-working-day")
    fun getNextWorkingDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): LocalDate {
        return holidayService.getNextWorkingDay(date)
    }
}
