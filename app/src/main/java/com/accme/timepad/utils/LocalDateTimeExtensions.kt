package com.accme.timepad.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.isToday(): Boolean {
    return this.toLocalDate().isEqual(LocalDate.now())
}

fun LocalDateTime.isTomorrow(): Boolean {
    return LocalDate.now().plusDays(1).isEqual(this.toLocalDate())
}

fun LocalDateTime.getNiceDate(): String {
    return this.format(DateTimeFormatter.ofPattern(Constants.FULL_DATE_FORMAT))
}

fun LocalDateTime.getNiceTime(): String {
    return this.format(DateTimeFormatter.ofPattern(Constants.TIME_FORMAT))
}

fun LocalTime.getNiceCountTime(): String {
    return this.format(DateTimeFormatter.ofPattern(Constants.COUNT_TIMER_FORMAT))
}
