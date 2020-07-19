package com.app.bubbledemoapp.core.network.webservice

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitCallAPI {

    companion object {

        private var retrofitAPIinterface: RetrofitAPIinterface? = null

        internal var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        fun getInstance(baseUrl: String): RetrofitAPIinterface? {
            if (retrofitAPIinterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(okHttpClient)
                    .build()

                retrofitAPIinterface = retrofit.create(RetrofitAPIinterface::class.java)
            }

            return retrofitAPIinterface
        }

    }

}



