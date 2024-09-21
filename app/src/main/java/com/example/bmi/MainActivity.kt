package com.example.bmi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val submitButton = findViewById<Button>(R.id.submit_button)
        val inputHeight = findViewById<EditText>(R.id.input_height)
        val inputWidth = findViewById<EditText>(R.id.input_width)
        val summary = findViewById<TextView>(R.id.summary)
        val image = findViewById<ImageView>(R.id.the_image)

        submitButton.setOnClickListener{
            val height = inputHeight.text.toString().toFloatOrNull() ?: 0f
            val width = inputWidth.text.toString().toFloatOrNull() ?: 0f

            if(height > 0f && width > 0f){
                val bmi = width / (height * height)
                val formattedBMI = Math.round(bmi * 100) / 100.0

                if(formattedBMI < 18.5){
                    summary.text = "Masz niedowagę (${formattedBMI})"
                    image.setImageResource(R.drawable.underweight)
                }
                else if(formattedBMI in 18.5..24.9){
                    summary.text = "Masz prawidłową wagę (${formattedBMI})"
                    image.setImageResource(R.drawable.normal)
                }
                else if(formattedBMI in 25.0..29.9){
                    summary.text = "Masz nadwagę (${formattedBMI})"
                    image.setImageResource(R.drawable.overweight)
                }
                else{
                    summary.text = "Masz otyłosć (${formattedBMI})"
                    image.setImageResource(R.drawable.obesity)
                }
            }
            else{
                summary.text="Dane są nieprawidłowe. Spróbuj ponownie."
            }
        }
    }
}