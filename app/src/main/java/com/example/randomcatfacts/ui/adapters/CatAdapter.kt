package com.example.randomcatfacts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.randomcatfacts.R
import com.example.randomcatfacts.data.database.Cat

class CatAdapter(private val catList: List<Cat>) {

    // Função para adicionar gatos em um LinearLayout ou ViewGroup
    fun bindCats(catsContainer: LinearLayout) {
        catsContainer.removeAllViews()  // Limpa qualquer item anterior

        for (cat in catList) {
            val catView = LayoutInflater.from(catsContainer.context).inflate(R.layout.item_cat, catsContainer, false)
            val catNameTextView = catView.findViewById<TextView>(R.id.catName)
            val catImageView = catView.findViewById<ImageView>(R.id.catImage)

            // Atribuindo valores aos views
            catNameTextView.text = cat.name
            // Carregar a imagem usando Glide
            Glide.with(catsContainer.context).load(cat.photoUrl).into(catImageView)

            // Adicionar o item à lista
            catsContainer.addView(catView)
        }
    }
}
