package com.tahufikprojects.ceritawarna.tesbutawarna

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

    var questions = arrayListOf<Questions>(
        Questions("1 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("2 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("3 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("4 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("5 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("6 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("7 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("8 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("9 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("10 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("11 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("12 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("13 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("14 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("15 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("16 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("17 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("18 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("19 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("20 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("21 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("22 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("23 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
        Questions("24 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5"))


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
            Toast.makeText(activity, score.toString(), Toast.LENGTH_SHORT).show()
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