package com.codingforanimals.animalacademy.presenter.learning.elements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Timestamp
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.model.data.models.learning.LearningElement
import com.codingforanimals.animalacademy.helpers.utils.Utils
import kotlinx.android.synthetic.main.element_single.view.*
import java.util.concurrent.TimeUnit

class ElementsAdapter(
    private val list: MutableList<LearningElement>,
    val clickListener: (LearningElement) -> Unit
) :
    RecyclerView.Adapter<ElementViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_single, parent, false)
        return ElementViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val videoElement = list[position]
        holder.bindElement(videoElement)
        holder.itemView.src.setOnClickListener { clickListener(videoElement) }
    }
}

class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindElement(learningElement: LearningElement) {
        itemView.title.text = learningElement.title
        Glide.with(itemView.context).load(learningElement.icon).into(itemView.cat)

        val days =
            Utils.getDateDifference(
                learningElement.date,
                Timestamp.now(),
                TimeUnit.DAYS
            )

        val dateDiff = if (days != 0L) {
            "Hace $days días"
        } else {
            "Hoy"
        }

        itemView.days_ago.text = dateDiff
        itemView.desc.text = learningElement.desc
        Glide.with(itemView.context).load(learningElement.src).into(itemView.src)
    }
}