package com.example.nooverdraft.model

import java.io.Serializable

class Compte (id : Int, val nom : String, var num_compte : Int): Serializable {
    companion object{
        var ID = 1
        const val COMPTE_ID = "id"
        const val COMPTE_NOM = "nom"
        const val COMPTE_NUM = "num"
    }

    val id : Int = ID++
}