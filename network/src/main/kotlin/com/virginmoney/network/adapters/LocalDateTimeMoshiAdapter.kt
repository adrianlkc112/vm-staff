package com.virginmoney.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

internal class LocalDateTimeMoshiAdapter {
    private val dateTimeFormatter =
        DateTimeFormatter.ofPattern(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.ENGLISH,
        )

    @FromJson
    fun fromJson(dateTimeString: String): LocalDateTime =
        LocalDateTime.parse(
            dateTimeString,
            dateTimeFormatter,
        )

    @ToJson
    fun toJson(localDateTime: LocalDateTime): String = localDateTime.format(dateTimeFormatter)
}
