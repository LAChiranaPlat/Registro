package com.example.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.registro.databinding.ActivityFrmRegistroBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class frmRegistro : AppCompatActivity() {

    lateinit var views:ActivityFrmRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views= ActivityFrmRegistroBinding.inflate(layoutInflater)

        setContentView(views.root)

        val nick=views.tilUser.editText
        val name=views.tilName.editText
        val lname=views.tilLname.editText
        val id=views.tilNID.editText

        views.btnCancelar.setOnClickListener {
            finish()
        }

        views.btnGuardar.setOnClickListener {

            if(vacios(nick!!,name!!,lname!!,id!!))//true/false
            {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Error de Ingreso de Datos")
                    .setMessage("Debe llenar todos los campos")
                    .setPositiveButton("OK"){d,w->
                        Log.i("result","El usuario acepto el error y lo va a corregir")
                    }.show()

                return@setOnClickListener
            }

            startActivity(
                Intent(this,details::class.java).apply {
                    putExtra("nick",nick.text.toString())
                    putExtra("name",name.text.toString())
                    putExtra("lname",lname.text.toString())
                    putExtra("idUser",id.text.toString())

                }
            )


        }

    }

    fun x(){

    }

    fun vacios(vararg campos:EditText): Boolean{

        for (campo in campos)
        {
            if(campo.text.isEmpty()){
                campo.requestFocus()
                return true
            }
        }

        return false

    }
}