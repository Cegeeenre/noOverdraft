package com.example.nooverdraft.activity

import android.accounts.AccountManager.get
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.storage.DepenseJSONFileStorage
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DepenseAddActivity : AppCompatActivity() {

    lateinit var buttonadd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        buttonadd = findViewById<Button>(R.id.add_button_send) as Button
        val intent = Intent(this,MainActivity::class.java)

        buttonadd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Log.i("test", "BONJOUR")

                var nom = findViewById<EditText>(R.id.add_nom).getText().toString()
                //Log.i("nom", nom)
                var montant = findViewById<EditText>(R.id.add_depense).getText().toString().toInt()
                val description = findViewById<EditText>(R.id.add_description).getText().toString()
                //Log.i("description", description)

                Toast.makeText(applicationContext, "Dépense ajoutée avec succès", Toast.LENGTH_SHORT).show()

                var maDepense:Depense =Depense(
                    0, nom, montant, description,
                    "Nourriture", "30/05/2021")

                DepenseJSONFileStorage.get(applicationContext).insert(maDepense)

                startActivity(intent)

            }
        })

    }
}