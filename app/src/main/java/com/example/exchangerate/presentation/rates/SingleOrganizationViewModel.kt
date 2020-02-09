package com.example.exchangerate.presentation.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.business.rates.GetOrganizationParamsByIdUseCase
import com.example.exchangerate.business.rates.GetWeekDaysUseCase
import com.example.exchangerate.entity.EN
import com.example.exchangerate.entity.rates.OrganizationParams
import kotlinx.coroutines.launch

class SingleOrganizationViewModel(
    private val getOrganizationParamsByIdUseCase: GetOrganizationParamsByIdUseCase,
    private val getWeekDaysUseCase: GetWeekDaysUseCase
): ViewModel() {

    val organizationParamsAcquiredLiveData = MutableLiveData<OrganizationParams>()

    val organizationParamsAcquisitionFailLiveData = MutableLiveData<Any>()

    fun loadOrganizationParams(
        organizationId: String,
        organizationName: String) {
        viewModelScope.launch {
            try {
                val organizationParams
                        = getOrganizationParamsByIdUseCase.getOrganization(organizationId, organizationName, EN)[0]

                val workingHours = organizationParams.workingHours.map { pair ->
                    Pair(getWeekDaysUseCase.getWorkingDaysBy(EN, pair.first), pair.second)
                }

                organizationParams.workingHours = workingHours

                organizationParamsAcquiredLiveData.value = organizationParams
            } catch (ex: Exception) {
                organizationParamsAcquisitionFailLiveData.value = Any()
            }
        }
    }

}