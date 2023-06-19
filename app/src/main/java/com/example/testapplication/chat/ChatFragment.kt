package com.example.testapplication.chat

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.testapplication.databinding.FragmentChatBinding
import com.example.testapplication.getRoomListModel
import com.example.testapplication.service.APIS
import retrofit2.Call
import retrofit2.Response

class ChatFragment: Fragment() {
    private var _binding : FragmentChatBinding? = null
    private val binding : FragmentChatBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ChatlistAdapter(requireContext())
        binding.rvCategory.adapter = adapter

        val preferences = requireActivity().getSharedPreferences("userInfo", MODE_PRIVATE)
        val userId = preferences?.getString("userId", "")
        Log.d("asdfasdf", userId.toString());
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}