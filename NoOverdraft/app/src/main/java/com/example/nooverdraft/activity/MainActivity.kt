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
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.LinearLayout
import android.widget.RemoteViews
import com.example.nooverdraft.adapter.CompteAdapter
import com.example.nooverdraft.dialog.Updatable
import com.example.nooverdraft.model.Compte
import com.example.nooverdraft.request.AppRequest
import com.example.nooverdraft.storage.CompteJSONFileStorage
import com.example.nooverdraft.activity.DepenseAddActivity as DepenseAddActivity

class MainActivity : AppCompatActivity(), Updatable {

    companion object {
        val EXTRA_DEPENSE = "EXTRA_DEPENSE"
        const val READ_STORAGE_CODE = 1
        const val WRITE_STORAGE_CODE = 2
        const val NB_COMPTE = 1
    }


    lateinit var list: RecyclerView
    lateinit var zone_compte: RecyclerView
    lateinit var button: FloatingActionButton

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    lateinit var buttonimport: FloatingActionButton
    lateinit var buttonchange: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Selection des boutons/Layout
        button = findViewById<FloatingActionButton>(R.id.depense_add) as FloatingActionButton

        buttonimport =
            findViewById<FloatingActionButton>(R.id.import_depense) as FloatingActionButton

        zone_compte = findViewById<RecyclerView>(R.id.layout_compte)
        list = findViewById<RecyclerView>(R.id.depense_list)

        buttonchange = findViewById<Button>(R.id.button_changement) as Button


        //Permession test read/write
        if (!checkPermission()) {
            requestPermission()
        }

        //Notification
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        //Ajout du compte
        var monCompte : Compte = Compte(
            0, "john", 2000)
        if (CompteJSONFileStorage.get(applicationContext).size() < 1){
        var monCompte: Compte = Compte(
            0, "john", 2000
        )}
        if (CompteJSONFileStorage.get(applicationContext).size() < 1) {
            CompteJSONFileStorage.get(applicationContext).insert(monCompte)
        }



        //Compte Affichage
        zone_compte.adapter = object : CompteAdapter(applicationContext){
            override fun onItemClick(view: View) {

            }

            override fun onLongItemClick(view: View): Boolean {
                Toast.makeText(applicationContext, "Je veux supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
        }


        //Liste depense
        list.adapter = object : DepenseAdapter(applicationContext) {
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, ItemActivity::class.java).apply {
                    putExtra(EXTRA_DEPENSE, view.tag as Int)
                }
                startActivity(intent)
            }

            override fun onLongItemClick(view: View): Boolean {
                Toast.makeText(applicationContext, "Je veux supprimer", Toast.LENGTH_SHORT).show()
                DepenseJSONFileStorage.get(applicationContext).delete(view.tag as Int)
                update()
                return true
            }
        }


        buttonimport.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AppRequest(this@MainActivity, this@MainActivity)
                update()
            }
        })


        buttonchange.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, CompteChangeActivity::class.java))

            }
        })


        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                val contentView = RemoteViews(packageName, R.layout.activity_notification)

                // checking if android version is greater than oreo(API 26) or not
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this@MainActivity, channelId)
                        .setContent(contentView)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.ic_launcher_background))
                        .setContentIntent(pendingIntent)
                } else {

                    builder = Notification.Builder(this@MainActivity)
                        .setContent(contentView)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.ic_launcher_background))
                        .setContentIntent(pendingIntent)
                }
                notificationManager.notify(1234, builder.build())

                /////////////////////////////////////////////////


                startActivity(Intent(this@MainActivity, DepenseAddActivity::class.java))
                finish()
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
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
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

    override fun update() {
        list.adapter?.notifyDataSetChanged()
        zone_compte.adapter?.notifyDataSetChanged()
    }






}
