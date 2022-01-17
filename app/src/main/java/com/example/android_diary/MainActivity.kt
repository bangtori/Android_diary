package com.example.android_diary

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase

    val addDiaryButton : Button by lazy{
        findViewById<Button>(R.id.addDiaryButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this, "myDiary.db", null, 1)
        database = dbHelper.writableDatabase
        initAddDiaryButton()
    }
    private fun initAddDiaryButton(){
        addDiaryButton.setOnClickListener{
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }
}