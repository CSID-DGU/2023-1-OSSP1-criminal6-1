package com.example.testapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.CategoryAdapter
import com.example.testapplication.databinding.FragmentCategoryBinding

class CategoryFragment: Fragment() {
    private var _binding : FragmentCategoryBinding? = null
    private val binding : FragmentCategoryBinding
        get() = requireNotNull(_binding)

    private val mockRepoList = listOf<CategoryData> (
        CategoryData(
            category_img = R.drawable.category1jpg,
            category_title ="사람들은 그것을 행복이라 부르기로 했다",
            category_genre = "장르: 판타지",
            category_diff = "난이도: 중",
            category_area = "지역: 강남"
        ),
        CategoryData(
            category_img = R.drawable.category2,
            category_title ="단잠",
            category_genre = "장르: 감성",
            category_diff = "난이도: 하",
            category_area = "지역: 잠실"
        ),
        CategoryData(
            category_img = R.drawable.category3,
            category_title ="꼬레아 우라",
            category_genre = "장르: 판타지",
            category_diff = "난이도: 하",
            category_area = "지역: 홍대"
        ),
        CategoryData(
            category_img = R.drawable.category4,
            category_title ="삐릿-뽀",
            category_genre = "장르: 미션",
            category_diff = "난이도: 중",
            category_area = "지역: 홍대"
        ),
        CategoryData(
            category_img = R.drawable.category5,
            category_title ="홀리데이",
            category_genre = "장르: 판타지",
            category_diff = "난이도: 중",
            category_area = "지역: 홍대"
        ),
        CategoryData(
            category_img = R.drawable.category6,
            category_title ="네드",
            category_genre = "장르: 모험",
            category_diff = "난이도: 중",
            category_area = "지역: 강남"
        ),
        CategoryData(
            category_img = R.drawable.category7,
            category_title ="US",
            category_genre = "장르: 판타지",
            category_diff = "난이도: 중",
            category_area = "지역: 강남"
        ),
        CategoryData(
            category_img = R.drawable.category8,
            category_title ="엔제리오",
            category_genre = "장르: 스토리",
            category_diff = "난이도: 중",
            category_area = "지역: 강남"
        ),
        CategoryData(
            category_img = R.drawable.category9,
            category_title ="포레스트",
            category_genre = "장르: 미션",
            category_diff = "난이도: 중",
            category_area = "지역: 강남"
        ),
        CategoryData(
            category_img = R.drawable.category10,
            category_title ="디어 마르시",
            category_genre = "장르: 스토리",
            category_diff = "난이도: 상",
            category_area = "지역: 건대"
        )

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CategoryAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        adapter.setRepoList(mockRepoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}