package com.example.nooverdraft.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.activity.MainActivity.Companion.EXTRA_DEPENSE
import com.example.nooverdraft.adapter.DepenseAdapter
import com.example.nooverdraft.model.Depense
import com.example.nooverdraft.storage.DepenseJSONFileStorage
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.Manifest
import com.example.nooverdraft.activity.DepenseAddActivity as DepenseAddActivity

class MainActivity : AppCompatActivity() {

    val depenses: ArrayList<Depense> = arrayListOf()

    companion object {
        val EXTRA_DEPENSE = "EXTRA_DEPENSE"
        const val READ_STORAGE_CODE = 1
        const val WRITE_STORAGE_CODE = 2
    }



    lateinit var list: RecyclerView
    lateinit var button: FloatingActionButton
    lateinit var json : DepenseJSONFileStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById<RecyclerView>(R.id.depense_list)
        button = findViewById<FloatingActionButton>(R.id.depense_add) as FloatingActionButton


        if (!checkPermission()) {
            requestPermission()
        }
        json  = DepenseJSONFileStorage(this)
        depenses.addAll(json.findAll())

        list = findViewById<RecyclerView>(R.id.depense_list)

        list.adapter = object : DepenseAdapter(depenses){
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, MainActivity::class.java).apply{
                    putExtra(
                        EXTRA_DEPENSE,
                        depenses.get(list.getChildViewHolder(view).adapterPosition)
                    )
                }
                startActivity(intent)
            }
            override fun onLongItemClick(view: View): Boolean {
                Toast.makeText(applicationContext, "Je veux supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
        }









        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, DepenseAddActivity::class.java))
            }
        })


        //var iddep = intent.getIntExtra("id", 0).toInt()
        //var depense = DepenseJSONFileStorage.get(this).findAll().get(iddep)



        //GetIntExtra pour récupérer la dépensepuis reafficher

        //var depense = intent.getIntExtra(EXTRA_DEPENSE, 0) as Depense



        /*
        list.adapter = object : DepenseAdapter(applicationContext){
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, MainActivity::class.java).apply{
                    putExtra( //Le mainActivity va changer ca renverra sur la page de la depsne
                        ListActivity.EXTRA_DEPENSE,
                        view.tag as Int
                        //on reaffiche depuis l'id dans la page
                    )
                }
                startActivity(intent)
            }
            override fun onLongItemClick(view: View): Boolean {
                Toast.makeText(applicationContext, "Je veux supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
        }
         */

    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_STORAGE_CODE
        )
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            WRITE_STORAGE_CODE
        )
    }





}