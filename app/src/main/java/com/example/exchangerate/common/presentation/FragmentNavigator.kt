package com.example.exchangerate.common.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.exchangerate.presentation.base.BaseActivity

object FragmentNavigator {
    fun addFragment(activity: BaseActivity, fragment: Fragment, @IdRes containerViewId: Int) {
        activity.run {
            supportFragmentManager.beginTransaction().add(containerViewId, fragment, null).commit()
        }
    }
}