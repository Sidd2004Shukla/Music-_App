package com.example.musicapp

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.CategoryEachBinding
import com.example.musicapp.databinding.PopularEachBinding
import com.squareup.picasso.Picasso

class MyAdapter3(var context: Context,var datalist: ArrayList<DataX>) : RecyclerView.Adapter<MyAdapter3.ViewHolder>() {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int = -1
    class ViewHolder(var binding: PopularEachBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=PopularEachBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.tv1.text=datalist[position].title
        Picasso.get().load(datalist[position].artist.picture_big).into(holder.binding.iv1)
        var value=false
        holder.itemView.setOnClickListener {
            if(value==false) {


                if (mediaPlayer != null) {

                    mediaPlayer?.pause()
                    mediaPlayer?.reset()
                }


                mediaPlayer = MediaPlayer.create(context, datalist[position].preview.toUri())


                mediaPlayer?.start()
                currentPlayingPosition = position
                value=true
            }
            else
            {
                mediaPlayer?.pause()
                mediaPlayer?.reset()
                value=false
            }



        }



    }

}
