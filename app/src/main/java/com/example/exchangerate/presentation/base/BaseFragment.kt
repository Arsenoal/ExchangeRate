package com.example.exchangerate.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.exchangerate.common.ERLogger

private const val FRAGMENT_LIFECYCLE = "Fragment ===> "

abstract class  BaseFragment: Fragment() {

    private val mTag = this.javaClass.simpleName

    @LayoutRes abstract fun getLayoutId(): Int

    abstract fun onLayoutReady(view: View, savedInstanceState: Bundle?)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE on attach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE on create")
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE on create view")
        return  inflater.inflate(getLayoutId(), container, false)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE view created")
        onLayoutReady(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE activity created")
    }

    override fun onStart() {
        super.onStart()
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE started")
    }

    override fun onResume() {
        super.onResume()
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE resumed")
    }

    override fun onDetach() {
        super.onDetach()
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE on detach")
    }

    override fun onDestroy() {
        super.onDestroy()
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE on destroy")
    }

    override fun onStop() {
        super.onStop()
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE on stop")
    }

    override fun onPause() {
        super.onPause()
        ERLogger.i(mTag, "$FRAGMENT_LIFECYCLE on pausef")
    }
}