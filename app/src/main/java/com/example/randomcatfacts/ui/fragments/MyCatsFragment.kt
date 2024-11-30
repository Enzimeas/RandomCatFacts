package com.example.randomcatfacts.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.randomcatfacts.R
import com.example.randomcatfacts.data.database.Cat
import com.example.randomcatfacts.data.database.CatDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.provider.MediaStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MyCatsFragment : Fragment() {
    private lateinit var database: CatDatabase
    private lateinit var catsContainer: LinearLayout
    private var selectedImageUri: Uri? = null

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_cats, container, false)

        // LinearLayout para adicionar os gatos manualmente
        catsContainer = view.findViewById(R.id.catsContainer)

        // Configuração do banco de dados
        database = Room.databaseBuilder(
            requireContext(),
            CatDatabase::class.java, "cat_database"
        ).build()

        val addCatButton = view.findViewById<FloatingActionButton>(R.id.fabAddCat)

        addCatButton.setOnClickListener {
            // Abre o Dialog para adicionar o gato
            showAddCatDialog()
        }

        // Observa os gatos no banco de dados e os exibe
        database.catDao().getAllCats().observe(viewLifecycleOwner) {
            displayCats(it) // Exibe os gatos na tela
        }

        return view
    }

    private fun displayCats(cats: List<Cat>) {
        catsContainer.removeAllViews()  // Limpa a lista de gatos atual

        for (cat in cats) {
            val catView = LayoutInflater.from(context).inflate(R.layout.item_cat, catsContainer, false)
            val catName = catView.findViewById<TextView>(R.id.catName)
            val catImage = catView.findViewById<ImageView>(R.id.catImage)

            catName.text = cat.name
            // Carregar a imagem do gato
            if (cat.photoUrl.isNotEmpty()) {
                // Aqui você pode usar Glide ou Picasso para carregar a imagem
                // Glide.with(context).load(cat.photoUrl).into(catImage)
            }

            catsContainer.addView(catView)  // Adiciona o item à lista
        }
    }

    private fun showAddCatDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_cat, null)
        val catNameEditText = dialogView.findViewById<EditText>(R.id.catNameEditText)
        val saveCatButton = dialogView.findViewById<View>(R.id.saveCatButton)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Adicionar Gato")
            .setView(dialogView)
            .create()

        saveCatButton.setOnClickListener {
            val catName = catNameEditText.text.toString().trim()

            if (catName.isNotEmpty() && selectedImageUri != null) {
                val newCat = Cat(name = catName, photoUrl = selectedImageUri.toString())

                // Usar lifecycleScope para lançar uma coroutine
                lifecycleScope.launch {
                    try {
                        // Tente inserir o gato no banco de dados
                        database.catDao().insertCat(newCat)

                        // Mostra a confirmação
                        Toast.makeText(context, "Gato adicionado!", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    } catch (e: Exception) {
                        // Caso ocorra algum erro, exibe a mensagem de erro
                        Toast.makeText(context, "Erro ao adicionar gato: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Por favor, insira o nome e selecione uma foto!", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.show()
        openImagePicker()
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                selectedImageUri = uri
                Toast.makeText(context, "Foto selecionada!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
