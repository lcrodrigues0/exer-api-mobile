package com.example.atividadeapi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.atividadeapi.databinding.ActivityMainBinding
import com.example.atividadeapi.viewModel.MainViewModel

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
    }

    private fun setObserver() {
        mainVM.getPost().observe(this, Observer {
            binding.postTextTv.text = it.toString()
        })
    }

    override fun onClick(view: View){
        if (view.id == R.id.getPost_btn){
            mainVM.setPost(binding.postIdEt.text.toString())
        }
    }
}