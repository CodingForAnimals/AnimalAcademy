package com.vegdev.vegacademy.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.google.firebase.auth.FirebaseAuth
import com.vegdev.vegacademy.R
import com.vegdev.vegacademy.utils.LayoutUtils
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity() {

    private val layoutUtils = LayoutUtils()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        firebaseAuth = FirebaseAuth.getInstance()

        start_create_user_filled_btn.setOnClickListener {

            val name = name_txt.text.toString()
            val email = email_txt.text.toString()
            val password = password_txt.text.toString()
            val confPassword = confpassword_txt.text.toString()

            if (password != confPassword) {
                layoutUtils.createToast(this, "Las contraseñas no coinciden")

            } else {


                val animation = TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
                )
                animation.duration = 500

                progress_bar.startAnimation(animation)
                progress_bar_background.startAnimation(animation)
                progress_bar.visibility = View.VISIBLE
                progress_bar_background.visibility = View.VISIBLE

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    val intent = Intent(this, WelcomeActivity::class.java)
                    this.startActivity(intent)
                }.addOnFailureListener {

                }
            }

        }

    }

}
