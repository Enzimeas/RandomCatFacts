package com.example.randomcatfacts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.randomcatfacts.R
import com.example.randomcatfacts.data.database.Cat
import com.example.randomcatfacts.data.database.CatDatabase
import com.example.randomcatfacts.ui.adapters.CatAdapter
import kotlinx.coroutines.launch

class MyCatsFragment : Fragment() {
    private lateinit var database: CatDatabase
    private lateinit var adapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_cats, container, false)

        // Corrigido os IDs para corresponder ao XML
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMyCats) // Alterado para recyclerViewMyCats
        val addCatButton = view.findViewById<Button>(R.id.fabAddCat) // Alterado para fabAddCat

        database = Room.databaseBuilder(
            requireContext(),
            CatDatabase::class.java, "cat_database"
        ).build()

        adapter = CatAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observando os dados no banco de dados
        database.catDao().getAllCats().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        addCatButton.setOnClickListener {
            // Aqui você pode abrir um diálogo para adicionar o gato
        }

        return view
    }
}
