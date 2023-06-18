package com.example.testapplication.roomrecommend

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.ActivityItemrecyclerciewBinding
import com.example.testapplication.model.response.getroomlistresponse
import com.example.testapplication.util.ItemDiffCallback
import io.grpc.internal.DnsNameResolver.SrvRecord

class MyAdapter(
    private val addchatroom: (Int) -> Unit
) : ListAdapter<getroomlistresponse.Data, MyAdapter.MyViewHolder>(
    ItemDiffCallback<getroomlistresponse.Data>(
        onItemsTheSame = { old, new -> old.roomID == new.roomID },
        onContentsTheSame = { old, new -> old == new }
    )
) {

    class MyViewHolder(
        private val binding: ActivityItemrecyclerciewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            roomdata: getroomlistresponse.Data,
            addchatroom: (Int) -> Unit
        ) {
            binding.roomdata = roomdata
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                addchatroom(roomdata.roomID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ActivityItemrecyclerciewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position),addchatroom)
    }
}