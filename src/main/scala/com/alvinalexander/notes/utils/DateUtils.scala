package com.alvinalexander.notes.utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}
import java.time.Year

object DateUtils

    val DATE_FORMAT = "EEE, MMM dd, yyyy h:mm a"

    def convertDateToString(d: Date): String =
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        dateFormat.format(d)

    def convertStringToDate(s: String): Date =
        val formatter = SimpleDateFormat(DATE_FORMAT)
        formatter.parse(s)

    def getCurrentDate: Date = Calendar.getInstance.getTime

    def currentMonthAbbreviated: String =
        SimpleDateFormat("MMM")
            .format(Calendar.getInstance.getTime)
            .toLowerCase

    def currentYear = Year.now.getValue

