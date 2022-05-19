package com.example.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.registro.databinding.ActivityLstUsuariosBinding
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LstUsuarios : AppCompatActivity() {

    lateinit var layout:ActivityLstUsuariosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout= ActivityLstUsuariosBinding.inflate(layoutInflater)

        setContentView(layout.root)

/**/
        val db = Firebase.firestore
/**/
        val recycler=layout.lista
        val search=layout.txtSearch

        recycler.layoutManager=LinearLayoutManager(this)

/**/
        val listaUser:ArrayList<Users> = ArrayList()
        var adapter:myAdapter?=null
/**/

        /**/
        db.collection("Usuarios")
            .whereNotEqualTo("Nombres","")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    Log.i("result", "${document.data.get("Nombres")}, ${document.data.get("Apellidos")}: ${document.data.get("Nick")} : [ID] ${ document.id }")
                    listaUser.add(Users(document.data.get("Nick").toString(),"${document.data.get("Nombres")}, ${document.data.get("Apellidos")}",document.id.toString()))
                }

                adapter=myAdapter(listaUser)
                recycler.adapter=adapter
            }
            .addOnFailureListener { exception ->
                Log.w("result", "Error getting documents.", exception)
            }

        /**/

        search.setOnQueryTextListener( object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.i("result",p0.toString())
                adapter?.buscar(p0.toString())

                return false
            }
        })



    }
}