package com.example.exchangerate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangerate.common.presentation.ActivityOrientation

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityOrientation.setForcePortrait(this)
    }
}