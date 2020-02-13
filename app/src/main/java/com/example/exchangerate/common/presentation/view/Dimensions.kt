package com.example.exchangerate.common.presentation.view

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue



fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Float.toPx(resources: Resources): Float {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this,
            resources.displayMetrics)
}