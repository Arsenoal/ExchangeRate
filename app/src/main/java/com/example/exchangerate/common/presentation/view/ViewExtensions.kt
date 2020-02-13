package com.example.exchangerate.common.presentation.view

import android.animation.ObjectAnimator
import android.view.View

fun View.appendToolBarHeight() {
    val statusBarHeight = context.getStatusBarHeight()

    layoutParams.height += statusBarHeight
}

fun View.slideDown() = slideDown(400)

fun View.slideDown(duration: Long) {
    val animation = ObjectAnimator.ofFloat(this, "translationY", height.toFloat())
    animation.duration = duration
    animation.start()
}

fun View.slideUp() = slideUp(400)

fun View.slideUp(duration: Long) {
    val animation = ObjectAnimator.ofFloat(this, "translationY", 0f, -height.toFloat())
    animation.duration = duration
    animation.start()
}