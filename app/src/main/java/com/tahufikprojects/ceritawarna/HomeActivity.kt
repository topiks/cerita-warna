package com.tahufikprojects.ceritawarna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.ceritawarna.deteksiwarna.CapActivity
import com.tahufikprojects.ceritawarna.datawarna.CobaActivity
import com.tahufikprojects.ceritawarna.utils.Preferences
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        preferences = Preferences(this)

        if(preferences.getValues("status").equals("1"))
        {
            finishAffinity()

            var goHome = Intent(this@HomeActivity, com.tahufikprojects.ceritawarna.CobaActivity::class.java)
            goHome.putExtra("USERNAME", preferences.getValues("nama"));
            startActivity(goHome)
        }

        // debugging deteksi warna
        btn_reset.setOnClickListener {
            preferences.setValues("onboarding", "")
            preferences.setValues("status", "")
            finishAffinity()

            var reset = Intent(this@HomeActivity, OnBoardingActivity::class.java)
            startActivity(reset)
        }

        sliderku.setOnClickListener {

            var kamera = Intent(this@HomeActivity, com.tahufikprojects.ceritawarna.CobaActivity::class.java)
            startActivity(kamera)
        }
//
//        btn_cap.setOnClickListener {
//
//            var pick = Intent(this@HomeActivity, CapActivity::class.java)
//            startActivity(pick)
//        }
//
//        coba_full.setOnClickListener {
//
//            var pick = Intent(this@HomeActivity, CobaActivity::class.java)
//            startActivity(pick)
//        }
    }
}