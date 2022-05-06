package com.example.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var layout:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout= ActivityMainBinding.inflate(layoutInflater)
        setContentView(layout.root)

        layout.rec.setOnClickListener {
            startActivity(Intent(this, frmRegistro::class.java))
        }

    }
}