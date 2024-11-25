package com.example.randomcatfacts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomcatfacts.R
import com.example.randomcatfacts.data.database.Cat

class CatAdapter : ListAdapter<Cat, CatAdapter.CatViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cat: Cat) {
            itemView.findViewById<TextView>(R.id.catNameTextView).text = cat.name
            val imageView = itemView.findViewById<ImageView>(R.id.catImageView)
            Glide.with(itemView.context).load(cat.photoUrl).into(imageView)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Cat, newItem: Cat) = oldItem == newItem
    }
}
