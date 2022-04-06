package com.example.farmersapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.example.farmersapp.databinding.ActivitySplashBinding

class OpenScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
        } catch (error: Amplify.AlreadyConfiguredException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }

        Amplify.Auth.fetchAuthSession(
            {

                Handler(Looper.getMainLooper()).postDelayed({
                    if (it.isSignedIn) {
                        startActivity(
                            Intent(
                                this,
                                MainActivity::class.java
                            )
                        )

                    } else {
                        startActivity(
                            Intent(
                                this,
                                IntroActivity::class.java
                            )
                        )
                    }
                    finish()
                }, 3000)

            },
            { error ->
                startActivity(
                    Intent(
                        this,
                        IntroActivity::class.java
                    )
                )
                finish()
            }
        )

    }
}