package com.tahufikprojects.ceritawarna.autentikasiuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.tahufikprojects.ceritawarna.R
import com.tahufikprojects.ceritawarna.HomeActivity
import com.tahufikprojects.ceritawarna.utils.Preferences
import kotlinx.android.synthetic.main.activity_masuk.*

class MasukActivity : AppCompatActivity() {

    lateinit var inputUsername:String
    lateinit var inputPass:String

    lateinit var mDatabase : DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

//        preferences.setValues("onboarding", "1")
        if(preferences.getValues("status").equals("1"))
        {
            finishAffinity()

            var goHome = Intent(this@MasukActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        btn_masuk_confirm.setOnClickListener {
            inputUsername = edit_username_masuk.text.toString()
            inputPass = edit_pass_masuk.text.toString()

            if(inputUsername.equals(""))
            {
                edit_username_masuk.error = "Silahkan Tulis Username Anda"
                edit_username_masuk.requestFocus()
            }
            else if(inputPass.equals(""))
            {
                edit_pass_masuk.error = "Silahkan Tulis Password Anda"
                edit_pass_masuk.requestFocus()
            }
            else
            {
                pushLogin(inputUsername, inputPass)
            }
        }
    }

    private fun pushLogin(inputUsername: String, inputPass: String) {
        mDatabase.child(inputUsername).addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(databaseError: DatabaseError)
            {
//                Toast.makeText(this@MasukActivity, databaseError.message, Toast.LENGTH_LONG).show()
                Toast.makeText(this@MasukActivity, "database error", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                var user = dataSnapshot.getValue(User::class.java)
                if(user == null)
                {
                    Toast.makeText(this@MasukActivity, "Username Tidak Ditemukan", Toast.LENGTH_LONG).show()
                }
                else
                {
                    if(user.password.equals(inputPass))
                    {
                        preferences.setValues("nama", user.email.toString())
                        preferences.setValues("email", user.nama.toString())
                        preferences.setValues("status", "1")
                        
                        var intent = Intent(this@MasukActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    }
                    else
                    {
                        Toast.makeText(this@MasukActivity, "Password Anda Salah", Toast.LENGTH_LONG).show()
                    }
                }

            }
        })

    }
}