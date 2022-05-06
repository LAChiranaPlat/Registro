package com.example.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.registro.databinding.ActivityDetailsBinding

class details : AppCompatActivity() {

    lateinit var contentViews:ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentViews= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(contentViews.root)

        contentViews.txtNombres.text=intent.getStringExtra("name").toString()
        contentViews.txtApellidos.text=intent.getStringExtra("lname").toString()
        contentViews.txtIdentificacion.text=intent.getStringExtra("idUser").toString()

        contentViews.txtTitle.append(intent.getStringExtra("nick").toString())
    }
}