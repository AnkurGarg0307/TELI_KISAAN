package com.example.farmersapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.example.farmersapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var isLogin: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.haveOrNot.setOnClickListener(View.OnClickListener {
            setUpLoginSignIn()
        })
        binding.loginSignup.setOnClickListener(View.OnClickListener {
            if (isLogin) signIN(
                binding.nameInput.text.toString(), binding.passwordInput.text.toString()
            )
            else signUp(
                binding.emailInput.text.toString(), binding.nameInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        })
    }

    private fun setUpLoginSignIn() {
        if (isLogin) {
            isLogin = false
            binding.emailInputLayout.visibility = View.VISIBLE
            binding.loginSignup.text = "SIGNUP"
            binding.haveOrNot.text = "Already Have a account ? Login"
            binding.forgot.visibility = View.GONE
        } else {
            isLogin = true
            binding.emailInputLayout.visibility = View.GONE
            binding.loginSignup.text = "LOGIN"
            binding.haveOrNot.text = "Don't have an account ? Signup"
            binding.forgot.visibility = View.VISIBLE
        }
    }

    private fun signUp(email: String, username: String, pass: String) {
        binding.progressBar.visibility = View.VISIBLE
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .userAttribute(AuthUserAttributeKey.name(), username)
            .build()
        Amplify.Auth.signUp(username, pass, options,
            { result ->
                if (result.isSignUpComplete) {
                    //signIN(username,pass)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.i("AuthQuickstart", "Sign in not complete")
                }
            },
            { Log.i("AuthQuickStart", "Sign up error: $it") }
        )
    }

    private fun confirmSignUp(username: String, code: String) {
        Amplify.Auth.confirmSignUp(
            username, code,
            { result ->
                if (result.isSignUpComplete) {
                    Log.i("AuthQuickstart", "Confirm signUp succeeded")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.i("AuthQuickstart", "Confirm sign up not complete")

                }
            },
            { Log.i("AuthQuickStart", "Sign up error: $it") }
        )
    }

    private fun signIN(username: String, pass: String) {
        binding.progressBar.visibility = View.VISIBLE
        Amplify.Auth.signIn(username, pass,
            { result ->
                if (result.isSignInComplete) {
                    Log.i("AuthQuickstart", "Sign in succeeded")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.i("AuthQuickstart", "Sign in not complete")
                }
            },
            { Log.i("AuthQuickStart", "Sign up error: $it") }
        )
    }

}