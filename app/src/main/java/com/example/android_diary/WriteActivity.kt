package com.example.android_diary

import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.android_diary.DBHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WriteActivity: AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase

    val saveButton:Button by lazy {
        findViewById<Button>(R.id.saveButton)
    }
    val dateYear:TextView by lazy{
        findViewById<TextView>(R.id.DateYear)
    }
    val dateMonth:TextView by lazy{
        findViewById<TextView>(R.id.DateMonth)
    }
    val dateDay:TextView by lazy{
        findViewById<TextView>(R.id.DateDay)
    }
    val thanks1EditText:EditText by lazy{
        findViewById<EditText>(R.id.thanksAnswer1EditText)
    }
    val thanks2EditText:EditText by lazy{
        findViewById<EditText>(R.id.thanksAnswer2EditText)
    }
    val thanks3EditText:EditText by lazy{
        findViewById<EditText>(R.id.thanksAnswer3EditText)
    }
    val betterDay1EditText:EditText by lazy{
        findViewById<EditText>(R.id.BetterDay1EditText)
    }
    val betterDay2EditText:EditText by lazy{
        findViewById<EditText>(R.id.BetterDay2EditText)
    }
    val betterDay3EditText:EditText by lazy{
        findViewById<EditText>(R.id.BetterDay3EditText)
    }
    val positiveSentenceEditText:EditText by lazy{
        findViewById<EditText>(R.id.positiveSentenceAnswer)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        dbHelper = DBHelper(this, "myDiary.db", null, 1)
        database = dbHelper.writableDatabase

        initDate()
        initsaveButton()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initDate(){
        var nowDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var splitDate = nowDate.split("-")
        dateYear.text = splitDate[0]
        dateMonth.text = splitDate[1]
        dateDay.text = splitDate[2]

    }
    private fun initsaveButton(){
        saveButton.setOnClickListener{
            var date = dateYear.text.toString()+dateMonth.text.toString()+dateDay.text.toString()
            var query = "INSERT INTO mytable values(${date}," +
                    " ${thanks1EditText.text.toString()}, ${thanks2EditText.text.toString()}, ${thanks3EditText.text.toString()}," +
                    "${betterDay1EditText.text.toString()}, ${betterDay2EditText.text.toString()}, ${betterDay3EditText.text.toString()}," +
                    "${positiveSentenceEditText.text.toString()});"
            finish()
        }
    }
}

