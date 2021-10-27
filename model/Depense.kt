package com.example.nooverdraft.model

import java.io.Serializable
import java.util.*

class Depense (id : Int, val nom : String, val montant : Int, val description : String, val categorie : String, val date: String)
    : Serializable {
    companion object{
        var ID = 1
    }

    val id : Int = ID++

}