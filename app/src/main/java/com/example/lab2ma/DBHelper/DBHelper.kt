package com.example.lab2ma.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.lab2ma.model.Dest


class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
   companion object{
       private val DATABASE_VER = 1;
       private val DATABASE_NAME = "dest.db"

       private val TABLE_NAME = "Dest"
       private val COL_ID = "Id"
       private val COL_Country = "Country"
       private val COL_City = "City"
       private val COL_Piece = "Price"
       private val COL_Description = "Description"
   }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY : String = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY,$COL_Country TEXT," +
                "$COL_City TEXT,$COL_Piece INTEGER,$COL_Description TEXT)" )
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD Operations
    val allDest:List<Dest>
        get(){
            val listDest = ArrayList<Dest>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst())
            {
                do{
                    val dest = Dest()
                    dest.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    dest.country = cursor.getString(cursor.getColumnIndex(COL_Country))
                    dest.city = cursor.getString(cursor.getColumnIndex(COL_City))
                    dest.price = cursor.getInt(cursor.getColumnIndex(COL_Piece))
                    dest.description = cursor.getString(cursor.getColumnIndex(COL_Description))

                    listDest.add(dest)

                } while (cursor.moveToNext())
            }
            db.close()
            return listDest
        }

    fun addDest(dest: Dest){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,dest.id)
        values.put(COL_Country,dest.country)
        values.put(COL_City,dest.city)
        values.put(COL_Piece,dest.price)
        values.put(COL_Description,dest.description)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateDest(dest: Dest): Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,dest.id)
        values.put(COL_Country,dest.country)
        values.put(COL_City,dest.city)
        values.put(COL_Piece,dest.price)
        values.put(COL_Description,dest.description)

        return db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(dest.id.toString()))
    }

    fun deleteDest(dest: Dest){
        val db = this.writableDatabase

        db.delete(TABLE_NAME,"$COL_ID=?", arrayOf(dest.id.toString()))
        db.close()
    }
}