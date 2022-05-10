package com.example.farmersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.farmersapp.databinding.ActivityDescriptionBinding
import com.example.farmersapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Description_Activity : AppCompatActivity() {
    lateinit var binding:ActivityDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.NValue.setText(intent.getStringExtra("N"))
        binding.PValue.setText(intent.getStringExtra("P"))
        binding.KValue.setText(intent.getStringExtra("K"))
    }
}