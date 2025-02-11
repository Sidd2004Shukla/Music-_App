package com.example.musicapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.ActivityMain3Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var artists:ArrayList<DataX>
    private lateinit var album:ArrayList<DataX>
    private lateinit var popular:ArrayList<DataX>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         var value=intent.getIntExtra("yes",0)
        binding.tv9.text=intent.getStringExtra("name")
        val reterofitbuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)
        artists= ArrayList()
        album= ArrayList()
        popular= ArrayList()
        val retrofitData=reterofitbuilder.getdata("eminem")
        retrofitData.enqueue(object : Callback<data?> {
            override fun onResponse(p0: Call<data?>, p1: Response<data?>) {
                val datalist=p1.body()?.data!!
                for(i in datalist){
                    if(i.artist.name==intent.getStringExtra("name")){
                        artists.add(i)
                    }


                }
                for(j in datalist){
                    if(j.album.title==intent.getStringExtra("album")){
                        album.add(j)
                    }

                }
                for(k in datalist){
                    if(k.rank<800000)
                    {
                        popular.add(k)
                    }

                }
                binding.rv6.layoutManager= LinearLayoutManager(this@MainActivity3,LinearLayoutManager.VERTICAL,false)

                binding.rv6.adapter=MyAdapter4(this@MainActivity3,artists)
                if(value==1)
                {
                    binding.rv6.adapter=MyAdapter4(this@MainActivity3,artists)
                }
                else {
                    binding.rv6.adapter = MyAdapter4(this@MainActivity3, album)
                }



            }

            override fun onFailure(p0: Call<data?>, p1: Throwable) {
                Log.d("Main Activity","onFailure"+p1.message)
            }
        })



    }
}