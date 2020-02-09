package com.example.exchangerate.repo.rates.service.weekdaysProvider

import com.example.exchangerate.entity.Lang

interface WeekdaysProviderService {
    suspend fun getWeekdays(lang: Lang, key: String): String
}