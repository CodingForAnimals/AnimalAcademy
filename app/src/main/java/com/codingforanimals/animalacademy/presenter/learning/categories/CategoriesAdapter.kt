package com.codingforanimals.animalacademy.presenter.learning.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.model.data.models.learning.Category
import com.codingforanimals.animalacademy.helpers.utils.Utils
import kotlinx.android.synthetic.main.categories_single.view.*

class CategoriesAdapter(
    private val videoCategories: MutableList<Category>,
    private val clickListener: (Category, List<View>) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_single, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int = videoCategories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = videoCategories[position]
        holder.bindCategory(category)
        holder.itemView.src.transitionName = category.title + "src"
        holder.itemView.title.transitionName = category.title + "title"
        holder.itemView.transitionName = category.title + "back"
        holder.itemView.setOnTouchListener(Utils.getResizerOnTouchListener(holder.itemView))
        holder.itemView.setOnClickListener {
            clickListener(
                category,
                listOf(holder.itemView.src, holder.itemView.title, holder.itemView)
            )
        }
    }
}

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindCategory(category: Category) {
        itemView.title.text = category.title
        Glide.with(itemView.context).load(category.icon).into(itemView.src)
    }


}