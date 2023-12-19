package com.example.remotedataproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.remotedataproject.R
import com.example.remotedataproject.model.Post

class PostAdapter(private val context: Context, private val list: List<Post>) : BaseAdapter() {
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = layoutInflater.inflate(R.layout.post_item, parent, false)

        val tvPostId = rowView.findViewById<TextView>(R.id.tvPostId)
        val tvPostTitle = rowView.findViewById<TextView>(R.id.tvPostTitle)

        tvPostId.text = list[position].id.toString()
        tvPostTitle.text = list[position].title
        return rowView
    }
}