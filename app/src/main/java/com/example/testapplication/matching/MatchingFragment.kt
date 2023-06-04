package com.example.testapplication.matching

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.create_room.CreateRoomLocalActivity
import com.example.testapplication.databinding.FragmentMatchingBinding

class MatchingFragment: Fragment(), View.OnClickListener  {
    private var _binding: FragmentMatchingBinding? = null
    private val binding: FragmentMatchingBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener {
            activity?.let{
                val intent1 = Intent (it, MatchingLocalActivity::class.java)
                it.startActivity(intent1)
            }
        }
        binding.btnCreate.setOnClickListener {
            activity?.let{
                val intent = Intent (it, CreateRoomLocalActivity::class.java)
                it.startActivity(intent)
            }

        }
        setOnClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setOnClickListener() {
        val btnSequence = binding.container2.children
        btnSequence.forEach { btn ->
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_search -> {
                val intent = Intent(getActivity(), MatchingLocalActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_create -> {
                val intent = Intent(getActivity(), CreateRoomLocalActivity::class.java)
                startActivity(intent)
            }
        }
    }
}


