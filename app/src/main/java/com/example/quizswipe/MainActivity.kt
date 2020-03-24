package com.example.quizswipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quiz = arrayListOf<Question>()
    private val quizAdapter = QuizAdapter(quiz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun addQuestions() {
        quiz.add(Question( "a val and var are the same", false))
        quiz.add(Question( "Mobile Applications Development grants 12 ECTS", true))
        quiz.add(Question( "A Unit in Kotlin corresponds to a void in Java", true))
        quiz.add(Question( "In Kotlin 'whe' replaces the 'switch' operator in Java.", true))
    }

    private fun initViews() {
        rvQuiz.layoutManager = LinearLayoutManager(this@MainActivity,
            RecyclerView.VERTICAL, false)

        rvQuiz.adapter = quizAdapter

        // item decoration
        rvQuiz.addItemDecoration(DividerItemDecoration(this@MainActivity,
            DividerItemDecoration.VERTICAL))

        // itemtouchhelper added to recycleview
        createItemTouchHelper().attachToRecyclerView(rvQuiz)

        addQuestions()
    }

    fun makeToast(text : String) {
        Toast.makeText(this@MainActivity, "Answer is $text", Toast.LENGTH_SHORT).show()
    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // disable movement of items
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // get position of item
                val position = viewHolder.adapterPosition
                val answer = quiz[position].answer

                // correct answer swipe left
                if (direction == ItemTouchHelper.LEFT && !answer) {
                    // remove item
                    quiz.removeAt(position)
                } else if (direction == ItemTouchHelper.RIGHT && answer) {
                    quiz.removeAt(position)
                } else {
                    Toast.makeText(this@MainActivity,
                        "Answer is not correct", Toast.LENGTH_SHORT).show()
                }

                // notify adapter that something has changed
                quizAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }
}
