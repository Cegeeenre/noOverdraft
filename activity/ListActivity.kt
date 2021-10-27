package com.example.nooverdraft.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.adapter.DepenseAdapter
import com.example.nooverdraft.model.Depense

class ListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DEPENSE = "EXTRA_DEPENSE"
    }

    var depenses: ArrayList<Depense> = arrayListOf()
    lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        depenses.add(
            Depense(
                1, "Bouffe", 15, "petite paella",
                "Nourriture", "30/05/2021"
            )
        )

        list = findViewById<RecyclerView>(R.id.depense_list)

        list.adapter = object : DepenseAdapter(depenses){
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, MainActivity::class.java).apply{
                    putExtra(
                        EXTRA_DEPENSE,
                        depenses.get(list.getChildViewHolder(view).adapterPosition)
                    )
                }
                startActivity(intent)
            }
            override fun onLongItemClick(view: View): Boolean {
                Toast.makeText(applicationContext, "Je veux supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
        }
    }


    }