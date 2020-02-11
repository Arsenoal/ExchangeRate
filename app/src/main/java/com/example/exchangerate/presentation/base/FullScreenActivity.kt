package com.example.exchangerate.presentation.base

import android.os.Bundle
import com.example.exchangerate.common.presentation.ActivityConfigs

abstract class FullScreenActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityConfigs.setFullScreen(this)
    }
}