package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var artistlist: ArrayList<String>
    private lateinit var albumlist: ArrayList<String>
    private lateinit var artist:ArrayList<DataX>
    private lateinit var album:ArrayList<DataX>
    private lateinit var popular:ArrayList<DataX>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.editTextText3.requestFocus()
        val reterofitbuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)
        artistlist= ArrayList()
        artist= ArrayList()
        albumlist= ArrayList()
        album= ArrayList()
        popular= ArrayList()
        val retrofitData=reterofitbuilder.getdata("eminem")
        retrofitData.enqueue(object : Callback<data?> {
            override fun onResponse(p0: Call<data?>, p1: Response<data?>) {
                val datalist=p1.body()?.data!!
                for(i in datalist){
                    if(artistlist.contains(i.artist.name)){
                        continue
                    }
                    else
                    {
                        artistlist.add(i.artist.name)
                        artist.add(i)


                    }

                }
                for(j in datalist){
                    if(albumlist.contains(j.album.title)){
                        continue
                    }
                    else
                    {
                        albumlist.add(j.album.title)
                        album.add(j)


                    }

                }
                for(k in datalist){
                    if(k.rank<800000)
                    {
                        popular.add(k)
                    }

                }
                binding.rv.layoutManager= LinearLayoutManager(this@MainActivity2,LinearLayoutManager.HORIZONTAL,false)
                binding.rv.adapter=MyAdapter(this@MainActivity2,artist)
                binding.rv2.layoutManager= LinearLayoutManager(this@MainActivity2,LinearLayoutManager.HORIZONTAL,false)
                binding.rv2.adapter=MyAdapter2(this@MainActivity2,album)
                binding.rv3.layoutManager= LinearLayoutManager(this@MainActivity2,LinearLayoutManager.HORIZONTAL,false)
                binding.rv3.adapter=MyAdapter3(this@MainActivity2,popular)

            }

            override fun onFailure(p0: Call<data?>, p1: Throwable) {
                Log.d("Main Activity","onFailure"+p1.message)
            }
        })
        binding.editTextText3.setOnClickListener()
        {
            startActivity(Intent(this,MainActivity4::class.java))
        }
    }
}