package com.vanitygoods.tudu.util

import org.threeten.bp.format.DateTimeFormatter


object DateTimeFormats {

    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy")
    val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val longDateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d")
}