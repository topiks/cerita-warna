package com.tahufikprojects.ceritawarna.autentikasiuser

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.tahufikprojects.ceritawarna.R
import kotlinx.android.synthetic.main.activity_daftar.*
import com.tahufikprojects.ceritawarna.HomeActivity


class DaftarActivity : AppCompatActivity() {

    lateinit var inputNama:String
    lateinit var inputEmail:String
    lateinit var inputPass:String
    lateinit var inputUsername:String

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseDatabase: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseDatabase.getReference("User")

        btn_daftar_confirm.setOnClickListener {

            inputNama = edit_nama_daftar.text.toString()
            inputEmail = edit_email_daftar.text.toString()
            inputPass = edit_pass_daftar.text.toString()
            inputUsername = edit_username_daftar.text.toString()

            if(inputNama.equals(""))
            {
                edit_nama_daftar.error = "Silhakan isi Nama Anda"
                edit_nama_daftar.requestFocus()
            }
            else if(inputUsername.equals(""))
            {
                edit_username_daftar.error = "Silhakan isi Username Anda"
                edit_username_daftar.requestFocus()
            }
            else if(inputEmail.equals(""))
            {
                edit_email_daftar.error = "Silhakan isi Email Anda"
                edit_email_daftar.requestFocus()
            }
            else if(inputPass.equals(""))
            {
                edit_pass_daftar.error = "Silhakan isi Password Anda"
                edit_pass_daftar.requestFocus()
            }
            else
            {
                saveDataBaru(inputNama, inputUsername, inputEmail, inputPass)
            }

        }
    }

    private fun saveDataBaru(inputNama: String, inputUsername: String, inputEmail: String, inputPass: String) {
        var user = User()
        user.nama = inputNama
        user.email = inputEmail
        user.password = inputPass
        user.username = inputUsername

        if(inputUsername != null)
        {
            cekDataPrimary(inputUsername, user)
        }
    }

    private fun cekDataPrimary(inputUsername: String, data: User) {
        mDatabaseReference.child(inputUsername).addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(databaseError: DatabaseError)
            {
                Toast.makeText(this@DaftarActivity, "database error", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                var user = dataSnapshot.getValue(User::class.java)
                if(user == null)
                {
                    mDatabaseReference.child(inputUsername).setValue(data)

                    var intent = Intent(this@DaftarActivity, HomeActivity::class.java)
                    startActivity(intent)

                    finishAffinity()
                }
                else
                {
                    Toast.makeText(this@DaftarActivity, "email sudah ada", Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}