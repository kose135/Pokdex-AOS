package com.base.pokedex.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object TimeUtil {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val currentTime: String
        get() {
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.format(formatter)
        }

    fun isDateDifferenceOverOneDay(date: String?): Boolean {
        if (date == null) return false

        val dateTime = LocalDateTime.parse(date, formatter)
        val currentTime = LocalDateTime.parse(currentTime, formatter)

        val daysDifference = ChronoUnit.DAYS.between(dateTime, currentTime)

        return (daysDifference >= 1)
    }
}