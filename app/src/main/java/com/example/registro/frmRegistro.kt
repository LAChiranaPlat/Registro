package com.example.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.registro.databinding.ActivityFrmRegistroBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class frmRegistro : AppCompatActivity() {

    lateinit var views:ActivityFrmRegistroBinding
    var nick:EditText?=null
    var name:EditText?=null
    var lname:EditText?=null
     var id:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views= ActivityFrmRegistroBinding.inflate(layoutInflater)

        setContentView(views.root)

        val db = Firebase.firestore

        nick=views.tilUser.editText
         name=views.tilName.editText
         lname=views.tilLname.editText
         id=views.tilNID.editText

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

            /*CONEXION FIREBASE: */
            // Create a new user with a first and last name
            val newUser = hashMapOf(
                "Nombres" to name!!.text.toString(),
                "Apellidos" to lname!!.text.toString(),
                "Nick" to nick!!.text.toString(),
                "ID" to id!!.text.toString()
            )

        // Add a new document with a generated ID
            db.collection("Usuarios")
                .add(newUser)
                .addOnSuccessListener { documentReference ->

                    _clear()
                    Toast.makeText(this,"Usuario Ingresado",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Log.e("result", "Error en Inserción de Datos: ", e)
                }


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

    fun _clear(){
        nick!!.text.clear()
        name!!.text.clear()
        lname!!.text.clear()
        id!!.text.clear()

        nick!!.requestFocus()
    }
}