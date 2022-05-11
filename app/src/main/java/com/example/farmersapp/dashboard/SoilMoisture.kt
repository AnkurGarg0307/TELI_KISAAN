package com.example.farmersapp.dashboard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.farmersapp.databinding.ActivitySoilMoisture2Binding
import com.google.firebase.database.*


class SoilMoisture : AppCompatActivity() {

    lateinit var binding: ActivitySoilMoisture2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySoilMoisture2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.reference.child("test")

        // Read from the database
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                val value: HashMap<Any, Any> = dataSnapshot.value as HashMap<Any, Any>
                binding.moistureVal.text = "${value["int"]} %"

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("sensordata", "Failed to read value.", error.toException())
            }
        })


    }
}