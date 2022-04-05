package com.example.farmersapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.farmersapp.databinding.ActivitySplashBinding

class OpenScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler: Boolean = Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    this,
                    IntroActivity::class.java
                )
            )
            finish()
        }, 5000)


    }
}