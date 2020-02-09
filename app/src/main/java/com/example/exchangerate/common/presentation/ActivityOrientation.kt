package com.example.exchangerate.common.presentation

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity

object ActivityOrientation {
    fun setForcePortrait(activity: AppCompatActivity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}