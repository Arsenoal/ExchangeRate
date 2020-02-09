package com.example.exchangerate.connector

import android.content.Context
import com.example.exchangerate.business.currency.GetCurrenciesListFromLocalArrayRes
import com.example.exchangerate.business.currency.GetCurrenciesListUseCase
import com.example.exchangerate.business.rates.GetRatesFromApi
import com.example.exchangerate.business.rates.GetRatesUseCase
import com.example.exchangerate.presentation.rates.AllRatesViewModel
import com.example.exchangerate.repo.apicreator.RestApiCreator
import com.example.exchangerate.repo.rates.currency.CurrenciesListProviderRepo
import com.example.exchangerate.repo.rates.currency.GetLocalAvailableCurrencies
import com.example.exchangerate.repo.rates.rates.GetRatesListByRetrofitApi
import com.example.exchangerate.repo.rates.rates.RatesListRepo
import com.example.exchangerate.repo.rates.service.currency.CurrenciesFromLocalRes
import com.example.exchangerate.repo.rates.service.currency.CurrenciesService
import com.example.exchangerate.repo.rates.service.rates.RatesListService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun ratesModule(context: Context) = module {
    single {
        get<RestApiCreator>().createApiService(RatesListService::class.java)
    }

    single<RatesListRepo> {
        GetRatesListByRetrofitApi(get())
    }

    single<GetRatesUseCase> {
        GetRatesFromApi(get())
    }

    single<CurrenciesService> {
        CurrenciesFromLocalRes(context)
    }

    single<CurrenciesListProviderRepo> {
        GetLocalAvailableCurrencies(get())
    }

    single<GetCurrenciesListUseCase> {
        GetCurrenciesListFromLocalArrayRes(get())
    }

    viewModel {
        AllRatesViewModel(get(), get())
    }
}