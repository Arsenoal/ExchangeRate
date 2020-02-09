package com.example.exchangerate.repo.rates.rates

import com.example.exchangerate.entity.Lang

interface WeekDaysProviderRepo {
    suspend fun getWeekdays(lang: Lang, key: String): String
}