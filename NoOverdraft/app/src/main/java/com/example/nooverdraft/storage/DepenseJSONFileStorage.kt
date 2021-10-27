package com.example.nooverdraft.storage

import android.content.Context
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.model.Depense.Companion.DEPENSE_DESCRIPTION
import com.example.nooverdraft.model.Depense.Companion.DEPENSE_ID
import com.example.nooverdraft.model.Depense.Companion.DEPENSE_MONTANT
import com.example.nooverdraft.model.Depense.Companion.DEPENSE_NOM
import com.example.nooverdraft.storage.utility.JSONFileStorage
import org.json.JSONObject

class DepenseJSONFileStorage internal constructor(context: Context) : JSONFileStorage<Depense>(context, "depense") {

    companion object {
        private var STORAGE: DepenseJSONFileStorage? = null

        fun get(context: Context) :DepenseJSONFileStorage {
            if (STORAGE == null) {
                STORAGE = DepenseJSONFileStorage(context)
            }
            return STORAGE as DepenseJSONFileStorage
        }
    }
    override fun create(id : Int, obj : Depense): Depense {
        return Depense(id, obj.nom, obj.montant, obj.description, obj.categorie, obj.date)
    }



    override fun objectToJson(id : Int, obj : Depense) : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(DEPENSE_ID, id)

        jsonObject.put(DEPENSE_NOM, obj.nom)
        jsonObject.put(DEPENSE_MONTANT, obj.montant)
        jsonObject.put(DEPENSE_DESCRIPTION, obj.description)

        return jsonObject


    }

    override fun jsonToObject(json : JSONObject): Depense {
        return Depense(
            json.getInt(DEPENSE_ID),
            json.getString(DEPENSE_NOM),
            json.getInt(DEPENSE_MONTANT),
            json.getString(DEPENSE_DESCRIPTION),
            "Nourriture", "21/10/2021"

            )
    }


}