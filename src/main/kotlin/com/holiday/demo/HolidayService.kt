package com.holiday.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate

@Service
class HolidayService @Autowired constructor(private val holidayRepository: HolidayRepository) {

    fun addHoliday(request: HolidayRequest) {
        val holiday = Holiday(
            name = request.name,
            holidayDate = request.holidayDate,
            addedDate = LocalDate.now()
        )
        holidayRepository.save(holiday)
    }

    fun getHolidays(
        fromHolidayDate: LocalDate?,
        toHolidayDate: LocalDate?,
        fromAddDate: LocalDate?,
        toAddDate: LocalDate?,
        sortBy: String?
    ): List<Holiday> {
        val holidays = holidayRepository.findAll().filter {
            (fromHolidayDate == null || !it.holidayDate.isBefore(fromHolidayDate)) &&
                    (toHolidayDate == null || !it.holidayDate.isAfter(toHolidayDate)) &&
                    (fromAddDate == null || !it.addedDate.isBefore(fromAddDate)) &&
                    (toAddDate == null || !it.addedDate.isAfter(toAddDate))
        }
        return when (sortBy) {
            "addDate" -> holidays.sortedBy { it.addedDate }
            "holidayDate" -> holidays.sortedBy { it.holidayDate }
            else -> holidays
        }
    }

    fun getWorkingDays(fromDate: LocalDate, toDate: LocalDate): List<LocalDate> {
        val holidays = holidayRepository.findAll().map { it.holidayDate }.toSet()
        return fromDate.datesUntil(toDate.plusDays(1)).filter {
            it.dayOfWeek != DayOfWeek.SATURDAY && it.dayOfWeek != DayOfWeek.SUNDAY && !holidays.contains(it)
        }.toList()
    }

    fun getNextWorkingDay(date: LocalDate): LocalDate {
        var nextDate = date.plusDays(1)
        val holidays = holidayRepository.findAll().map { it.holidayDate }.toSet()
        while (nextDate.dayOfWeek == DayOfWeek.SATURDAY || nextDate.dayOfWeek == DayOfWeek.SUNDAY || holidays.contains(nextDate)) {
            nextDate = nextDate.plusDays(1)
        }
        return nextDate
    }
}
