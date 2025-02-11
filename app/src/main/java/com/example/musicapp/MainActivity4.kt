package com.example.musicapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.ActivityMain4Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    private lateinit var datalist: ArrayList<DataX>
    private lateinit var radapter: MyAdapter5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var value=intent.getIntExtra("yes",0)

        val reterofitbuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)
        val retrofitData=reterofitbuilder.getdata("eminem")
        datalist=ArrayList<DataX>()
        retrofitData.enqueue(object : Callback<data?> {
            override fun onResponse(p0: Call<data?>, p1: Response<data?>) {
                val datalist=p1.body()?.data!!.toList() as ArrayList<DataX>

                binding.rv7.layoutManager= LinearLayoutManager(this@MainActivity4,
                    LinearLayoutManager.VERTICAL,false)


                    radapter=MyAdapter5(this@MainActivity4,datalist)
                    binding.rv7.adapter=radapter




            }

            override fun onFailure(p0: Call<data?>, p1: Throwable) {
                Log.d("Main Activity","onFailure"+p1.message)
            }
        })
        binding.editTextText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString()!=""){
                    filterdetails(p0.toString())

                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }
    private fun filterdetails(filereddata: String) {
        var filterData= ArrayList<DataX>()
        for(item in datalist) {
            if (item.title.lowercase().contains(filereddata.lowercase())) {
                filterData.add(item)
            }
            if(filereddata.length==0)
            {
                filterData=datalist
            }
        }
        radapter.filterList(filterlist = filterData)


    }
}