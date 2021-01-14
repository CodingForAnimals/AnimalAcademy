package com.codingforanimals.animalacademy.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.login.LoginContract
import com.codingforanimals.animalacademy.presenter.login.CreateUserPresenter
import com.codingforanimals.animalacademy.helpers.utils.Utils
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity(), LoginContract.View.CreateUser {

    private val presenter = CreateUserPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        logo.setOnTouchListener(Utils.getResizerOnTouchListener(logo))
        logo.setOnClickListener {  }

        create_btn.setOnTouchListener(Utils.getResizerOnTouchListener(create_btn))
        create_btn.setOnClickListener {
            presenter.createUserIntent(
                name_txt.text.toString().trim(),
                email_txt.text.toString().trim(),
                password_txt.text.toString().trim(),
                confpassword_txt.text.toString().trim()
            )
        }
    }

    override fun showProgressbar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progress_bar.visibility = View.INVISIBLE
    }

}
