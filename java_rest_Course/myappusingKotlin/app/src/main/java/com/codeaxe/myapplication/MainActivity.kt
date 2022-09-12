package com.codeaxe.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val buttonClick = findViewById<Button>(R.id.bbuttonimage)
        buttonClick.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            val toast = Toast.makeText(applicationContext,"Welcome",Toast.LENGTH_LONG)
            toast.setGravity(Gravity.LEFT,200 , 200)
            toast.show()
            startActivity(intent)
        }
    }
}