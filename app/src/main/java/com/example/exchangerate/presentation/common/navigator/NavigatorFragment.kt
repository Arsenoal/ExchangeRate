package com.example.exchangerate.presentation.common.navigator

import android.graphics.*
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.exchangerate.R
import com.example.exchangerate.common.presentation.view.getStatusBarHeight
import com.example.exchangerate.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_navigator.*

private const val COLOR_TAB_NOT_SELECTED = R.color.white
private const val COLOR_TAB_SELECTED = R.color.whiteTransparentSixtyPercent

class NavigatorFragment: BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_navigator

    override fun onLayoutReady(view: View, savedInstanceState: Bundle?) {
        setupTabLayout()
        setupTopPadding(view)
    }

    private fun setupTabLayout() {
        topNavigatorView.addOnTabSelectedListener(object: TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tabItem: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tabItem: TabLayout.Tab?) {
                context?.let { context ->
                    tabItem?.icon?.run {
                        colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, COLOR_TAB_SELECTED), PorterDuff.Mode.DST_IN)
                    }
                }
            }

            override fun onTabSelected(tabItem: TabLayout.Tab?) {
                context?.let { context ->
                    tabItem?.icon?.run {
                        colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, COLOR_TAB_NOT_SELECTED), PorterDuff.Mode.DST_IN)
                    }
                }
            }
        })

        topNavigatorView.getTabAt(1)?.select()

        val tabStrip = topNavigatorView.getChildAt(0) as LinearLayout
        val childCount = tabStrip.childCount

        for(i in 0 until childCount) {
            tabStrip.getChildAt(i).setOnTouchListener { v, event -> true }
        }
    }

    private fun setupTopPadding(view: View) {
        view.run {
            context.getStatusBarHeight()

            setPadding(paddingLeft, paddingTop + context.getStatusBarHeight(), paddingRight, paddingBottom)
        }
    }

    companion object {
        fun newInstance() = NavigatorFragment()
    }
}