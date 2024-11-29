package com.example.randomcatfacts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.randomcatfacts.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatFactsFragment : Fragment() {
    private val BASE_URL = "https://cat-fact.herokuapp.com/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_facts, container, false)
        val catFactTextView = view.findViewById<TextView>(R.id.catFactTextView)
        val loadCatFactButton = view.findViewById<Button>(R.id.loadCatFactButton)

        loadCatFactButton.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(CatFactsApi::class.java)
            api.getRandomCatFact().enqueue(object : Callback<CatFact> {
                override fun onResponse(call: Call<CatFact>, response: Response<CatFact>) {
                    catFactTextView.text = response.body()?.text
                }

                override fun onFailure(call: Call<CatFact>, t: Throwable) {
                    Toast.makeText(context, "Erro ao carregar fato", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return view
    }
}
