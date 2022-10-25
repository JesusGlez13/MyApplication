package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class ActivityLogin : AppCompatActivity() {
    var txtUsu: EditText?=null
    var txtPass: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtUsu=findViewById(R.id.txtUsu)
        txtPass=findViewById(R.id.txtPass)
    }

    @SuppressLint("Range")
    fun login(view: View){

        var pass=txtPass?.text.toString()
        var passAct:String?=""
        var cont:Int=0

        // Create or Instantiate the database
        val dataBaseHelper = DataBaseHelper(applicationContext)

        //Para recibir parámetros
        //var intent:Intent=intent
        //var userRecibed=intent.getStringExtra("UserName")
        //var passRecibed=intent.getStringExtra("UserPass")

        val db_writer = dataBaseHelper.writableDatabase
        val selectQuery = "SELECT  * FROM Usuario WHERE password = ?"


        db_writer.rawQuery(selectQuery, arrayOf(pass)).use { // .use requires API 16
            if (it.moveToFirst()) {

                passAct=it.getString(it.getColumnIndex("password"))
                cont++

            }

        }

        if(cont>=1){
            val home= Intent(this, ActivityHome::class.java)
            txtPass?.setText("")
            txtUsu?.setText("")
            startActivity(home)
        }else{
            Toast.makeText(this,"El usuario se debe loguear", Toast.LENGTH_LONG).show()
        }
        txtPass?.setText("")
        txtUsu?.setText("")
    }
    fun irRegistro(view: View){
        val reg=Intent(this, MainActivity::class.java)
        startActivity(reg)

    }

}
