package com.example.nooverdraft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.model.Compte
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.storage.CompteJSONFileStorage
import com.example.nooverdraft.storage.DepenseJSONFileStorage

abstract class CompteAdapter(val context: Context): RecyclerView.Adapter<CompteAdapter.CompteHolder>(){


    class CompteHolder(view: View): RecyclerView.ViewHolder(view){
        val nomcompte = view.findViewById<TextView>(R.id.nom_compte)
        val numcompte = view.findViewById<TextView>(R.id.num_compte)
    }

    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view: View): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_compte, parent, false)
        view.setOnClickListener{onItemClick(it)}
        view.setOnLongClickListener{onLongItemClick(it)}
        return CompteHolder(view)
    }

    override fun onBindViewHolder(holder: CompteHolder, position: Int) {
        val compte: Compte = CompteJSONFileStorage.get(context).findAll().get(position)
        holder.nomcompte.text = compte.nom
        holder.numcompte.text = compte.num_compte.toString() + "$"
        holder.itemView.tag = compte.id
    }

    override fun getItemCount(): Int {
        return CompteJSONFileStorage.get(context).size()
    }
}