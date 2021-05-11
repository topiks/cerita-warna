package com.tahufikprojects.ceritawarna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.tahufikprojects.ceritawarna.home.HomeActivity
import com.tahufikprojects.ceritawarna.utils.Preferences
import kotlinx.android.synthetic.main.activity_daftar.*
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {
    lateinit var preferences:Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        preferences = Preferences(this)
        if(preferences.getValues("onboarding").equals("1"))
        {
            finishAffinity()

            var goHome = Intent(this@OnBoardingActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        btn_masuk.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity()

            var intent = Intent(this@OnBoardingActivity, MasukActivity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener {
            var intent = Intent(this@OnBoardingActivity, DaftarActivity::class.java)
            startActivity(intent)

            }
        }
    }
