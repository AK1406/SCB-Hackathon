package com.scb.gamify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class QuizFragment : Fragment() {

    private var selectedCategory: String = ""
    private var learn_quiz: String = ""
    private lateinit var quizButton: Button
    private var questions = arrayOf("")
    private var answers = arrayOf("")
    private var options = arrayOf("")

    //html quiz
    private var questionsHtml = arrayOf(
        "Q.1. Who created HTML?",
        "Q.2. Where is the correct place to insert a CSS File?",
        "Q.3. HTML Full Form",
        "Q.4. What is the correct HTML element for inserting a line break?",
        "Q.5. Choose the correct HTML element for the largest heading",
        "Q.6. Which character is used to indicate an end tag?",
        "Q.7. Choose the correct HTML element to define important text",
        "Q.8. Choose the correct HTML element to define emphasized text",
        "Q.9. How can you make a numbered list?",
        "Q.10. How can you make a bulleted list?"
    )

    private var answerHtml = arrayOf(
        "Sir Tim Berners-Lee",
        "The <head> section",
        "Hyper Text Markup Language",
        "<br>",
        "<h1>",
        "/",
        "<strong>",
        "<em>",
        "<ol>", "ul"
    )
    private var optionsHtml = arrayOf(
        "Sir Tim Berners-Lee",
        "Alan Turing",
        "Grace Hopper",
        "Dennis Ritchie",
        "The <body> section",
        "The <head> section",
        "All is correct",
        "None of these",
        "Hyper Text Markup Language",
        "Hyper Type Markup Language",
        "Hyper Text Marketing Language",
        "None",
        "<break>",
        "<bl>",
        "<br>",
        "<hr>",
        "Head",
        "Heading",
        "<h6>",
        "<h1>",
        "/",
        "<",
        "^",
        "*",
        "<strong>",
        "<i>",
        "<important>", "<b>",
        "<em>",
        "<italic>",
        "<i>",
        "<b>",
        "<ol>",
        "<list>",
        "dl",
        "ul",
        "<dl>",
        "<ul>",
        "<ol",
        "<bullet>"
    )

    //css quiz

    private var questionsCss = arrayOf(
        "Q.1. Full Form of CSS",
        "Q.2. Which HTML tag is used to define an internal style sheet?",
        "Q.3. Which HTML attribute is used to define inline styles?",
        "Q.4. Which property is used to change the background color?",
        "Q.5. Which CSS property is used to change the text color of an element?",
        "Q.6. Which CSS property controls the text size?",
        "Q.7. Which property is used to change the font of an element?",
        "Q.8. Which property is used to change the left margin of an element?",
        "Q.9. How do you select an element with id 'demo'?",
        "Q.10. How do you select elements with class name 'test'?"

    )

    private var optionsCss = arrayOf(
        "Cascading Style Sheets", "Colorful Style Sheets", "Computer Style Sheets",
        "Creative Style Sheet",
        "<css>", "style", "<color>", "<script>",
        "style", "font", "styles", "class",
        "backgroundColor", "background-color", "color", "bgcolor",
        "text-color", "textColor", "color", "Tcolor",
        "text-style", "font-size", "font-style", "text-size",
        "font-family", "font-style", "font-weight", "None",
        "marginLeft", "margin-left", "padding", "indent",
        "#demo", "demo", ".demo", "*demo",
        "#test", ".test", "*test", "&test"

    )
    private var answerCss = arrayOf(
        "Cascading Style Sheets", "<style>", "style", "background-color", "text-color", "font-size",
        "font-family", "margin-left", "#demo", ".test"

    )


    companion object {
        fun newInstance(): Fragment {
            return QuizFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        quizButton = view.findViewById(R.id.playQuiz_home_btn)
        val bundle = this.arguments
        selectedCategory = bundle!!.getString("CATEGORY_ID")!!
        learn_quiz = bundle!!.getString("LEARNORQUIZ")!!



        if (selectedCategory == "html") {
            questions = questionsHtml
            answers = answerHtml
            options = optionsHtml
        } else if (selectedCategory == "css") {
            questions = questionsCss
            answers = answerCss
            options = optionsCss
        }

        val b = Bundle()
        b.putStringArray("QES", questions)
        b.putStringArray("ANS", answers)
        b.putStringArray("OPS", options)

        quizButton.setOnClickListener {
            val intent = Intent(context, PlayActivity::class.java)
            intent.putExtra("BUNDLE", b)
            startActivity(intent)
        }
        return view
    }
}