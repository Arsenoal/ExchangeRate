package com.example.exchangerate.repo.weekdays.service

import com.example.exchangerate.entity.Lang

interface WeekdaysProviderService {
    suspend fun getWeekdays(lang: Lang, key: String): String
}