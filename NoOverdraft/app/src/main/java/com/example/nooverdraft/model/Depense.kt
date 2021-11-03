package com.example.nooverdraft.model

import java.io.Serializable
import java.util.*

class Depense (id : Int, nom : String, montant : Int, description : String, categorie : String, date: String)
    : Serializable {
    companion object{
        var ID = 1
    }

    val id : Int = ID++

}