package com.example.farmersapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.farmersapp.Description_Activity
import com.example.farmersapp.R
import com.example.farmersapp.dashboard.AboutCrop
import com.example.farmersapp.models.CropItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AboutCropAdapter(private val mList: List<CropItem>): RecyclerView.Adapter<AboutCropAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.crop_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        holder.imageView.setImageResource(ItemsViewModel.image)

        holder.textView.setText(ItemsViewModel.name)
        holder.item.setOnClickListener(View.OnClickListener {V->
            val intent = Intent(V.context,Description_Activity::class.java)
            intent.putExtra("N", ItemsViewModel.N)
            intent.putExtra("P", ItemsViewModel.P)
            intent.putExtra("K", ItemsViewModel.K)
            V.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        val textView: TextView = itemView.findViewById(R.id.textView5)
        val item: LinearLayout = itemView.findViewById(R.id.cropItem)
    }
}