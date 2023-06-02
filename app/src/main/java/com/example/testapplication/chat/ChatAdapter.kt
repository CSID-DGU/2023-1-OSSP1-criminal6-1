package com.example.testapplication.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.multi_type1

class chatAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var datas = mutableListOf<chatData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.chat_row,
                    parent,
                    false
                )
                MultiViewHolder1(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.chat_row_my,
                    parent,
                    false
                )
                MultiViewHolder2(view)
            }
        }
    }

    override fun getItemCount(): Int = datas.size

    override fun getItemViewType(position: Int): Int {
        return datas[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (datas[position].type) {
            multi_type1 -> {
                (holder as MultiViewHolder1).bind(datas[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as MultiViewHolder2).bind(datas[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    inner class MultiViewHolder1(view: View) : RecyclerView.ViewHolder(view) {

        private val Name: TextView = view.findViewById(R.id.chat_nickname)
        private val Msg: TextView = view.findViewById(R.id.chat_msg)
        private val Time: TextView = view.findViewById(R.id.chat_time)

        fun bind(item: chatData) {
            Name.text = item.name
            Msg.text = item.msg
            Time.text = item.time

        }
    }

    inner class MultiViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        private val Msg: TextView = view.findViewById(R.id.my_chat_msg)
        private val Time: TextView = view.findViewById(R.id.my_chat_time)

        fun bind(item: chatData) {
            Msg.text = item.msg
            Time.text = item.time

        }
    }
}