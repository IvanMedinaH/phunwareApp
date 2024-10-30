package com.kotlin.phunwareapp.data.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

fun formatToUserTimeZone(gmtDate: String): String {
    // Parse the date string into a ZonedDateTime
    val zonedDateTime = ZonedDateTime.parse(gmtDate)

    // Get the user's timezone (replace with user's timezone as needed)
    val userZone = TimeZone.getDefault().toZoneId()

    // Convert the GMT date to user's timezone
    val userDateTime = zonedDateTime.withZoneSameInstant(userZone)

    // Format the date into a readable string
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
    return userDateTime.format(formatter)
}
