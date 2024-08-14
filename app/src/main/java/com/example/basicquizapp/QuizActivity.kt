package com.example.basicquizapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var selectedTopic: String
    private lateinit var questionTextView: TextView
    private lateinit var answersRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        selectedTopic = intent.getStringExtra("topic") ?: ""
        questionTextView = findViewById(R.id.questionTextView)
        answersRadioGroup = findViewById(R.id.answersRadioGroup)

        loadQuestion(selectedTopic)

        val submitAnswerButton: Button = findViewById(R.id.submitAnswerButton)
        submitAnswerButton.setOnClickListener {
            val selectedId = answersRadioGroup.checkedRadioButtonId

            if (selectedId != -1) {
                val selectedRadioButton: RadioButton = findViewById(selectedId)
                val answer = selectedRadioButton.text.toString()

                val isCorrect = isCorrectAnswer(answer)
                if (isCorrect) {
                    showNotification("Quiz Completed", "You answered correctly!")
                } else {
                    showNotification("Quiz Completed", "Incorrect answer. Try again!")
                }
            } else {
                // Handle case when no answer is selected (optional)
                showNotification("Quiz Error", "Please select an answer!")
            }
        }

        // Create notification channel for Android 8.0 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("quiz_channel", "Quiz Notification", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun loadQuestion(topic: String) {
        when (topic) {
            "Math" -> {
                questionTextView.text = "What is 2 + 2?"
                (answersRadioGroup.getChildAt(0) as RadioButton).text = "3"
                (answersRadioGroup.getChildAt(1) as RadioButton).text = "4"
                (answersRadioGroup.getChildAt(2) as RadioButton).text = "5"
                (answersRadioGroup.getChildAt(3) as RadioButton).text = "6"
            }
            "Science" -> {
                questionTextView.text = "What planet is known as the Red Planet?"
                (answersRadioGroup.getChildAt(0) as RadioButton).text = "Earth"
                (answersRadioGroup.getChildAt(1) as RadioButton).text = "Mars"
                (answersRadioGroup.getChildAt(2) as RadioButton).text = "Jupiter"
                (answersRadioGroup.getChildAt(3) as RadioButton).text = "Venus"
            }
            "History" -> {
                questionTextView.text = "Who was the first President of the United States?"
                (answersRadioGroup.getChildAt(0) as RadioButton).text = "Abraham Lincoln"
                (answersRadioGroup.getChildAt(1) as RadioButton).text = "Thomas Jefferson"
                (answersRadioGroup.getChildAt(2) as RadioButton).text = "George Washington"
                (answersRadioGroup.getChildAt(3) as RadioButton).text = "John Adams"
            }
        }
    }

    private fun isCorrectAnswer(answer: String): Boolean {
        return when (selectedTopic) {
            "Math" -> answer == "4"
            "Science" -> answer == "Mars"
            "History" -> answer == "George Washington"
            else -> false
        }
    }

    private fun showNotification(title: String, text: String) {
        val builder = NotificationCompat.Builder(this, "quiz_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(1, builder.build())
    }
}
