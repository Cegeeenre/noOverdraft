package com.example.nooverdraft.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.nooverdraft.R
import com.example.nooverdraft.model.Compte
import com.example.nooverdraft.storage.CompteJSONFileStorage

class CompteChangeActivity : AppCompatActivity() {

    lateinit var buttonchange: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comptemaj)


        buttonchange = findViewById<Button>(R.id.compte_changement_send) as Button
        val intent = Intent(this,MainActivity::class.java)


        var compte : Compte = CompteJSONFileStorage.get(applicationContext).find(1) as Compte
        // on récupère les infos
        findViewById<EditText>(R.id.compte_nom).setText(compte.nom)
        findViewById<EditText>(R.id.compte_montant).setText(compte.num_compte.toString())


        buttonchange.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // on crée un nouveau compte
                var nouveaunom = findViewById<EditText>(R.id.compte_nom).getText().toString()
                var nouveaumontant = findViewById<EditText>(R.id.compte_montant).getText().toString().toInt()

                var nouveaucompte : Compte = Compte(
                    0, nouveaunom, nouveaumontant)

                //Log.i("nouveaunom", nouveaunom)

                // on change le compte courant par le nouveau
                CompteJSONFileStorage.get(applicationContext).update(1, nouveaucompte as Compte)

                startActivity(intent)


            }
        })
    }
}