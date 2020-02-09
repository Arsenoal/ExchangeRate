package com.example.exchangerate.connector

import android.content.Context
import com.example.exchangerate.business.rates.GetOrganizationParamsByIdFromRetrofitApi
import com.example.exchangerate.business.rates.GetOrganizationParamsByIdUseCase
import com.example.exchangerate.business.rates.GetWeekDaysFromLocalRes
import com.example.exchangerate.business.rates.GetWeekDaysUseCase
import com.example.exchangerate.presentation.rates.SingleOrganizationViewModel
import com.example.exchangerate.repo.apicreator.RestApiCreator
import com.example.exchangerate.repo.rates.organization.OrganizationParamsByRetrofitApi
import com.example.exchangerate.repo.rates.organization.OrganizationParamsRepo
import com.example.exchangerate.repo.rates.rates.WeekDaysProviderFromRes
import com.example.exchangerate.repo.rates.rates.WeekDaysProviderRepo
import com.example.exchangerate.repo.rates.service.organization.OrganizationService
import com.example.exchangerate.repo.rates.service.weekdaysProvider.WeekdaysProviderFromLocalRes
import com.example.exchangerate.repo.rates.service.weekdaysProvider.WeekdaysProviderService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun organizationModule(context: Context) = module {
    single {
        get<RestApiCreator>().createApiService(OrganizationService::class.java)
    }

    single<OrganizationParamsRepo> {
        OrganizationParamsByRetrofitApi(get())
    }

    single<GetOrganizationParamsByIdUseCase> {
        GetOrganizationParamsByIdFromRetrofitApi(get())
    }

    single<WeekdaysProviderService> {
        WeekdaysProviderFromLocalRes(context)
    }

    single<WeekDaysProviderRepo> {
        WeekDaysProviderFromRes(get())
    }

    single<GetWeekDaysUseCase> {
        GetWeekDaysFromLocalRes(get())
    }

    viewModel {
        SingleOrganizationViewModel(get(), get())
    }
}