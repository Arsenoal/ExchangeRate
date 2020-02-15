package com.example.exchangerate.business.weekdays

import com.example.exchangerate.entity.Lang

interface GetWeekDaysUseCase {
    suspend fun getWorkingDaysBy(lang: Lang, key: String): String
}