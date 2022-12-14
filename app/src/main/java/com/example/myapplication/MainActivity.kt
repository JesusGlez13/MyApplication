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
    var txtNombre: EditText?=null
    var txtTelf: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUsu=findViewById(R.id.txtUsu)
        txtPass=findViewById(R.id.txtPass)
        txtNombre=findViewById(R.id.txtNom)
        txtTelf=findViewById(R.id.txtTelf)


    }
    fun guardar(view: View){
        var us=txtUsu?.text.toString()
        var pass=txtPass?.text.toString()
        var nom=txtNombre?.text.toString()
        var tel=txtTelf?.text.toString()
        var cont:Int=0



        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db_writer = dataBaseHelper.writableDatabase
        val db_writer2 = dataBaseHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put("email", us)
            put("password", pass)
            put("nombre", nom)
            put("telefono", tel)
        }

        val selectQuery = "SELECT  * FROM Usuario WHERE email = ?"


        db_writer2.rawQuery(selectQuery, arrayOf(us)).use { // .use requires API 16
            if (it.moveToFirst()) {
            cont++


            }
                if(cont<1){
                    // Insert the new row, returning the primary key value of the new row
                    val newRowId = db_writer?.insert("Usuario", null, values)
                    Toast.makeText(this,"El usuario se ha guardado correctamente", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Existe un usuario con el mismo email, por favor cambialo", Toast.LENGTH_LONG).show()
                }

        }

        txtPass?.setText("")
        txtUsu?.setText("")
        txtNombre?.setText("")
        txtTelf?.setText("")

    }
    fun irLogin(view:View){

        val login=Intent(this, ActivityLogin::class.java)//.apply{
          //putExtra("UserName", us)
          //putExtra("UserPass", pass)
        //}

        txtPass?.setText("")
        txtUsu?.setText("")
        txtNombre?.setText("")
        txtTelf?.setText("")
        startActivity(login)

    }
}