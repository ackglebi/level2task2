package com.example.quizswipe

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_view.view.*

class QuizAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder> () {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind (question : Question) {
            itemView.itemQuestion.text = question.question

            itemView.setOnClickListener {

                val answer = question.answer

                Toast.makeText(it.context, "Answer is $answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

}