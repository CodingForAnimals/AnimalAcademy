package com.codingforanimals.animalacademy.contract.learning

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.codingforanimals.animalacademy.model.data.models.learning.Category
import com.codingforanimals.animalacademy.presenter.learning.elements.ElementViewHolder

interface ElementsContract {
    interface View {
        fun buildRecyclerView(adapter: RecyclerView.Adapter<ElementViewHolder>)
        fun setBackgroundColor(colors: List<Int>, bitmap: Bitmap, socials: String, title: String)
    }
    interface Actions {
        suspend fun fetchAndBuildRecyclerView(category: Category)
        fun buildAndSetBackgroundColor(imageUrl: String)
        fun buildAndStartInstagramIntent(instagramUrl: String)
    }
}