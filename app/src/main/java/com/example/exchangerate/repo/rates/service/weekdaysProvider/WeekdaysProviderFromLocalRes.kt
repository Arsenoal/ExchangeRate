package com.example.exchangerate.repo.rates.service.weekdaysProvider

import android.content.Context
import com.example.exchangerate.R
import com.example.exchangerate.entity.AM
import com.example.exchangerate.entity.EN
import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.RU

class WeekdaysProviderFromLocalRes(private val context: Context): WeekdaysProviderService {
    override suspend fun getWeekdays(lang: Lang, key: String): String {
        val weekdays: Array<String> = when(lang) {
            EN -> context.resources.getStringArray(R.array.weekDaysEn)
            RU -> context.resources.getStringArray(R.array.weekDaysRu)
            AM -> context.resources.getStringArray(R.array.weekDaysAm)
        }

        return if (key.contains("-")) {
            val startDay = key.split("-")[0].toInt()
            val endDay = key.split("-")[1].toInt()
            val start = weekdays[startDay - 1]
            val end = weekdays[endDay - 1]
            "$start - $end"
        } else {
            weekdays[key.toInt() - 1]
        }

    }
}