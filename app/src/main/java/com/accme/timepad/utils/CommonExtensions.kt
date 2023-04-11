package com.accme.timepad.utils

import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

fun Long.toLocalTime(): LocalTime {
    val duration = Duration.parseIsoString((this.seconds).toIsoString())
    return LocalTime.ofSecondOfDay(duration.inWholeSeconds)
}
