package com.bruceenterprises.githubapichallenge.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun `formatToBrazilianDate should return formatted date correctly`() {
        val inputDate = "2024-12-24T14:02:26Z"
        val expectedDate = "24 de dezembro de 2024"

        val formattedDate = inputDate.formatToBrazilianDate()

        assertEquals(expectedDate, formattedDate)
    }

    @Test
    fun `formatToBrazilianDate should handle different months correctly`() {
        val testCases = mapOf(
            "2024-01-01T00:00:00Z" to "1 de janeiro de 2024",
            "2024-02-14T12:30:00Z" to "14 de fevereiro de 2024",
            "2024-03-15T08:45:00Z" to "15 de março de 2024",
            "2024-04-30T23:59:59Z" to "30 de abril de 2024",
            "2024-05-25T10:00:00Z" to "25 de maio de 2024",
            "2024-06-10T06:15:00Z" to "10 de junho de 2024",
            "2024-07-04T04:00:00Z" to "4 de julho de 2024",
            "2024-08-19T16:20:00Z" to "19 de agosto de 2024",
            "2024-09-07T11:59:00Z" to "7 de setembro de 2024",
            "2024-10-31T22:45:00Z" to "31 de outubro de 2024",
            "2024-11-11T03:30:00Z" to "11 de novembro de 2024",
            "2024-12-25T18:00:00Z" to "25 de dezembro de 2024"
        )

        for ((input, expected) in testCases) {
            assertEquals(expected, input.formatToBrazilianDate())
        }
    }

    @Test
    fun `formatToBrazilianDate should empty string for invalid date`() {
        val invalidDate = "invalid-date-string"

        assertEquals("", invalidDate.formatToBrazilianDate())
    }
}
