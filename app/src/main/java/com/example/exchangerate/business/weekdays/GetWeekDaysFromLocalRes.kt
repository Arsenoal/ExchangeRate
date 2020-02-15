package com.example.exchangerate.business.weekdays

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.repo.weekdays.WeekDaysProviderRepo

class GetWeekDaysFromLocalRes(
    private val weekDaysProviderRepo: WeekDaysProviderRepo
): GetWeekDaysUseCase {
    override suspend fun getWorkingDaysBy(lang: Lang, key: String) = weekDaysProviderRepo.getWeekdays(lang, key)
}