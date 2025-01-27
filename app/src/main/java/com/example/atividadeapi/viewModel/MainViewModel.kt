package com.example.atividadeapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private var post = MutableLiveData<String>();

    fun getPost() : LiveData<String> {
        return post;
    }

    fun setPost(newPost: String) {
        post.value = newPost;
    }
}