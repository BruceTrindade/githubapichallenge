package com.bruceenterprises.githubapichallenge.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.formatToBrazilianDate(): String {
    val instant = Instant.parse(this)
    val localDate = instant.toLocalDateTime(TimeZone.UTC).date

    val monthNames = listOf(
        "janeiro", "fevereiro", "março", "abril", "maio", "junho",
        "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"
    )
    val month = monthNames[localDate.monthNumber - 1] // `monthNumber` retorna 1-12

    return "${localDate.dayOfMonth} de $month de ${localDate.year}"
}