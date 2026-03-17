package com.example.studentregistrationapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentId = findViewById<EditText>(R.id.studentId)
        val name = findViewById<EditText>(R.id.fullName)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val age = findViewById<EditText>(R.id.age)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val favGame = arrayOf(findViewById<CheckBox>(R.id.fbFavoriteGame),
                                findViewById<CheckBox>(R.id.cricFavoriteGame),
                                findViewById<CheckBox>(R.id.bdFavoriteGame),
                                findViewById<CheckBox>(R.id.badFavoriteGame))
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)



        val items = resources.getStringArray(R.array.country)
        val spinnerReg = findViewById<Spinner>(R.id.dropDown)
        val adapterSpinner: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items)

        spinnerReg.adapter = adapterSpinner




        val editTextDate = findViewById<EditText>(R.id.btnDate)

        editTextDate.setOnClickListener {

            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    editTextDate.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                },
                year,
                month,
                day
            )

            datePickerDialog.show()

        }

        btnSubmit.setOnClickListener {
            when {
                studentId.text.toString().trim().isEmpty() -> {
                    studentId.error = "fill Student Id field"
                    studentId.requestFocus()
                    return@setOnClickListener
                }

                name.text.toString().trim().isEmpty() -> {
                    name.error = "Name is required"
                    name.requestFocus()
                    return@setOnClickListener
                }

                email.text.toString().trim().isEmpty() -> {
                    email.error = "email is required"
                    email.requestFocus()
                    return@setOnClickListener
                }

                password.text.toString().trim().isEmpty() -> {
                    password.error = "password is required"
                    password.requestFocus()
                    return@setOnClickListener
                }

                age.text.toString().trim().isEmpty() -> {
                    age.error = "age is required"
                    age.requestFocus()
                    return@setOnClickListener
                }



            }

        }

    }
}