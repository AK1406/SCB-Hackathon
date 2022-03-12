package com.scb.gamify

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.scb.gamify.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {
    private lateinit var activityResultBinding: ActivityResultBinding
    var totalScore = 0
    var correct = 0
    var Wrong = 0
    var skip = 0
    var isKey = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(activityResultBinding.root)
        totalScore = intent.extras!!.getInt("correct")
        Wrong = intent.extras!!.getInt("wrong")
        skip = intent.extras!!.getInt("skip")
        initializeViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeViews() {
        activityResultBinding.apply {
            Score.text = "Score: $totalScore"
            right.text = "Correct: $totalScore"
            wrong.text = "Wrong: $Wrong"
            Skip.text = "Skip: $skip"
            if (totalScore >= 6) {
                activityResultBinding.WinImg.setImageResource(R.drawable.win)
                Toast.makeText(this@ResultActivity, "Great!", Toast.LENGTH_SHORT).show()
            } else {
                WinImg.setImageResource(R.drawable.improve)
                Toast.makeText(this@ResultActivity, "Need Improvement!", Toast.LENGTH_SHORT).show()
            }
            PlayAgain.setOnClickListener {
                finish()
            }
           share.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Quiz score")
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Score : $totalScore/10")
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }

        }
    }
}