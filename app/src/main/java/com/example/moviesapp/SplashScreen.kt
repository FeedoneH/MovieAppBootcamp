package com.example.moviesapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.moviesapp.presentation.login.LogInFragment


class SplashScreen : Activity() {
    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH = 1000

    /** Called when the activity is first created.  */
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(com.example.moviesapp.R.layout.splash)
        Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this@SplashScreen, MainActivity::class.java)
            this@SplashScreen.startActivity(mainIntent)
            this@SplashScreen.finish()
        }, 1000)
    }
}