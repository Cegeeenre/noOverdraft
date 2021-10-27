package com.example.nooverdraft.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nooverdraft.R
import com.example.nooverdraft.model.Depense

class MainActivity : AppCompatActivity() {

    private lateinit var depense : Depense


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        depense = intent.getSerializableExtra(ListActivity.EXTRA_DEPENSE) as Depense



    }
}