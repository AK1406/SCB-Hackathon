package com.scb.gamify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class CourseFragment : Fragment() {

    private var selectedCategory: String = ""
    private lateinit var learnCourse: CardView
    private lateinit var quiz: CardView
    private lateinit var studyMaterail: CardView
    private var learnOrQuiz: String = ""

    companion object {
        fun newInstance(): Fragment {
            return CourseFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)
        learnCourse = view.findViewById(R.id.learn)
        quiz = view.findViewById(R.id.quiz)
        studyMaterail = view.findViewById(R.id.material)


        val myContext = activity as FragmentActivity
        //retrieve category id
        val bundle = this.arguments
        selectedCategory = bundle!!.getString("CATEGORY_ID")!!

        learnCourse.setOnClickListener {
            learnOrQuiz = "learn"

            val url = if (selectedCategory == "html") {
                "https://www.youtube.com/watch?v=tb63cY7yjWI"
            } else {
                "https://www.youtube.com/watch?v=1PnVor36_40"
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        studyMaterail.setOnClickListener {
            val url = if (selectedCategory == "html") {
                "https://www.w3schools.com/html/"
            } else {
                "https://www.w3schools.com/css/"
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
        quiz.setOnClickListener {
            learnOrQuiz = "quiz"
            val fragment = QuizFragment.newInstance()
            val bundle2 = Bundle()
            bundle2.putString("LEARNORQUIZ", learnOrQuiz)
            bundle2.putString("CATEGORY_ID", selectedCategory)
            fragment.arguments = bundle2
            val fragmentManager: FragmentManager = myContext.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.courseLayout, fragment)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }

        return view
    }

}