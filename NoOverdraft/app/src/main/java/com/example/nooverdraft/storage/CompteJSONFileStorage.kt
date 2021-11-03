package com.example.nooverdraft.storage

import android.content.Context
import com.example.nooverdraft.model.Compte
import com.example.nooverdraft.model.Compte.Companion.COMPTE_ID
import com.example.nooverdraft.model.Compte.Companion.COMPTE_NOM
import com.example.nooverdraft.model.Compte.Companion.COMPTE_NUM
import com.example.nooverdraft.storage.utility.JSONFileStorage
import org.json.JSONObject

class CompteJSONFileStorage private constructor(context: Context) : JSONFileStorage<Compte>(context, "compte"){
    companion object {
        private var STORAGE: CompteJSONFileStorage? = null

        fun get(context: Context) : CompteJSONFileStorage {
            if (STORAGE == null) {
                STORAGE = CompteJSONFileStorage(context)
            }
            return STORAGE as CompteJSONFileStorage
        }
    }

    override fun create(id : Int, obj : Compte): Compte {
        return Compte(id, obj.nom, obj.num_compte)
    }

    override fun objectToJson(id : Int, obj : Compte) : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(COMPTE_ID, id)

        jsonObject.put(COMPTE_NOM, obj.nom)
        jsonObject.put(COMPTE_NUM, obj.num_compte)

        return jsonObject


    }

    override fun jsonToObject(json : JSONObject): Compte {
        return Compte(
            json.getInt(COMPTE_ID),
            json.getString(COMPTE_NOM),
            json.getInt(COMPTE_NUM),

        )
    }
}