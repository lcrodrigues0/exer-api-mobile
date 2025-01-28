package com.example.atividadeapi.repository.api.service

import com.example.atividadeapi.repository.api.model.BlogPostEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogPostService {
    @GET("posts/{postId}")
    fun getSinglePost(@Path("postId")postId: Int): Call<BlogPostEntity>
}