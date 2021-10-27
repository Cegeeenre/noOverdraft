package com.example.nooverdraft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.storage.DepenseJSONFileStorage

abstract class DepenseAdapter(val depenses : List<Depense>) : RecyclerView.Adapter<DepenseAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val depenseImage = view.findViewById<ImageView>(R.id.image_depense)
        val depensenom = view.findViewById<TextView>(R.id.nom_depense)
        val depensemontant = view.findViewById<TextView>(R.id.montant_depense)

    }

    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view: View): Boolean


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_depense, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.depensenom.text = depenses.get(position).nom
        holder.depensemontant.text = "- " + depenses.get(position).montant.toString() + "$"

    }

    override fun getItemCount(): Int {
        return depenses.size
    }

}