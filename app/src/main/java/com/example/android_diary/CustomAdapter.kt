package com.example.android_diary

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(val context: Context, var itemList:ArrayList<ListData>): BaseAdapter() {
//    var list : ArrayList<ListData> = ArrayList<ListData>()

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): Any {
        return itemList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val context : Context = parent!!.context
        var view= LayoutInflater.from(context).inflate(R.layout.list_item_view, null)
        val dateView = view.findViewById<TextView>(R.id.dateTextView)
        val subTitleView = view.findViewById<TextView>(R.id.subtitleTextView)
        val idValue = view.findViewById<TextView>(R.id.databaseIdValue)

        val item = itemList[position]
        dateView.text = item.date
        subTitleView.text = item.positiveSentence
        idValue.text = item.idValue.toString()

        return view
    }
    public fun addItemToList(id:Int,date : String, positiveSentence:String){
        val listData = ListData()

        listData.date = date
        listData.positiveSentence = positiveSentence
        listData.idValue = id

        itemList.add(listData)
    }
}