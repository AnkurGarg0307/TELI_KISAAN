package com.example.farmersapp.dashboard

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmersapp.R
import com.example.farmersapp.adapters.AboutCropAdapter
import com.example.farmersapp.adapters.GovtSchemeAdapter
import com.example.farmersapp.models.CropItem
import com.example.farmersapp.models.SchemeItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class GovernmentSchemas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_government_schemas)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<SchemeItem>()

        val db = Firebase.firestore
        db.collection("policies")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    data.add(
                        SchemeItem(
                            document.data["image"].toString(),
                            document.data["name"].toString(),
                            document.data["desc"].toString()

                        )
                    )
                }
                val adapter = GovtSchemeAdapter(data)
                recyclerview.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "$exception", Toast.LENGTH_LONG).show()
            }

    }
}