package com.example.exchangerate.presentation.organizations

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.exchangerate.business.organization.GetOrganizationParamsByIdUseCase
import com.example.exchangerate.business.weekdays.GetWeekDaysUseCase
import com.example.exchangerate.entity.EN
import com.example.exchangerate.entity.rates.OrganizationParams

class SingleOrganizationViewModel(
        private val getOrganizationParamsByIdUseCase: GetOrganizationParamsByIdUseCase,
        private val getWeekDaysUseCase: GetWeekDaysUseCase
): ViewModel() {

    fun loadOrganizationParams(organizationId: String, organizationName: String): LiveData<Result<OrganizationParams>> = organizationsLiveData(organizationId, organizationName)

    private fun organizationsLiveData(organizationId: String, organizationName: String): LiveData<Result<OrganizationParams>> = liveData {
        try {
            val organizationParams: OrganizationParams = getOrganizationParamsByIdUseCase.getOrganization(organizationId, organizationName, EN)[0]

            val workingHours: List<Pair<String, String>> = organizationParams.workingHours.map { pair ->
                val workingDaysByLang: String = getWeekDaysUseCase.getWorkingDaysBy(EN, pair.first)
                Pair(workingDaysByLang, pair.second)
            }

            organizationParams.workingHours = workingHours

            emit(Result.success(organizationParams))
        } catch (ex: Exception) {
            emit(Result.failure(ex))
        }
    }

}