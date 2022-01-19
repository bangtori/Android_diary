package com.example.android_diary

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class ContentsActivity: AppCompatActivity()  {
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase

    val yearTextView : TextView by lazy{
        findViewById<TextView>(R.id.contentsDateYear)
    }
    val monthTextView : TextView by lazy{
        findViewById<TextView>(R.id.contentsDateMonth)
    }
    val dayTextView : TextView by lazy{
        findViewById<TextView>(R.id.contentsDateDay)
    }
    val thanksContents1 : TextView by lazy {
        findViewById<TextView>(R.id.thanksContents1)
    }
    val thanksContents2 : TextView by lazy {
        findViewById<TextView>(R.id.thanksContents2)
    }
    val thanksContents3 : TextView by lazy {
        findViewById<TextView>(R.id.thanksContents3)
    }
    val betterDayContents1 : TextView by lazy{
        findViewById<TextView>(R.id.betterDayContents1)
    }
    val betterDayContents2 : TextView by lazy{
        findViewById<TextView>(R.id.betterDayContents2)
    }
    val betterDayContents3 : TextView by lazy{
        findViewById<TextView>(R.id.betterDayContents3)
    }
    val positiveSentence :TextView by lazy{
        findViewById<TextView>(R.id.positiveSentenceContents)
    }
    val removeButton : Button by lazy{
        findViewById<Button>(R.id.removeButton)
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents)
        val intent = intent
        val id = intent.getIntExtra("id", -1)
        if (id == -1){
            Toast.makeText(this,"일기를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
        dbHelper = DBHelper(this, "myDiary.db", null, 1)
        database = dbHelper.writableDatabase

        val sql = "select * from mytable where _id=${id}"
        val cursor: Cursor?= database.rawQuery(sql, null)
        if(cursor != null && cursor.moveToFirst()) {
            val date = cursor.getString(1)
            yearTextView.text = date.substring(0,4)
            monthTextView.text = date.substring(4,6)
            dayTextView.text = date.substring(6)

            thanksContents1.text = "1. "+cursor.getString(2)
            thanksContents2.text = "2. "+cursor.getString(3)
            thanksContents3.text = "3. "+cursor.getString(4)

            betterDayContents1.text = "1. "+cursor.getString(5)
            betterDayContents2.text = "2. "+cursor.getString(6)
            betterDayContents3.text = "3. " + cursor.getString(7)

            positiveSentence.text = cursor.getString(8)


        }
    }
}