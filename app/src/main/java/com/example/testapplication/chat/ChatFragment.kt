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

    private val api = APIS.create()
/*
    private val mockRepoList = listOf<ChatlistData> (
        ChatlistData(
            chatlist_img = R.drawable.room1,
            chatlist_title ="즐기는자가 1류다,,,",
            chatlist_hashtag = "#방탈출 #홍대 #초보자_환영 #나도잡혔어"
        ),
        ChatlistData(
            chatlist_img = R.drawable.gongpo,
            chatlist_title ="공포 3단계 가보자고~",
            chatlist_hashtag = "#방탈출 #강남 #눈물각 #앞장서줘"
        ),
    )
*/
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

        api.getRoomList(
            userId.toString()
        ).enqueue(object : retrofit2.Callback<getRoomListModel> {
            override fun onResponse(
                call: Call<getRoomListModel>,
                response: Response<getRoomListModel>
            ) {
                Log.d("유저 정보", "${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.setRepoList(it.roomList)
                    }
                }
            }

            override fun onFailure(call: Call<getRoomListModel>, t: Throwable) {
                Log.d("getRoomList", "fail")
            }

        })



        
        //binding..setOnClickListener {
          //  activity?.let{
            //    val intent = Intent (it, ChatActivity::class.java)
              //  it.startActivity(intent)
            //}
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}