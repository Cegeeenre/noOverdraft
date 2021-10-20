package com.example.nooverdraft

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nooverdraft.R
import com.example.nooverdraft.fragments.ListActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        //injecter le fragment dans notre boite (fragement container )
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, ListActivity())
        transaction.addToBackStack(null)
        transaction.commit()

    }
}