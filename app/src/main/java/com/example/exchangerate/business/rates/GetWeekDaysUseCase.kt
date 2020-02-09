package com.example.exchangerate.business.rates

import com.example.exchangerate.entity.Lang

interface GetWeekDaysUseCase {
    suspend fun getWorkingDaysBy(lang: Lang, key: String): String
}