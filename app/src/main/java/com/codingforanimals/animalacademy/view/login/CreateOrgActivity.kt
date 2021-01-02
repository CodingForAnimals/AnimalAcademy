package com.codingforanimals.animalacademy.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.login.LoginContract
import com.codingforanimals.animalacademy.presenter.login.CreateOrgPresenter
import com.codingforanimals.animalacademy.helpers.utils.Utils
import kotlinx.android.synthetic.main.activity_create_org.*

class CreateOrgActivity : AppCompatActivity(), LoginContract.View.CreateOrg {

    private val presenter = CreateOrgPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_org)

        create_btn.setOnTouchListener(Utils.getResizerOnTouchListener(create_btn))
        create_btn.setOnClickListener {
            presenter.createOrgIntent(
                name_txt.text.toString().trim(),
                email_txt.text.toString().trim(),
                password_txt.text.toString(),
                confpassword_txt.text.toString()
            )
        }


    }

    override fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun startWelcomeActivity() {
        val welcomeIntent = Intent(this, WelcomeActivity::class.java)
        startActivity(welcomeIntent)
    }

    override fun showProgressbar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progress_bar.visibility = View.INVISIBLE
    }
}