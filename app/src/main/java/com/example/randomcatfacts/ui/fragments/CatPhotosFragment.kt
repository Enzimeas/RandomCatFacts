package com.example.randomcatfacts.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.randomcatfacts.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.randomcatfacts.network.CatImage
import com.example.randomcatfacts.data.api.CatApi

class CatPhotosFragment : Fragment() {
    private val BASE_URL = "https://api.thecatapi.com/v1/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_photos, container, false)
        val catImageView = view.findViewById<ImageView>(R.id.catImageView)
        val loadCatPhotoButton = view.findViewById<Button>(R.id.loadCatPhotoButton)

        loadCatPhotoButton.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(CatApi::class.java)
            api.getRandomCatImage().enqueue(object : Callback<List<CatImage>> {
                override fun onResponse(call: Call<List<CatImage>>, response: Response<List<CatImage>>) {
                    val imageUrl = response.body()?.get(0)?.url
                    Glide.with(this@CatPhotosFragment).load(imageUrl).into(catImageView)
                }

                override fun onFailure(call: Call<List<CatImage>>, t: Throwable) {
                    Toast.makeText(context, "Erro ao carregar imagem", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return view
    }
}
