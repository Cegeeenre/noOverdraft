package com.example.nooverdraft.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.storage.DepenseJSONFileStorage
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DepenseAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        lateinit var buttonadd: Button

        buttonadd = findViewById<Button>(R.id.add_button_send) as Button


        buttonadd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var nom = findViewById<EditText>(R.id.add_nom).toString()
                var montant = findViewById<EditText>(R.id.add_depense).toString().toInt()
                val description = findViewById<EditText>(R.id.add_description).toString()


                var maDepense:Depense =Depense(
                    0, nom, montant, description,
                    "Nourriture", "30/05/2021")

                DepenseJSONFileStorage.get(applicationContext).insert(maDepense)

                startActivity(Intent(this@DepenseAddActivity, DepenseAddActivity::class.java))

            }
        })










    }
}