package com.tahufikprojects.ceritawarna.tesbutawarna

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tahufikprojects.ceritawarna.CobaActivity
import com.tahufikprojects.ceritawarna.R
import com.tahufikprojects.ceritawarna.autentikasiuser.DaftarActivity
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity
import com.tahufikprojects.ceritawarna.databinding.FragmentResultBinding
import com.tahufikprojects.ceritawarna.databinding.FragmentTesRuleBinding
import com.tahufikprojects.ceritawarna.forum.ForumMainActivity
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.fragment_result.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mContext: Context? = null

    var displayScore: String? = ""
    var skor: Int = 0
    var komentar: String = ""

    lateinit var binding: FragmentResultBinding

    protected lateinit var baseActivity: TestMainActivity
    protected lateinit var contextFragment: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TestMainActivity) {
            this.baseActivity = context
        }
        this.contextFragment = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_result, container, false)

        displayScore = arguments?.getString("msg")
        view.hasiltes.text = displayScore

        val btn_jurusan = view.findViewById<View>(R.id.btn_jurusan_dari_tes) as Button
        val btn_forum = view.findViewById<View>(R.id.btn_diskusi_dari_tes) as Button
        val btn_beranda = view.findViewById<View>(R.id.btn_beranda_dari_tes) as Button

        btn_jurusan.setOnClickListener {
            val intent = Intent(container?.getContext(), CobaCariMainActivity::class.java)
            startActivity(intent)
        }

        btn_forum.setOnClickListener {
            val intent = Intent(container?.getContext(), ForumMainActivity::class.java)
            startActivity(intent)
        }

        btn_beranda.setOnClickListener {
            val intent = Intent(container?.getContext(), CobaActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}