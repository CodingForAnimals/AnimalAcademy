package com.codingforanimals.animalacademy.presenter.login

import android.content.Context
import android.content.Intent
import com.codingforanimals.animalacademy.contract.login.LoginContract
import com.codingforanimals.animalacademy.helpers.utils.Utils
import com.codingforanimals.animalacademy.model.data.dataholders.UserDataHolder
import com.codingforanimals.animalacademy.view.main.MainActivity

class WelcomePresenter(
    private val context: Context,
    private val iView: LoginContract.View.Welcome
) : LoginContract.Actions.Welcome {

    override suspend fun buildAndBindTexts() {
        iView.bindTexts(buildTitle(), buildSubtitle())

    }

    override fun shouldExit(intent: Intent?) {
        intent?.let {
            if (it.getBooleanExtra("EXIT", false)) {
                iView.onBackPressed()
            }
        }
    }

    override fun startMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    private suspend fun buildTitle(): String =
        "Hola,  " + Utils.getFirstWord(UserDataHolder.getUserData().username)

    private fun buildSubtitle(): String {
        return if (UserDataHolder.currentUser.isOrg) {
            "Desde Academia Animal te brindamos tu propio espacio para que reunas y capacites a tus miembros con material personalizado"
        } else {
            "Desde Academia Animal trabjamos duro para brindarte todo lo necesario para que ayudes al movimiento de los derechos de los animales"
        }
    }
}