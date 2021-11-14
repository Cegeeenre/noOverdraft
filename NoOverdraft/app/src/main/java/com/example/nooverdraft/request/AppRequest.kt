package com.example.nooverdraft.request

import android.Manifest
import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.nooverdraft.R
import com.example.nooverdraft.dialog.Updatable
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.storage.DepenseJSONFileStorage
import org.json.JSONObject
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.checkSelfPermission
import com.example.nooverdraft.model.Compte
import com.example.nooverdraft.storage.CompteJSONFileStorage


class AppRequest(private val context: Context, updatable: Updatable) {
    companion object{
        private const val URL =
            "http://os-vps418.infomaniak.ch:1186/i507_1_9/fichier.json"
    }


    init {
        // instantiation de la requête
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET, URL, null,
            {
                    response ->
                refresh(response)
                updatable.update()
                Log.i("test", "test")
                Toast.makeText(context, R.string.request_success, Toast.LENGTH_SHORT).show()
            },
            {
                    error ->
                println(error)
                Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
            }

        )

        queue.add(request)
        queue.start()
    }

    private fun refresh(json : JSONObject) {
        delete()
        insert(json)
    }

    private fun delete(){

        for (contact in DepenseJSONFileStorage.get(context).findAll()){
            DepenseJSONFileStorage.get(context).delete(contact.id)
        }

    }

    private fun insert(json : JSONObject) {
        val depenses = json.getJSONArray("depenses")
        for (i in 0 until depenses.length()) {
            val depense = depenses.getJSONObject(i)

            val montant = depense.getInt(Depense.DEPENSE_MONTANT)

            DepenseJSONFileStorage.get(context).insert(
                Depense(
                    0,
                    depense.getString(Depense.DEPENSE_NOM),
                    depense.getInt(Depense.DEPENSE_MONTANT),
                    depense.getString(Depense.DEPENSE_DESCRIPTION),
                    "Nourriture", "10/10/2021"
                )
            )

            var compte : Compte = CompteJSONFileStorage.get(context).find(1) as Compte

            //Gere le compte, en enlevant la moula
            val solde : Int = compte.num_compte - montant
            Log.i("compte", solde.toString())
            val name : String = compte.nom
            var temp_compte : Compte = Compte(
                0, name, solde)

            CompteJSONFileStorage.get(context).update(1, temp_compte as Compte)


        }
    }
}