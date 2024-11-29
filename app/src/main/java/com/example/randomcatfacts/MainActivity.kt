package com.example.randomcatfacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.randomcatfacts.ui.fragments.CatFactsFragment
import com.example.randomcatfacts.ui.fragments.CatPhotosFragment
import com.example.randomcatfacts.ui.fragments.MyCatsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Definindo o Toolbar (se necessário)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configurar o BottomNavigationView
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Configuração do listener de navegação
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_cat_facts -> replaceFragment(CatFactsFragment())  // Fatos de Gato
                R.id.item_cat_photos -> replaceFragment(CatPhotosFragment())  // Fotos de Gato
                R.id.item_my_cats -> replaceFragment(MyCatsFragment())  // Meus Gatos
            }
            true
        }

        // Inicializando o fragmento da tela inicial (CatFactsFragment)
        if (savedInstanceState == null) {
            replaceFragment(CatFactsFragment())
        }
    }

    // Função para substituir o fragmento
    private fun replaceFragment(fragment: Fragment) {
        // Iniciando uma transação de fragmento para substituir o conteúdo atual
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)  // Opcional, para permitir voltar à tela anterior
        transaction.commit()
    }
}
