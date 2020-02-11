package com.example.exchangerate.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangerate.common.presentation.ActivityConfigs

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityConfigs.setForcePortrait(this)
    }
}