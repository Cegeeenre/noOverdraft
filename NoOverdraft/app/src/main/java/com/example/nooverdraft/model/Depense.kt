package com.example.nooverdraft.model

import java.io.Serializable
import java.util.*

class Depense (id : Int, val nom : String, val montant : Int, val description : String, val categorie : String, val date: String)
    : Serializable{
    companion object{
        var ID = 1
        const val DEPENSE_ID = "id"
        const val DEPENSE_NOM = "nom"
        const val DEPENSE_DESCRIPTION = "description"
        const val DEPENSE_MONTANT = "montant"

    }

    val id : Int = ID++

}