package com.example.testapplication.chat

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.create_room.CreateTotalActivity
import com.example.testapplication.databinding.ActivityCreateTotalBinding
import com.example.testapplication.databinding.LayoutChatlistBinding
import com.example.testapplication.getRoomListModel
import com.example.testapplication.model.request.creatroomrequest

class ChatlistAdapter(context : Context): RecyclerView.Adapter<ChatlistAdapter.ChatlistViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context)}
    private var categorylist: List<CreateTotalActivity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatlistViewHolder {
        val binding = ActivityCreateTotalBinding.inflate(inflater, parent,false)
        return ChatlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatlistViewHolder, position: Int) {
        holder.bind(categorylist[position])
    }

    override fun getItemCount(): Int {
        return categorylist.size
    }

    class ChatlistViewHolder(
        private val binding: ActivityCreateTotalBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CreateTotalActivity) {
            binding.tvRoomtitle.setText(data.title)

        }
    }

    fun setRepoList(repoList: List<CreateTotalActivity>){
        this.categorylist = repoList.toList()
        notifyDataSetChanged()
    }
}
