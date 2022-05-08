
package com.example.farmersapp.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmersapp.R
import com.example.farmersapp.adapters.AboutCropAdapter
import com.example.farmersapp.models.CropItem

class AboutCrop : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_crop)
            val recyclerview = findViewById<RecyclerView>(R.id.recyclerView2)

            recyclerview.layoutManager = LinearLayoutManager(this)

            val data = ArrayList<CropItem>()

            for (i in 1..5) {
                data.add(CropItem(R.drawable.bot_icon, "Item " + i))
                val adapter = AboutCropAdapter(data)
                recyclerview.adapter = adapter
            }
    }
}