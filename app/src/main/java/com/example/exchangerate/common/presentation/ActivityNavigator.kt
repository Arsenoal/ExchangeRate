package com.example.exchangerate.common.presentation

import android.content.Intent
import android.os.Bundle
import com.example.exchangerate.presentation.BaseActivity

object ActivityNavigator {
    fun navigate(activity: BaseActivity, targetActivityClassName: Class<out BaseActivity>) {
        activity.run {
            startActivity(Intent(this, targetActivityClassName))
        }
    }

    fun navigate(activity: BaseActivity, targetActivityClassName: Class<out BaseActivity>, extras: Bundle) {
        activity.run {
            val intent = Intent(this, targetActivityClassName)
            intent.putExtras(extras)
            startActivity(intent)
        }
    }

    fun navigateAndFinishCurrent(activity: BaseActivity, targetActivityClassName: Class<out BaseActivity>) {
        activity.run {
            startActivity(Intent(this, targetActivityClassName))
            finish()
        }
    }
}