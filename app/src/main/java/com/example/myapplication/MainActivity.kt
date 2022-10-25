package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.*
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    var txtUsu: EditText?=null
    var txtPass: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUsu=findViewById(R.id.txtUsu)
        txtPass=findViewById(R.id.txtPass)


    }
    fun guardar(view: View){
        var us=txtUsu?.text.toString()
        var pass=txtPass?.text.toString()

        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db_writer = dataBaseHelper.writableDatabase
// Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put("email", us)
            put("password", pass)
        }
// Insert the new row, returning the primary key value of the new row
        val newRowId = db_writer?.insert("Usuario", null, values)


        Toast.makeText(this,"El usuario se ha guardado correctamente", Toast.LENGTH_LONG).show()
        println("Hola")

    }
    fun irLogin(view:View){
        var us=txtUsu?.text.toString()
        var pass=txtPass?.text.toString()
        val login=Intent(this, ActivityLogin::class.java)//.apply{
          //putExtra("UserName", us)
          //putExtra("UserPass", pass)

        //}
        txtPass?.setText("")
        txtUsu?.setText("")
        startActivity(login)

    }



}