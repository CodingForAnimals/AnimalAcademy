package com.codingforanimals.animalacademy.presenter.recipes.suggestion.adapter.spinner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.codingforanimals.animalacademy.helpers.utils.Utils

class SpinnerAdapter(context: Context, resource: Int, private val types: List<String>) :
    ArrayAdapter<String>(context, resource) {

    override fun getCount(): Int = types.size
    override fun getItem(position: Int): String? = types[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = Utils.getTranslation(types[position], context)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        view.text = Utils.getTranslation(types[position], context)
        return view
    }
}