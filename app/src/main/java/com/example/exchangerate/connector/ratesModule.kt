package com.example.exchangerate.connector

import android.content.Context
import com.example.exchangerate.business.currency.GetCurrenciesListFromLocalArrayRes
import com.example.exchangerate.business.currency.GetCurrenciesListUseCase
import com.example.exchangerate.business.paymentvariants.GetPaymentVariantsFromLocalRes
import com.example.exchangerate.business.paymentvariants.GetPaymentVariantsUseCase
import com.example.exchangerate.business.rates.GetRatesFromApi
import com.example.exchangerate.business.rates.GetRatesUseCase
import com.example.exchangerate.presentation.organizations.organizations.OrganizationsViewModel
import com.example.exchangerate.repo.service.apicreator.RestApiCreator
import com.example.exchangerate.repo.currency.CurrenciesListProviderRepo
import com.example.exchangerate.repo.currency.GetLocalAvailableCurrencies
import com.example.exchangerate.repo.rates.GetRatesListByRetrofitApi
import com.example.exchangerate.repo.rates.RatesListRepo
import com.example.exchangerate.repo.currency.service.CurrenciesFromLocalRes
import com.example.exchangerate.repo.currency.service.CurrenciesService
import com.example.exchangerate.repo.paymentvariants.GetLocalAvailablePaymentVariants
import com.example.exchangerate.repo.paymentvariants.PaymentVariantsProviderRepo
import com.example.exchangerate.repo.paymentvariants.service.PaymentVariantsProviderFromLocalRes
import com.example.exchangerate.repo.paymentvariants.service.PaymentVariantsProviderService
import com.example.exchangerate.repo.rates.service.RatesListServiceRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun ratesModule(context: Context) = module {
    single {
        get<RestApiCreator>().createApiService(RatesListServiceRetrofit::class.java)
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

    single<PaymentVariantsProviderService> {
        PaymentVariantsProviderFromLocalRes(context)
    }

    single<PaymentVariantsProviderRepo> {
        GetLocalAvailablePaymentVariants(get())
    }

    single<GetPaymentVariantsUseCase> {
        GetPaymentVariantsFromLocalRes(get())
    }

    viewModel {
        OrganizationsViewModel(get(), get(), get())
    }
}