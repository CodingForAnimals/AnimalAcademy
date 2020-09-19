package com.vegdev.vegacademy.presenter.learning.elements

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.vegdev.vegacademy.model.data.models.Category
import com.vegdev.vegacademy.model.domain.interactor.learning.ElementsInteractor
import com.vegdev.vegacademy.utils.LayoutUtils
import com.vegdev.vegacademy.view.learning.elements.ElementsFragmentDirections
import com.vegdev.vegacademy.view.learning.elements.ElementsView
import com.vegdev.vegacademy.view.main.main.MainView

class ElementsPresenter(
    val context: Context,
    private val view: ElementsView,
    private val iMainView: MainView,
    private val category: Category,
    private val interactor: ElementsInteractor
) {

    suspend fun fetchAndBuildRecyclerView(category: Category) {
//        view.hideLayout()
        iMainView.showProgress()

        val path = "learning/${category.type}/${category.cat}"
        val list = interactor.getElements(path)

        view.buildRecyclerView(ElementsAdapter(list) {
            if (category.type == "videos") {
                iMainView.onVideoClicked(it.link)
            } else {
                val directionsToWebView =
                    ElementsFragmentDirections.actionNavigationVideosToNavigationWebview(it.link)
                iMainView.navigateToDirection(directionsToWebView)
            }
        })

//        view.showLayout()
        iMainView.hideProgress()
    }

    fun buildAndSetBackgroundColor(imageUrl: String) {
        Glide.with(context).asBitmap().load(imageUrl).into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                val colors = LayoutUtils().getAverageColor(bitmap)
                view.setBackgroundColor(colors, bitmap, category.socials, category.title)
            }
        })
    }

    fun buildAndStartInstagramIntent(instagramUrl: String) {

        val url = "http://instagram.com/_u/$instagramUrl"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.instagram.android")

        if (isInstagramIntentAvailable(context, intent)) {
            context.startActivity(intent)
        } else {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/$instagramUrl")
                )
            )
        }
    }

    private fun isInstagramIntentAvailable(context: Context?, intent: Intent): Boolean {
        val packageManager = context?.packageManager
        val list = packageManager?.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list?.size!! > 0
    }
}