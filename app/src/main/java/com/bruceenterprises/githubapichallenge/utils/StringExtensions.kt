package com.bruceenterprises.githubapichallenge.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.isIso8601(): Boolean {
    return try {
        Instant.parse(this)
        true
    } catch (e: Exception) {
        false
    }
}

fun String.formatToBrazilianDate(): String {
    if (!this.isIso8601()) return ""

    val instant = Instant.parse(this)
    val localDate = instant.toLocalDateTime(TimeZone.UTC).date

    val monthNames = listOf(
        "janeiro", "fevereiro", "março", "abril", "maio", "junho",
        "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"
    )
    val month = monthNames[localDate.monthNumber - 1]

    return "${localDate.dayOfMonth} de $month de ${localDate.year}"
}

fun String.shortDescription(): String {
    val maxLength = 100
    return if (this.length > maxLength) {
        this.substring(0, maxLength) + "..."
    } else {
        this
    }
}