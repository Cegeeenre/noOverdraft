package com.example.nooverdraft.activity

import android.app.Application
import android.app.ListActivity
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nooverdraft.R
import com.example.nooverdraft.dialog.Updatable
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.storage.DepenseJSONFileStorage

class ItemActivity : AppCompatActivity() {

    private var depense: Depense? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depense)

        // on prend l'id de la dépense que l'on veut afficher
        val id = intent.getIntExtra(MainActivity.EXTRA_DEPENSE, 0)
        depense = DepenseJSONFileStorage.get(applicationContext).find(id)
        // on remplace les champs
        findViewById<TextView>(R.id.affiche_nom).text = depense?.nom;
        findViewById<TextView>(R.id.affiche_montant).text = depense?.montant.toString();
        findViewById<TextView>(R.id.affiche_description).text = depense?.description;

    }


}