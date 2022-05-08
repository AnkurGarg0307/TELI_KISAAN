package com.example.farmersapp.dashboard

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmersapp.R
import com.example.farmersapp.adapters.GovtSchemeAdapter
import com.example.farmersapp.models.SchemeItem


class GovernmentSchemas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_government_schemas)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<SchemeItem>()

        for (i in 1..5) {
            data.add(SchemeItem(R.drawable.bot_icon, "Item " + i, "hello" + i))
        }

        val adapter = GovtSchemeAdapter(data)

        recyclerview.adapter = adapter
    }
}