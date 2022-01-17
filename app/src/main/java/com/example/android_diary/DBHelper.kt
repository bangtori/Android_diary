package com.example.android_diary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
        var sql : String = "CREATE TABLE if not exists mytable (" +
                "_id integer primary key autoincrement," +
                "date text" +
                "thanks1 text" +
                "thanks2 text" +
                "thanks3 text" +
                "betterDay1 text" +
                "betterDay2 text" +
                "betterDay3 text" +
                "positiveSentence text);";

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}