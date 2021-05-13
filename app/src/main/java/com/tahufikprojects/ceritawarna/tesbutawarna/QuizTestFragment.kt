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
    val maxQuestions = 2
    lateinit var answers:ArrayList<String>
    lateinit var selectedAnswer: String
    var score = 0
    lateinit var name: String

    var questions = arrayListOf<Questions>(
        Questions("1 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5"), "@drawable/soal1"),
        Questions("2 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5"), "@drawable/soal2")
//        Questions("3 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
//        Questions("4 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
//        Questions("5 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5")),
//        Questions("6 satu di tambah satu sama dengan ?", arrayListOf("2", "3", "4", "5"))
    )


    private fun setQuestions()
    {
        currentQuestions = questions[questionIndex]
//        bgsoal.setImageResource(R.drawable.soal2)
        name = currentQuestions.g
        Toast.makeText(activity, name, Toast.LENGTH_SHORT).show()
        answers = ArrayList(currentQuestions.theAnswer)
        answers.shuffle()
        Log.d("ANSWER", answers[0] + " " + answers[1] + " " + answers[2] + " " + answers[3])
        Log.d("ANSWEREAL", currentQuestions.theAnswer[0])
    }

    private fun randomQuestions()
    {
        questions.shuffle();
        setQuestions()
    }

    private fun checkAnswers(answers:String)
    {
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
//        bgsoal.setImageResource(imageResource)

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