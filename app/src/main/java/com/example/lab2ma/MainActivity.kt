package com.example.lab2ma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2ma.Adapter.ListViewAdapter
import com.example.lab2ma.DBHelper.DBHelper
import com.example.lab2ma.model.Dest

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.add_dialog.view.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db: DBHelper
    internal var listDest:List<Dest> = ArrayList<Dest> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DBHelper(this)
        refreshData()

        //Add button
        fab.setOnClickListener { view ->
            val addDialog  = LayoutInflater.from(this).inflate(R.layout.add_dialog, null)

            val builder = AlertDialog.Builder(this)
                .setView(addDialog)
                .setTitle("Add item")

            val alertDialog = builder.show()

            addDialog.button_add.setOnClickListener {
                alertDialog.dismiss()

                val id = addDialog.add_id.text.toString().toInt()
                val country = addDialog.add_country.text.toString()
                val city = addDialog.add_city.text.toString()
                val price = addDialog.add_price.text.toString().toInt()
                val description = addDialog.add_description.text.toString()

                val dest = Dest(id, country, city, price, description)
                db.addDest(dest)
                refreshData()
            }

            addDialog.button_cancel.setOnClickListener{
                alertDialog.dismiss()
            }
        }

        //Update button
        update_button.setOnClickListener{
            val dest = Dest(
                id_edittext.text.toString().toInt(),
                country_edittext.text.toString(),
                city_edittext.text.toString(),
                price_edittext.text.toString().toInt(),
                description_edittext.text.toString()
            )
            db.updateDest(dest)
            refreshData()
        }

        //Delete button
        remove_button.setOnClickListener{
            val dest = Dest(
                id_edittext.text.toString().toInt(),
                country_edittext.text.toString(),
                city_edittext.text.toString(),
                price_edittext.text.toString().toInt(),
                description_edittext.text.toString()
            )
            db.deleteDest(dest)
            refreshData()
        }

        //cancel
        cancel_button.setOnClickListener{
            refreshData()
        }

    }

    private fun refreshData() {
        listDest = db.allDest
        val adapter = ListViewAdapter(this@MainActivity,listDest,id_edittext, country_edittext, city_edittext, price_edittext, description_edittext)
        dest_listview.adapter = adapter
        adapter.resetLayout()
    }

    override fun onBackPressed() {
        val adapter = ListViewAdapter(this@MainActivity,listDest,id_edittext, country_edittext, city_edittext, price_edittext, description_edittext)
        if (adapter.getView() == View.VISIBLE.toString())
            adapter.resetLayout()
    }
}
