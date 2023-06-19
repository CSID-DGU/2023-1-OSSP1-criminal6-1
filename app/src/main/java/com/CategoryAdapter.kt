package com

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.CategoryData
import com.example.testapplication.databinding.LayoutCategoryListBinding


class CategoryAdapter(context : Context):RecyclerView.Adapter<CategoryAdapter.RecyclerViewViewHolder>() {

    private val inflater by lazy {LayoutInflater.from(context)}
    private var repoList: List<CategoryData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val binding = LayoutCategoryListBinding.inflate(inflater, parent,false)
        return RecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(repoList[position])

        //holder.itemView.setOnClickListener {
          //  Log.d("클릭","클릭")
            //val intent = Intent(holder.itemView?.context, MatchingEnd::class.java)
            //ContextCompat.startActivity(holder.itemView.context, intent,null)
        //}
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    class RecyclerViewViewHolder(
        private val binding: LayoutCategoryListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryData) {
            binding.imgCategory.setImageDrawable(binding.root.context.getDrawable(data.category_img))
            binding.titleCategory.setText(data.category_title)
            binding.genreCategory.setText(data.category_genre)
            binding.diffCategory.setText(data.category_diff)
            binding.areaCategory.setText(data.category_area)

        }
    }

    fun setRepoList(repoList: List<CategoryData>){
        this.repoList = repoList.toList()
        notifyDataSetChanged()
    }
}

