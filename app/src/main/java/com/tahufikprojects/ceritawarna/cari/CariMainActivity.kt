package com.tahufikprojects.ceritawarna.cari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.ceritawarna.R
import com.tahufikprojects.ceritawarna.databinding.ActivityCariMainBinding
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.adapters.SearchViewBindingAdapter
import com.google.firebase.database.*
import com.tahufikprojects.ceritawarna.autentikasiuser.User
import com.tahufikprojects.ceritawarna.utils.Preferences
import kotlinx.android.synthetic.main.activity_cari_main.*

class CariMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityCariMainBinding
    lateinit var mDatabaseJurusan : DatabaseReference
    lateinit var preferences: Preferences
    lateinit var dataInputSearch: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDatabaseJurusan = FirebaseDatabase.getInstance().getReference("Jurusan")
        preferences = Preferences(this)

        binding = ActivityCariMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = arrayOf("aaaa", "bbbb", "cccc", "AAAA", "elektro", "Elektro", "Teknik Elektro", "teknik elektro")

//        button_debug_jurusan.setOnClickListener {
//            cariDataJurusan()
//        }

        val userAdapter : ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            user
        )

        binding.userList.adapter = userAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                binding.searchView.clearFocus()
                if(user.contains(query))
                {
                    userAdapter.filter.filter(query)
                    Toast.makeText(this@CariMainActivity, "submit", Toast.LENGTH_LONG).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                userAdapter.filter.filter(newText)
//                Toast.makeText(this@CariMainActivity, newText, Toast.LENGTH_LONG).show()
                cariDataJurusan(newText.toString())
                return false
            }
        })

    }

    private fun cariDataJurusan(input: String) {
        mDatabaseJurusan.child(input).addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var jurusan = snapshot.getValue(Jurusan::class.java)
                if ( jurusan == null)
                    Toast.makeText(this@CariMainActivity, "null", Toast.LENGTH_LONG).show()
                else
                    preferences.setValues("Kampus", jurusan?.Kampus.toString())
                    Toast.makeText(this@CariMainActivity, jurusan?.Kampus.toString(), Toast.LENGTH_LONG).show()

            }
        }
        )
    }
}