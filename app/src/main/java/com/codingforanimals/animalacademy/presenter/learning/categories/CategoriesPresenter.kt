package com.codingforanimals.animalacademy.presenter.learning.categories

import android.os.Bundle
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.learning.CategoriesContract
import com.codingforanimals.animalacademy.contract.main.MainContract
import com.codingforanimals.animalacademy.model.data.models.learning.Category
import com.codingforanimals.animalacademy.model.domain.interactor.learning.CategoriesInteractor

class CategoriesPresenter(
    private val iView: CategoriesContract.View,
    private val iMainView: MainContract.View
) : CategoriesContract.Actions {

    var videosAdapter: CategoriesAdapter? = null
    private var articlesAdapter: CategoriesAdapter? = null
    private val interactor = CategoriesInteractor()

    override suspend fun fetchAndBuildRecyclerViews() {
        iMainView.showProgress()

        if (videosAdapter == null) {
            val videoCategories = interactor.getVideoCategories()
            videosAdapter = buildAdapter(videoCategories)
        }

        if (articlesAdapter == null) {
            val articlesCategories = interactor.getArticlesCategories()
            articlesAdapter = buildAdapter(articlesCategories)

        }
        iView.buildVideosRV(videosAdapter)
        iView.buildArticlesRV(articlesAdapter)
        iMainView.hideProgress()

    }

    private fun buildAdapter(categories: MutableList<Category>): CategoriesAdapter {
        return CategoriesAdapter(categories) { category, view ->
            val bundle = Bundle()
            bundle.putParcelable("category", category)
            val extras = FragmentNavigatorExtras(
                view[0] to (category.title + "src")
            )
            iMainView.navigateWithOptions(R.id.navigation_element, bundle, null, extras)
        }
    }

}