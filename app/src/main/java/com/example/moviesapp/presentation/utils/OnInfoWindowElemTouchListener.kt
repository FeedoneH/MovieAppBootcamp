package com.example.moviesapp.presentation.utils

import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.google.android.gms.maps.model.Marker


abstract class OnInfoWindowElemTouchListener(view: View, bgDrawableNormal: Drawable, bgDrawablePressed: Drawable) : OnTouchListener {
    private var view: View
    private var bgDrawableNormal: Drawable
    private var bgDrawablePressed: Drawable
    private var handler: Handler = Handler()
    private var marker: Marker? = null
    private var pressed = false

    public fun setMarker(marker: Marker?){
        this.marker=marker
    }
    override fun onTouch(vv: View?, event: MotionEvent): Boolean {
        if (0 <= event.x && event.x <= view.getWidth() && 0 <= event.y && event.y <= view.getHeight()) {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> startPress()
                MotionEvent.ACTION_UP -> handler.postDelayed(confirmClickRunnable, 150)
                MotionEvent.ACTION_CANCEL -> endPress()
                else -> {
                }
            }
        } else {
            // If the touch goes outside of the view's area
            // (like when moving finger out of the pressed button)
            // just release the press
            endPress()
        }
        return false
    }

    private fun startPress() {
        if (!pressed) {
            pressed = true
            handler.removeCallbacks(confirmClickRunnable)
            view.setBackground(bgDrawablePressed)
            if (marker != null) marker!!.showInfoWindow()
        }
    }

    private fun endPress(): Boolean {
        return if (pressed) {
            pressed = false
            handler.removeCallbacks(confirmClickRunnable)
            view.setBackground(bgDrawableNormal)
            if (marker != null) marker!!.showInfoWindow()
            true
        } else false
    }

    private var confirmClickRunnable = Runnable {
        if (endPress()) {
            onClickConfirmed(view, marker)
        }
    }

    /**
     * This is called after a successful click
     */
    protected abstract fun onClickConfirmed(v: View?, marker: Marker?)

    init {
        this.view = view
        this.marker=marker
        this.bgDrawableNormal = bgDrawableNormal
        this.bgDrawablePressed = bgDrawablePressed
    }
}