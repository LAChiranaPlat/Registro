package com.example.registro

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registro.databinding.ItemsTemplateBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class myAdapter(var usuarios:ArrayList<Users>):RecyclerView.Adapter<myAdapter.ViewHolder>() {

    lateinit var ctx:Context
    private var lstOriginal:ArrayList<Users> = ArrayList()

    init {
        lstOriginal.addAll(usuarios)
    }

    class ViewHolder(view:ItemsTemplateBinding):RecyclerView.ViewHolder(view.root) {

        val nombres:TextView
        val nick:TextView
        val edit:ImageView
        val del:ImageView

        init {
            nombres=view.txtdatos
            nick=view.txtNick
            edit=view.imgEdit
            del=view.imgDelete
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter.ViewHolder {
        ctx=parent.context

        val views=ItemsTemplateBinding.inflate(LayoutInflater.from(ctx),parent,false)
        return ViewHolder(views)

    }

    override fun onBindViewHolder(holder: myAdapter.ViewHolder, position: Int) {
        val item=usuarios.get(position)

        holder.apply {
            nick.text=item.nick
            nombres.text=item.nombres

            edit.setOnClickListener {
                ctx.startActivity(Intent(ctx,EditUser::class.java))
            }

            del.setOnClickListener {

                MaterialAlertDialogBuilder(ctx)
                    .setTitle("Eliminando Usuarios")
                    .setMessage("Esta seguro de Eliminar a: ${nick.text.toString()}")
                    .setPositiveButton("OK"){d,w->
                        usuarios.removeAt(position)
                        notifyDataSetChanged()
                    }.show()


            }
        }

    }

    override fun getItemCount()=usuarios.size

    fun buscar(data:String){

        if(data.isEmpty()){
            usuarios.clear()
            usuarios.addAll(lstOriginal)
        }else{
            val filtro:ArrayList<Users> = ArrayList()

            for(item in usuarios){
                if(item.nick.lowercase().contains(data.lowercase())){
                    filtro.add(item)
                }
            }

            usuarios.clear()
            usuarios.addAll(filtro)
        }

        notifyDataSetChanged()

    }

}