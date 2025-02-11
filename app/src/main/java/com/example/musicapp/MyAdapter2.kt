package com.example.musicapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.AlbumEachBinding
import com.example.musicapp.databinding.CategoryEachBinding
import com.squareup.picasso.Picasso

class MyAdapter2(var context: Context,var datalist: ArrayList<DataX>) : RecyclerView.Adapter<MyAdapter2.ViewHolder>() {
    class ViewHolder(var binding: AlbumEachBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=AlbumEachBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tv1.text=datalist[position].album.title
        Picasso.get().load(datalist[position].album.cover_big).into(holder.binding.iv1)
        holder.itemView.setOnClickListener {
            var myintent= Intent(context,MainActivity3::class.java)
            myintent.putExtra("album",datalist[position].album.title)
            context.startActivity(myintent)

        }


    }

}
