package com.example.lab2ma.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.lab2ma.model.Dest
import com.example.lab2ma.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_layout.view.*

class ListViewAdapter(internal val activity: Activity,
                      internal val listDest: List<Dest>,
                      internal val edit_id: EditText,
                      internal val edit_country: EditText,
                      internal val edit_city: EditText,
                      internal val edit_price: EditText,
                      internal val edit_description: EditText):BaseAdapter(){
    internal val inflater: LayoutInflater
    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val listLayout: View
        listLayout = inflater.inflate(R.layout.list_layout, null)
        listLayout.id_textview.text = listDest[position].id.toString()
        listLayout.country_textview.text = listDest[position].country.toString()
        listLayout.city_textview.text = listDest[position].city.toString()
        listLayout.price_textview.text = listDest[position].price.toString()
        listLayout.description_textview.text = listDest[position].description.toString()

        //Event
        listLayout.setOnClickListener {
            this.activity.updateTool_layout.visibility = View.VISIBLE
            edit_id.setText(listLayout.id_textview.text.toString())
            edit_country.setText(listLayout.country_textview.text.toString())
            edit_city.setText(listLayout.city_textview.text.toString())
            edit_price.setText(listLayout.price_textview.text.toString())
            edit_description.setText(listLayout.description_textview.text.toString())
        }
        return listLayout
    }

    override fun getItem(position: Int): Any {
        return listDest[position]
    }

    override fun getItemId(position: Int): Long {
        return listDest[position].id.toLong()
    }

    override fun getCount(): Int {
        return listDest.size
    }

    fun resetLayout(){
        this.activity.updateTool_layout.visibility = View.GONE
    }

    fun getView():String{
        return this.activity.updateTool_layout.visibility.toString()
    }

}