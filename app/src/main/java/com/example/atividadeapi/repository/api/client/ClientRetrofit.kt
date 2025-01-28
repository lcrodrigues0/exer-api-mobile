package com.example.atividadeapi.repository.api.client

import com.example.atividadeapi.repository.api.service.BlogPostService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ClientRetrofit {
    companion object{
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private fun getClientInstace(): Retrofit {
            if(!::INSTANCE.isInitialized) {
                val http = OkHttpClient.Builder()

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return INSTANCE
        }

        fun <S> createService(className: Class<S>): S {
            return getClientInstace().create(className)
        }
    }
}