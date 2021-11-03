package com.example.nooverdraft.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.model.Depense

class ListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CONTACT = "EXTRA_CONTACT"
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

        //list = findViewById<RecyclerView>(R.id.depense_list)


    }
}