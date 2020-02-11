package com.example.exchangerate.common.presentation

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE

object ActivityConfigs {
    fun setForcePortrait(activity: AppCompatActivity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun setFullScreen(activity: AppCompatActivity) {
        activity.window.run {
            decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}