package the.beginning.of.the.end.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import the.beginning.of.the.end.utils.TimeExt.minus
import the.beginning.of.the.end.utils.TimeExt.now
import the.beginning.of.the.end.utils.TimeExt.plus
import kotlin.time.Duration


object TimeExt {


    fun LocalDateTime.Companion.now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun LocalDate.Companion.now(): LocalDate {
        return LocalDateTime.now().date
    }

    fun LocalTime.Companion.now(): LocalTime {
        return LocalDateTime.now().time
    }

    fun LocalTime.Companion.min(): LocalTime {
        return LocalTime(0, 0)
    }

    fun LocalTime.Companion.max(): LocalTime {
        return LocalTime(23, 59, 59, 999999999)
    }

    fun LocalDate.atStartOfDay(): LocalDateTime {
        return LocalDateTime(this, LocalTime.min())
    }

    fun LocalDate.atEndOfDay(): LocalDateTime {
        return LocalDateTime(this, LocalTime.max())
    }

    fun LocalDateTime.plus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
        val timeZone = TimeZone.currentSystemDefault()
        return this.toInstant(timeZone)
            .plus(value, unit)
            .toLocalDateTime(timeZone)
    }

    fun LocalDateTime.minus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
        val timeZone = TimeZone.currentSystemDefault()
        return this.toInstant(timeZone)
            .minus(value, unit)
            .toLocalDateTime(timeZone)
    }

    fun LocalDateTime.minus(other: LocalDateTime): Duration {
        val timeZone = TimeZone.currentSystemDefault()
        return this.toInstant(timeZone)
            .minus(other.toInstant(timeZone))
    }
    /*
    val now = LocalDateTime.now()

    val dateNow = with(now){
        "${dayOfMonth}/${monthNumber}/$year - $hour : $minute : $second "
    }

    val datetime: LocalDateTime = LocalDateTime.now()
    val dateTimeAfterOneHour: LocalDateTime = datetime.plus(1, DateTimeUnit.HOUR)
    val dateTimeAfterOneDay: LocalDateTime = datetime.plus(24, DateTimeUnit.HOUR)
    val dateTime30MinEarlier: LocalDateTime = datetime.minus(30, DateTimeUnit.MINUTE)
     */

}

fun main() {

    val now = LocalDateTime.now()
    val currentTime: LocalDateTime = now.plus(1, DateTimeUnit.SECOND)
    val remainingTime = LocalDateTime(2024, 3, 5, 22, 54, 54)

    val remainingInWhole = remainingTime.minus(currentTime)

    val s = with(remainingInWhole) {
        "$inWholeDays $inWholeHours $inWholeMinutes $inWholeSeconds"
    }

    println(s)


}