package com.tahufikprojects.ceritawarna.tesbutawarna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.ceritawarna.R

class TestMainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_main)

        val fragment_test = QuizTestFragment()
        supportFragmentManager.beginTransaction().replace(R.id.hostFragment, fragment_test).commit()

    }

    override fun passDataCom(scoreTest: String) {
        val bundle = Bundle()
        bundle.putString("msg", scoreTest)


        val transaction = this.supportFragmentManager.beginTransaction()
        val fragment_result = ResultFragment()
        fragment_result.arguments = bundle

        transaction.replace(R.id.hostFragment, fragment_result)
        transaction.commit()

    }
}