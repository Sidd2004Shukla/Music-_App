package com.example.musicapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.CategoryEachBinding
import com.squareup.picasso.Picasso

class MyAdapter(var context: Context,var datalist: ArrayList<DataX>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(var binding: CategoryEachBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=CategoryEachBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tv1.text=datalist[position].artist.name
        Picasso.get().load(datalist[position].artist.picture_big).into(holder.binding.iv1)
        holder.itemView.setOnClickListener {
            var myintent=Intent(context,MainActivity3::class.java)
            myintent.putExtra("name",datalist[position].artist.name)
            myintent.putExtra ("yes",1)
            context.startActivity(myintent)


        }


    }

}
