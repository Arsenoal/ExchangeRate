package com.example.exchangerate.connector

import com.example.exchangerate.presentation.common.navigator.NavigatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigatorModule = module {
    viewModel {
        NavigatorViewModel()
    }
}