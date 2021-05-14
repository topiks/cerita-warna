package com.tahufikprojects.ceritawarna.tesbutawarna

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.tahufikprojects.ceritawarna.R
import com.tahufikprojects.ceritawarna.databinding.FragmentQuizTestBinding
import kotlinx.android.synthetic.main.fragment_quiz_test.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizTestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizTestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentQuizTestBinding
    lateinit var currentQuestions: Questions
    private var questionIndex = 0
    val maxQuestions = 24
    lateinit var answers:ArrayList<String>
    lateinit var selectedAnswer: String
    var score = 0
    lateinit var name: String
    private lateinit var communicator: Communicator

    var questions = arrayListOf<Questions>(
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("12", "1", "2", "15")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("8", "5", "3", "2")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("29", "70", "24", "21")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("5", "3", "2", "6")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("3", "6", "5", "15")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("15", "11", "1", "17")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("74", "73", "51", "21")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("6", "5", "3", "8")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("45", "42", "73", "74")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("5", "6", "7", "13")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("7", "11", "1", "17")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("16", "11", "15", "51")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("73", "13", "70", "23")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("Tidak ada angka", "5", "4", "15")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("Tidak ada angka", "40", "4", "5")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("26", "6", "2", "28")),
        Questions("Angka berapakah yang tertera pada gambar di bawah?", arrayListOf("42", "4", "43", "2")),
        Questions("Gambar apa yang kamu lihat di bawah?", arrayListOf("Garis alur merah dan ungu", "Garis alur merah", "Garis alur ungu", "Tidak ada angka")),
        Questions("Gambar apa yang kamu lihat di bawah?", arrayListOf("Ada semacam bercak/garis, namun putus & tidak jelas", "Ada sebuah garis yang bisa diikuti alurnya", "Ada dua buah garis yang bisa diikuti alurnya", "Ada sebuah garis yang bisa diikuti alurnya, dan sebuah garis yang alurnya terputus-putus")),
        Questions("Gambar apa yang kamu lihat di bawah?", arrayListOf("Garis alur merah dan ungu", "Garis alur hijau kebiruan yang bisa diikuti alurnya", "Tidak ada apa-apa", "Garis alur hijau kebiruan yang alurnya terputus-putus")),
        Questions("Gambar apa yang kamu lihat di bawah?", arrayListOf("Garis alur oranye", "Tidak ada apa-apa", "Garis alur oranye terputus-putus", "Garis alur merah dan ungu")),
        Questions("Gambar apa yang kamu lihat di bawah?", arrayListOf("Garis alur hijau kebiruan yang disambung garis alur hijau kekuningan", "Garis alur hijau kebiruan yang disambung garis alur ungu", "Tidak ada garis/alur yang jelas", "Garis alur merah dan ungu")),
        Questions("Gambar apa yang kamu lihat di bawah?", arrayListOf("Garis alur ungu disambung garis alur oranye", "Garis alur ungu disambung garis alur hijau kebiruan", "Tidak ada garis/ alur yang jelas", "Garis alur merah dan ungu")),
        Questions("Gambar apa yang kamu lihat di bawah?", arrayListOf("Ada sebuah garis yang sangat jelas bisa diikuti alurnya", "Ada sebuah garis alur namun terputus di tengah jalan", "Tidak ada garis/ alur yang jelas", "Garis alur merah dan ungu"))
    )


    private fun setQuestions()
    {
        currentQuestions = questions[questionIndex]
//        bgsoal.setImageResource(R.drawable.soal2)

        answers = ArrayList(currentQuestions.theAnswer)
        answers.shuffle()
        Log.d("ANSWER", answers[0] + " " + answers[1] + " " + answers[2] + " " + answers[3])
        Log.d("ANSWEREAL", currentQuestions.theAnswer[0])
    }

    private fun randomQuestions()
    {
//        questions.shuffle();
        setQuestions()
    }

    private fun checkAnswers(answers:String)
    {
        if(questionIndex.equals(0))
            bgsoal.setImageResource(R.drawable.soal2)
        else if(questionIndex.equals(1))
            bgsoal.setImageResource(R.drawable.soal3)
        else if(questionIndex.equals(2))
            bgsoal.setImageResource(R.drawable.soal4)
        else if(questionIndex.equals(3))
            bgsoal.setImageResource(R.drawable.soal5)
        else if(questionIndex.equals(4))
            bgsoal.setImageResource(R.drawable.soal6)
        else if(questionIndex.equals(5))
            bgsoal.setImageResource(R.drawable.soal7)
        else if(questionIndex.equals(6))
            bgsoal.setImageResource(R.drawable.soal8)
        else if(questionIndex.equals(7))
            bgsoal.setImageResource(R.drawable.soal9)
        else if(questionIndex.equals(8))
            bgsoal.setImageResource(R.drawable.soal10)
        else if(questionIndex.equals(9))
            bgsoal.setImageResource(R.drawable.soal11)
        else if(questionIndex.equals(10))
            bgsoal.setImageResource(R.drawable.soal12)
        else if(questionIndex.equals(11))
            bgsoal.setImageResource(R.drawable.soal13)
        else if(questionIndex.equals(12))
            bgsoal.setImageResource(R.drawable.soal14)
        else if(questionIndex.equals(13))
            bgsoal.setImageResource(R.drawable.soal15)
        else if(questionIndex.equals(14))
            bgsoal.setImageResource(R.drawable.soal16)
        else if(questionIndex.equals(15))
            bgsoal.setImageResource(R.drawable.soal17)
        else if(questionIndex.equals(16))
            bgsoal.setImageResource(R.drawable.soal18)
        else if(questionIndex.equals(17))
            bgsoal.setImageResource(R.drawable.soal19)
        else if(questionIndex.equals(18))
            bgsoal.setImageResource(R.drawable.soal20)
        else if(questionIndex.equals(19))
            bgsoal.setImageResource(R.drawable.soal21)
        else if(questionIndex.equals(20))
            bgsoal.setImageResource(R.drawable.soal22)
        else if(questionIndex.equals(21))
            bgsoal.setImageResource(R.drawable.soal23)
        else if(questionIndex.equals(22))
            bgsoal.setImageResource(R.drawable.soal24)


        if(answers.equals(currentQuestions.theAnswer[0]))
        {
            score = score + 1
        }
        else
        {

        }

        questionIndex++

        if(questionIndex < maxQuestions)
        {
            setQuestions()
            binding.invalidateAll()
        }
        else
        {
//            Toast.makeText(activity, score.toString(), Toast.LENGTH_SHORT).show()
//            finishAffinity()
//            NavHostFragment.findNavController(this).navigate(R.id.resultFragment)
            communicator = activity as Communicator
            var komentar: String
            if(score <= 9)
                komentar = "Wah, sayang sekali sepertinya kamu harus memeriksakan mata mu ke dokter"
            else if(score >= 10 && score <= 12)
                komentar = "Hasil masih tidak dapat dipastikan antara buta warna  parsial atau tidak"
            else
                komentar = "Dari hasil tes, kemungkinan tidak ada yang salah pada kemampuan mu dalam membedakan warna"

            communicator.passDataCom(komentar)

        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        name = currentQuestions.g
//        val imageResource:Int
//        imageResource =
//            resources.getIdentifier(name, null, activity!!.packageName)
//
//        Toast.makeText(activity, questionIndex.toString(), Toast.LENGTH_SHORT).show()

        bgsoal.setImageResource(R.drawable.soal1)


        btn_tes_1.setOnClickListener {
            checkAnswers(btn_tes_1.text.toString())
        }
        btn_tes_2.setOnClickListener {
            checkAnswers(btn_tes_2.text.toString())
        }
        btn_tes_3.setOnClickListener {
            checkAnswers(btn_tes_3.text.toString())
        }
        btn_tes_4.setOnClickListener {
            checkAnswers(btn_tes_4.text.toString())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_test, container, false)
        randomQuestions()
        binding.quiz = this
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizTestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizTestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}