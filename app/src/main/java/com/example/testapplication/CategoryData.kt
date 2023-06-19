package com.example.testapplication

import androidx.annotation.DrawableRes

data class CategoryData(
    @DrawableRes val category_img: Int,
    val category_title : String,
    val category_genre: String,
    val category_diff: String,
    val category_area: String
)
