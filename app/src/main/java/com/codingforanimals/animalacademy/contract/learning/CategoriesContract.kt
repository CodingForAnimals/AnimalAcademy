package com.codingforanimals.animalacademy.contract.learning

import androidx.recyclerview.widget.RecyclerView
import com.codingforanimals.animalacademy.presenter.learning.categories.CategoryViewHolder

interface CategoriesContract {

    interface View {
        fun buildVideosRV(adapter: RecyclerView.Adapter<CategoryViewHolder>?)
        fun buildArticlesRV(adapter: RecyclerView.Adapter<CategoryViewHolder>?)
    }

    interface Actions {
        suspend fun fetchAndBuildRecyclerViews()
    }

    interface Data {

    }
}