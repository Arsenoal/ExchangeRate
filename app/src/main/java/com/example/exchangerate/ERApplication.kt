package com.example.exchangerate

import android.app.Application
import com.example.exchangerate.common.ERLogger
import com.example.exchangerate.connector.DIModules

class ERApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ERLogger.init()

        DIModules.connect(applicationContext)
    }
}