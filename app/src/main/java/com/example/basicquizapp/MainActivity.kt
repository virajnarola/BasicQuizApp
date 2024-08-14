package com.example.basicquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topicListView: ListView = findViewById(R.id.topicListView)
        val topics = arrayOf("Math", "Science", "History")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topics)
        topicListView.adapter = adapter

        topicListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedTopic = topics[position]
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("topic", selectedTopic)
            startActivity(intent)
        }
    }
}
