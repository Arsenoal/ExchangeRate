package com.example.exchangerate.connector

import android.content.Context
import com.example.exchangerate.repo.apicreator.restApiCreatorModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DIModules {
    companion object {
        fun connect(context: Context) {
            startKoin {
                androidContext(context)

                modules(
                    listOf(
                        restApiCreatorModule,
                        ratesModule(context),
                        organizationModule(context))
                )
            }
        }
    }
}