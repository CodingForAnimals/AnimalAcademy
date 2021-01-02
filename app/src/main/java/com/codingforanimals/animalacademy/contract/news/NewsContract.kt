package com.codingforanimals.animalacademy.contract.news

import androidx.recyclerview.widget.RecyclerView
import com.codingforanimals.animalacademy.presenter.news.pager.NewsViewHolder

interface NewsContract {

    interface View {
        fun hideLayout()
        fun showLayout()
    }
    interface PagerView {
        fun buildRv(adapter: RecyclerView.Adapter<NewsViewHolder>)
    }
    interface PagerActions {
        suspend fun fetchNewsAndBuildRecyclerViews(position: Int)
    }
}