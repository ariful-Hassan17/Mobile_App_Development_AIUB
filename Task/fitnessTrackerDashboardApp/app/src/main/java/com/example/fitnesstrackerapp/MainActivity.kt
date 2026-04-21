package com.example.fitnesstrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var stepCount = 0
    private var waterAmount = 0
    private var goal = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val steps = findViewById<TextView>(R.id.steps)
        val water = findViewById<TextView>(R.id.water)
        val calories = findViewById<TextView>(R.id.calories)
        val workout = findViewById<TextView>(R.id.workout)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val percentage = findViewById<TextView>(R.id.percentage)
        val updateBtn = findViewById<Button>(R.id.updateBtn)

        updateBtn.setOnClickListener {
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.setPadding(50, 40, 50, 10)

            val inputSteps = EditText(this)
            inputSteps.hint = "Enter Steps"

            val inputWaterAmount = EditText(this)
            inputWaterAmount.hint = "Enter Water Amount in Liter"

            layout.addView(inputSteps)
            layout.addView(inputWaterAmount)

            AlertDialog.Builder(this).setTitle("Update Fitness Data").setView(layout).setPositiveButton("Update") { _, _ ->
                val newSteps = inputSteps.text.toString()
                val newWaterAmount = inputWaterAmount.text.toString()

                if (newSteps.isNotEmpty() && newWaterAmount.isNotEmpty()) {
                    stepCount = newSteps.toInt()
                    steps.text = stepCount.toString()

                    waterAmount = newWaterAmount.toInt()
                    water.text = "$waterAmount L"
                } else {
                    Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
                }

                val workoutType = when {
                    stepCount > 20000 -> "Heavy Workout"
                    stepCount > 10000 -> "Moderate Workout"
                    stepCount > 0 -> "Light Workout"
                    else -> "None"
                }
                workout.text = workoutType

                val caloriesBurned = stepCount * 0.04
                calories.text = caloriesBurned.toInt().toString()

                var progress = (stepCount * 100) / goal
                if(progress > 100) {
                    progress = 100
                }

                progressBar.progress = progress
                percentage.text = "$progress"

                if(progress == 100) {
                    Toast.makeText(this, "Congratulations! You have reached your goal!", Toast.LENGTH_SHORT).show()
                }
            }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}