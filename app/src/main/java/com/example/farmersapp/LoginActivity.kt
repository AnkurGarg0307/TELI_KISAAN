package com.example.farmersapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farmersapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var isLogin = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.loginSignup.setOnClickListener(View.OnClickListener {
            if (isLogin) {
                signIn(binding.nameInput.text.toString(), binding.passwordInput.text.toString())
            } else {
                if (binding.passwordInput.text.toString()
                        .equals(binding.confirmPasswordInput.text.toString())
                ) {
                    createAccount(
                        binding.nameInput.text.toString(),
                        binding.passwordInput.text.toString()
                    )
                } else {
                    Toast.makeText(
                        baseContext,
                        "Confirm Password doesn't match Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        binding.ChangeHaveOrNot.setOnClickListener(View.OnClickListener {
            if (isLogin) {
                binding.confirmPassword.visibility = View.VISIBLE
                binding.loginSignup.text = "SIGNUP"
                binding.ChangeHaveOrNot.text = "Login"
                binding.haveOrNot.text = "Already have an account?"
                binding.forgot.visibility = View.GONE
                binding.confirmPassword.visibility = View.VISIBLE
                isLogin = false
            } else {
                binding.forgot.visibility = View.VISIBLE
                binding.confirmPassword.visibility = View.GONE
                binding.loginSignup.text = "LOGIN"
                binding.ChangeHaveOrNot.text = "SignUp"
                binding.haveOrNot.text = "Don't have an account?"
                isLogin = true
            }
        })
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        savePrefsData()
                        Toast.makeText(
                            baseContext,
                            "Authentication Created successful.",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        savePrefsData()
                        Toast.makeText(
                            baseContext,
                            "Authentication SuccessFull.",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isLogin", true)
        editor.apply()
    }
}