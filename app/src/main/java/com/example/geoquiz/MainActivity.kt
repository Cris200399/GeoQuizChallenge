package com.example.geoquiz


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var  questions: ArrayList<Question>
    var position = 0
    var points = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQuestion()
        setupViews()
    }

    private fun loadQuestion() {
        this.questions = ArrayList()
        this.questions.addAll(
            listOf(
                Question("¿Is Lima Chile's Capital?", false),
                Question("The Pacific Ocean is larger than the Atlantic Ocean?", true),
                Question("The source of the Nile River is in Egypt?", false),
                Question("The Amazon River is the longest river in the Americas?", true),
                Question("Lake Baikal is the world's oldest and deepest freshwater lake?", true),
                Question("Mount Kilimanjaro is the highest mountain in the world?", false),
                Question("The Sahara Desert is the largest desert in the world?", true),
                Question("The island of Tasmania is administrated by Australia?", true),
                Question("The island of Ibiza belongs to the Balearic Islands?", true)
            )
        )

    }



    private fun setupViews() {
        val btnYes: Button = findViewById<Button>(R.id.btnYes)
        val btnNo: Button = findViewById<Button>(R.id.btnNo)
        val btnNext: Button = findViewById<Button>(R.id.btnNext)
        val btnBack: Button = findViewById<Button>(R.id.btnBack)
        val tvPoints: TextView = findViewById<TextView>(R.id.tvPoints)
        tvPoints.text = "Points: " + this.points.toString()
        showSentence()

        btnYes.setOnClickListener {
            if(questions[this.position].answer) {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG).show()
                this.points += 1
                tvPoints.text = "Points: " + this.points.toString()
                this.nexSentence()
                this.showSentence()
            }
            else Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show()
        }


        btnNo.setOnClickListener {
            if(!questions[this.position].answer) {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG).show()
                this.points += 1
                tvPoints.text = "Points: " + this.points.toString()
                this.nexSentence()
                this.showSentence()
            }
            else Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show()
        }


        btnNext.setOnClickListener {
            //Mostrar siguiente pregunta
            this.nexSentence()
            showSentence()
        }
        btnBack.setOnClickListener {
            //Mostrar Anterior pregunta
            this.lastSentence()
            showSentence()
            Log.d("MainActivity", "Back button pressed")
        }

    }

    private fun nexSentence(){
        this.position = (this.position + 1) % this.questions.size
    }

    private fun lastSentence(){
        if (this.position == 0) {
            this.position = this.questions.size - 1
        }
        else{
            this.position = (this.position - 1) % this.questions.size
        }

    }

    private fun showSentence() {
        var tvSentence: TextView = findViewById<TextView>(R.id.tvQuestion)
        tvSentence.text = questions[this.position].sentence
    }
}