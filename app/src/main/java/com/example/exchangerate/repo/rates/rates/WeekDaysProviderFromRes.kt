package com.example.exchangerate.repo.rates.rates

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.repo.rates.service.weekdaysProvider.WeekdaysProviderService

class WeekDaysProviderFromRes(
    private val weekdaysProviderService: WeekdaysProviderService
): WeekDaysProviderRepo {
    override suspend fun getWeekdays(lang: Lang, key: String) = weekdaysProviderService.getWeekdays(lang, key)
}