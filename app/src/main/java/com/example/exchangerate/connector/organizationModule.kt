package com.example.exchangerate.connector

import android.content.Context
import com.example.exchangerate.business.organization.GetOrganizationParamsByIdFromRetrofitApi
import com.example.exchangerate.business.organization.GetOrganizationParamsByIdUseCase
import com.example.exchangerate.business.weekdays.GetWeekDaysFromLocalRes
import com.example.exchangerate.business.weekdays.GetWeekDaysUseCase
import com.example.exchangerate.presentation.organizations.SingleOrganizationViewModel
import com.example.exchangerate.repo.service.apicreator.RestApiCreator
import com.example.exchangerate.repo.organization.OrganizationParamsByRetrofitApi
import com.example.exchangerate.repo.organization.OrganizationParamsRepo
import com.example.exchangerate.repo.weekdays.WeekDaysProviderFromRes
import com.example.exchangerate.repo.weekdays.WeekDaysProviderRepo
import com.example.exchangerate.repo.organization.service.OrganizationServiceRetrofit
import com.example.exchangerate.repo.weekdays.service.WeekdaysProviderFromLocalRes
import com.example.exchangerate.repo.weekdays.service.WeekdaysProviderService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun organizationModule(context: Context) = module {
    single {
        get<RestApiCreator>().createApiService(OrganizationServiceRetrofit::class.java)
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