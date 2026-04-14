package com.example.loginprofileapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    lateinit var navigationBar: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val userName = findViewById<EditText>(R.id.userName)
        val passWords = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.loginButton)
        navigationBar = findViewById<RelativeLayout>(R.id.navigationBar)
        val loggingForm = findViewById<RelativeLayout>(R.id.innerLayout)
        val name = findViewById<TextView>(R.id.name)
        val logout = findViewById<Button>(R.id.logout)
        val welcome = findViewById<TextView>(R.id.welcome)
        val userProfile = findViewById<RelativeLayout>(R.id.userProfile)

        button.setOnClickListener {

            if(userName.text.trim().toString() == "Ariful" && passWords.text.trim().toString() == "1234") {
                navigationBar.visibility = View.GONE
                loggingForm.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                Thread {
                    loading()

                    runOnUiThread {
                        navigationBar.visibility = View.VISIBLE
                        welcome.text = "Hi ${userName.text}"
                        userProfile.visibility = View.VISIBLE
                    }
                }.start()

            }
            else
                Toast.makeText(this, "incorrect user name or password", Toast.LENGTH_SHORT).show()
        }

        logout.setOnClickListener {
            navigationBar.visibility = View.VISIBLE
            loggingForm.visibility = View.VISIBLE
            userProfile.visibility = View.GONE
            welcome.text = "Welcome Back"
            userName.text.clear()
            passWords.text.clear()

        }


    }

    fun loading (){

            for (i in 0..100 step 25) {
                Thread.sleep(500)
                progressBar.progress = i
            }


        runOnUiThread{
            runOnUiThread {
                Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show()

            }

        progressBar.visibility = View.GONE

        }
    }


}