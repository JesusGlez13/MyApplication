package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


    class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            // If you change the database schema, you must increment the database version.
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "MySQLDataBase"
        }
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE Usuario (UID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, nombre TEXT NOT NULL, isadmin INTEGER NOT NULL DEFAULT 0, telefono TEXT NOT NULL)")
            db.execSQL("CREATE TABLE Facilitador (FID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, nombre TEXT NOT NULL, bio TEXT, foto BLOB)")
            db.execSQL("CREATE TABLE Ubicacion (UBID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, direccion TEXT NOT NULL, aforo TEXT NOT NULL, nombre TEXT NOT NULL, foto BLOB)")
            db.execSQL("CREATE TABLE Evento (EID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT NOT NULL, fecha TEXT NOT NULL, descripcion TEXT NOT NULL, foto BLOB, FOREIGN KEY (FID) REFERENCES Facilitador (FID), FOREIGN KEY (UBID) REFERENCES Ubicacion (UBID))")
            db.execSQL("CREATE TABLE UserEvent (UEID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, FOREIGN KEY (FID) REFERENCES Facilitador (FID), FOREIGN KEY (UBID) REFERENCES Ubicacion (UBID))")

        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // This database is only a cache for online data, so its upgrade policy is to
            // simply to discard the data and start over.
            db.execSQL("DROP TABLE IF EXISTS Usuario")
            db.execSQL("DROP TABLE IF EXISTS Facilitador")
            db.execSQL("DROP TABLE IF EXISTS Ubicacion")
            db.execSQL("DROP TABLE IF EXISTS Evento")
            db.execSQL("DROP TABLE IF EXISTS UserEvent")
            onCreate(db)
        }
        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(db, oldVersion, newVersion)
        }
    }
