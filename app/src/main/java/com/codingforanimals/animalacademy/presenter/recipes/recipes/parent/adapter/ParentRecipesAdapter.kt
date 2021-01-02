package com.codingforanimals.animalacademy.presenter.recipes.recipes.parent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.recipes.recipes.RecipesContract
import com.codingforanimals.animalacademy.model.data.models.recipes.TypesRecipe
import com.codingforanimals.animalacademy.presenter.recipes.recipes.parent.ParentRecipesPresenter
import kotlinx.android.synthetic.main.recipes_parent_single.view.*


class ParentRecipesAdapter(
    iRecipesView: RecipesContract.View,
    private val types: TypesRecipe,
    private val scrollStateHolder: ScrollStateHolder
) :
    RecyclerView.Adapter<ParentRecipesViewHolder>() {

    private val presenter = ParentRecipesPresenter(iRecipesView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentRecipesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipes_parent_single, parent, false)
        return ParentRecipesViewHolder(itemView, scrollStateHolder)
    }

    override fun getItemCount(): Int = types.types.size

    override fun onBindViewHolder(holder: ParentRecipesViewHolder, position: Int) {
        val type: String = types.types[position]

        presenter.bindViewHolder(type, position, holder)

        holder.itemView.recipe_parent_searchbar.setOnEditorActionListener { textView, _, _ ->
            presenter.handleSearchBarAction(textView.text.toString(), type, position, holder)
        }

        holder.itemView.recipe_parent_searchbar.setOnTouchListener(
            presenter.onTouchSearchBarIcon(type, position, holder)
        )

        holder.itemView.recipe_parent_search_icon.setOnClickListener {
            presenter.showSearchBar(holder)
        }

        holder.itemView.recipe_parent_close_searchbar.setOnClickListener {
            presenter.hideSearchBar(type, position, holder)
        }

    }
}