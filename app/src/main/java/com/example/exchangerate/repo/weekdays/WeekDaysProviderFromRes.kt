package com.example.exchangerate.repo.weekdays

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.repo.weekdays.service.WeekdaysProviderService

class WeekDaysProviderFromRes(
    private val weekdaysProviderService: WeekdaysProviderService
): WeekDaysProviderRepo {
    override suspend fun getWeekdays(lang: Lang, key: String) = weekdaysProviderService.getWeekdays(lang, key)
}