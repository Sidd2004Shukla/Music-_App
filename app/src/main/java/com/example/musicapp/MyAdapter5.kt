package com.example.musicapp

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.EachListBinding
import com.squareup.picasso.Picasso

class MyAdapter5(var context: Context, var  datalist: ArrayList<DataX>) : RecyclerView.Adapter<MyAdapter5.ViewHolder>() {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int = -1

     inner class ViewHolder(var binding: EachListBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=EachListBinding.inflate(android.view.LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterlist: ArrayList<DataX>) {
        datalist = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.textView4.text = datalist[position].title
        holder.binding.textView7.text = datalist[position].artist.name
        Picasso.get().load(datalist[position].artist.picture_big).into(holder.binding.iv8)
        var value = false
        holder.binding.imageView3.setOnClickListener {
            if (value == false) {


                if (mediaPlayer != null) {
                    holder.binding.imageView3.setImageResource(R.drawable.baseline_play_arrow_24)
                    mediaPlayer?.pause()
                    mediaPlayer?.reset()
                }


                mediaPlayer = MediaPlayer.create(context, datalist[position].preview.toUri())

                holder.binding.imageView3.setImageResource(R.drawable.baseline_pause_24)
                mediaPlayer?.start()
                currentPlayingPosition = position
                value = true
            } else {
                mediaPlayer?.pause()
                holder.binding.imageView3.setImageResource(R.drawable.baseline_play_arrow_24)
                mediaPlayer?.reset()
                value = false
            }
        }
    }

}
