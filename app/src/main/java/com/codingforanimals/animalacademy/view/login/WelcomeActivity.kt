package com.codingforanimals.animalacademy.view.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.login.LoginContract
import com.codingforanimals.animalacademy.presenter.login.WelcomePresenter
import com.codingforanimals.animalacademy.helpers.utils.Utils
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity(), LoginContract.View.Welcome {

    private val presenter = WelcomePresenter(this, this)

    private var titleTxt: TextView? = null
    private var subtitleTxt: TextView? = null
    private var startBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        postponeEnterTransition()
        presenter.shouldExit(intent)
        bindView()

        lifecycleScope.launch {
            presenter.buildAndBindTexts()
            setStartBtnListeners()
            startPostponedEnterTransition()
        }
    }


    override fun onBackPressed() {
        this.moveTaskToBack(true)
    }

    override fun bindTexts(title: String, subtitle: String) {
        titleTxt?.text = title
        subtitleTxt?.text = subtitle
    }

    private fun bindView() {
        titleTxt = findViewById(R.id.title_txt)
        subtitleTxt = findViewById(R.id.subtitle_txt)
        startBtn = findViewById(R.id.start_btn)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setStartBtnListeners() {
        startBtn?.setOnTouchListener(Utils.getResizerOnTouchListener(startBtn))
        startBtn?.setOnClickListener { presenter.startMainActivity() }
    }
}
