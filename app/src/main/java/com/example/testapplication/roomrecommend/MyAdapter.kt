package com.example.testapplication.roomrecommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.ActivityItemrecyclerciewBinding
import com.example.testapplication.model.response.getroomlistresponse
import com.example.testapplication.util.ItemDiffCallback

class MyAdapter : ListAdapter<getroomlistresponse.Data, MyAdapter.MyViewHolder>(
    ItemDiffCallback<getroomlistresponse.Data>(
        onItemsTheSame = { old, new -> old.roomID == new.roomID},
        onContentsTheSame = { old, new -> old == new }
    )
) {

    class MyViewHolder(
        private val binding: ActivityItemrecyclerciewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(roomdata: getroomlistresponse.Data) {
            binding.roomdata = roomdata
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ActivityItemrecyclerciewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}