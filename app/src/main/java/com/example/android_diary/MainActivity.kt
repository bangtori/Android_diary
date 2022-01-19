package com.example.android_diary

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase

    val addDiaryButton : Button by lazy{
        findViewById<Button>(R.id.addDiaryButton)
    }
    val itemListView : ListView by lazy{
        findViewById<ListView>(R.id.itemListView)
    }
    var itemList = arrayListOf<ListData>()
    val itemAdapter = CustomAdapter(this, itemList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this, "myDiary.db", null, 1)
        database = dbHelper.writableDatabase
        createList()
        initAddDiaryButton()
    }

    override fun onRestart() {
        super.onRestart()
        createList()

    }
    private fun initAddDiaryButton(){
        addDiaryButton.setOnClickListener{
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }
    @SuppressLint("Range")
    private fun createList(){
        val sql = "select * from mytable"
        val cursor:Cursor? = database.rawQuery(sql, null)
        if (cursor != null) {
            itemAdapter.itemList.clear()
            while(cursor.moveToNext()){
                val id = cursor.getInt(0)
                val date = cursor.getString(1)
                val positiveSentence = cursor.getString(8)
                itemAdapter.addItemToList(id, date, positiveSentence)

            }
            itemListView.adapter = itemAdapter
            //클릭 이벤트 처리
            itemListView.setOnItemClickListener { parent, view, position, id ->
                var id = view.findViewById<TextView>(R.id.databaseIdValue).text.toString().toInt()
                val intent = Intent(this, ContentsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        }



    }
}