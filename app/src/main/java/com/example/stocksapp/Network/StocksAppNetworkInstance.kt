package com.example.stocksapp.Network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StocksAppNetworkInstance {

    companion object {
        private const val BASE_URL = "https://storage.googleapis.com/cash-homework/cash-stocks-api/"

        fun provideNetworkInstance(): Retrofit {
            val gson = GsonBuilder().create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

    }
}