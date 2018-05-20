package com.vanitygoods.tudu.data

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId


class Converters {
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): Long {
        return dateTime?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli() ?: 0L
    }

    @TypeConverter
    fun toLocalDateTime(epochMillis: Long): LocalDateTime? {
        return if (epochMillis == 0L) null else LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault())
    }
}