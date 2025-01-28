package com.example.atividadeapi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.atividadeapi.R
import com.example.atividadeapi.databinding.ActivityMainBinding
import com.example.atividadeapi.repository.api.client.ClientRetrofit
import com.example.atividadeapi.repository.api.model.BlogPostEntity
import com.example.atividadeapi.repository.api.service.BlogPostService
import com.example.atividadeapi.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getPostBtn.setOnClickListener(this)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()

        val bpService = ClientRetrofit.createService(BlogPostService::class.java)
    }

    private fun setObserver() {
        mainVM.getPost().observe(this, Observer {
            binding.postTextTv.text = it.toString()
        })
    }

    override fun onClick(view: View){
        if (view.id == R.id.getPost_btn){
            val bpService = ClientRetrofit.createService(BlogPostService::class.java)
            val call: Call<BlogPostEntity> = bpService.getSinglePost(binding.postIdEt.text.toString().toInt())

            call.enqueue(object: Callback<BlogPostEntity> {
                override fun onResponse(
                    call: Call<BlogPostEntity>,
                    response: Response<BlogPostEntity>
                ) {
                    mainVM.setPost(response.body()!!.body)
                }

                override fun onFailure(call: Call<BlogPostEntity>, t: Throwable) {
                    Log.d("ERRO", "Falha na chamada de API")
                }
            })

        }
    }
}