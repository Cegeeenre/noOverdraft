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

// INUTILE

class ListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DEPENSE = "EXTRA_DEPENSE"
    }

    var depenses: ArrayList<Depense> = arrayListOf()
    lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }


}