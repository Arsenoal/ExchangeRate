package com.example.exchangerate.business.rates

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.repo.rates.rates.WeekDaysProviderRepo

class GetWeekDaysFromLocalRes(
    private val weekDaysProviderRepo: WeekDaysProviderRepo
): GetWeekDaysUseCase {
    override suspend fun getWorkingDaysBy(lang: Lang, key: String) = weekDaysProviderRepo.getWeekdays(lang, key)
}